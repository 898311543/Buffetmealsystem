package com.example.a13051_000.buffetmealsystem.Fragment.FragmentForm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubin on 2016/6/16.
 */
public class Result implements Serializable{
    private String order_num;
    private String order_belong;
    private String order_price;
    private boolean order_finish;
    private String seat_num;
    private Order_detail order_detail;

    public void setOrder_belong(String order_belong) {
        this.order_belong = order_belong;
    }

    public Order_detail getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(Order_detail order_detail) {
        this.order_detail = order_detail;
    }

    public String getOrder_belong() {
        return order_belong;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_price(String order_price) {
        order_price = order_price;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_finish(boolean order_finish){this.order_finish = order_finish;}

    public boolean getOrder_finish(){return order_finish;}

    public void setSeat_num(String seat_num) {
        this.seat_num = seat_num;
    }

    public String getSeat_num() {
        return seat_num;
    }

    public static class Order_detail implements Serializable{
        public List<String> dish_name;
        public List<String> num;
        public List<String> id;
        public ArrayList<Boolean> finish = new ArrayList<>();
        public void setDish_name(List<String> dish_name) {
            this.dish_name = dish_name;
        }

        public List<String> getDish_name() {
            return dish_name;
        }

        public void setNum(List<String> num) {
            this.num = num;
        }

        public List<String> getNum() {
            return num;
        }

        public List<String> getId() {
            return id;
        }

        public void setId(List<String> id) {
            this.id = id;
        }

        public void setFinish(ArrayList<Boolean> finish){this.finish = finish;}
        public void setFinish(int num,Boolean finish){
            this.finish.add(num,finish);
        }
        public List<Boolean> getFinish(){return finish;}

    }
}
