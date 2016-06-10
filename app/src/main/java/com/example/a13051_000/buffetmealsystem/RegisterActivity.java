package com.example.a13051_000.buffetmealsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 13051_000 on 2016/4/20.
 */
public class RegisterActivity extends BaseActivity {
    public static final int SHOW_RESPONSE = 0;
    private Button button1;
    private TextView textView1;
    private static String sResult = "";
    private ProgressDialog progressDialog;
    private String number;
    private String pwd;
    //网络连接检查函数:::
    private boolean AccessNetworkState(){
        ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connManager.getActiveNetworkInfo() != null){
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        else
            return false;
    }
    //异步消息处理；；；
    private android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case SHOW_RESPONSE:
                    String response = (String) message.obj;
                    sResult = response;
                    if (!sResult.equals(-1)) {
                        Gson gson = new Gson();
                        String result = "";
                        ResultFromServer loginResult = null;
                        try {
                            loginResult = gson.fromJson(sResult,ResultFromServer.class);
                        } catch (Exception e) {
                            Log.d("data1", e.toString());
                        }
                        progressDialog.dismiss();
                        Log.d("data1",sResult);
                        String nick_name = "";
                        if (loginResult.getNick_name() != null) {
                            Toast.makeText(getApplicationContext(), "您的用户名为:" + loginResult.getNick_name(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "信息输入错误,请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button1= (Button) findViewById(R.id.button1);
        textView1= (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterActivity.this,LoginActivity.class);
                RegisterActivity.this.startActivity(intent1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动等待活动
                switch (view.getId()) {
                    case R.id.button1:
                        if(AccessNetworkState()) {
                            progressDialog = new ProgressDialog(RegisterActivity.this);
                            progressDialog.setTitle("正在加载...");
                            progressDialog.setMessage("Loading...");
                            progressDialog.setCancelable(true);
                            progressDialog.show();
                            Log.d("data1", "Result:" + sResult);
                            //将数据加入请求当中
                            number = ((EditText) findViewById(R.id.editText1)).getText().toString();
                            pwd = ((EditText) findViewById(R.id.editText2)).getText().toString();
                            boolean flag = false;
                            String nickname = "";
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("user", number));
                            params.add(new BasicNameValuePair("password", pwd));
                            String strUrlPath = "http://www.loushubin.cn/login_user.php";
                            //调用Thread，创建新线程进行网络请求
                            sendRequest(strUrlPath, params);
                        }
                        else
                            Toast.makeText(RegisterActivity.this,"未连接到网络,请检查网络连接设置",Toast.LENGTH_SHORT).show();
                }
            }
            //启动新的线程
            private void sendRequest(final String strUrlPath, final List<NameValuePair> params){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String strResult = null;
                        try {
                            strResult = HttpUtils.submitPostData(strUrlPath,params,"utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = strResult;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
}
}
