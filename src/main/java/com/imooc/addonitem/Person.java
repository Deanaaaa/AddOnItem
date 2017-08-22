package com.imooc.addonitem;

import java.util.Vector;

import cn.bmob.v3.BmobUser;

/**
 * Created by 文静 on 2017/7/30.
 */

public class Person extends BmobUser {


    public Vector<String> orderNumber = new Vector<>();

    public Person(){

    }

    public void setOrderNumber(Vector<String> orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void addOrderNumber(String orderNumber){

        this.orderNumber.add(orderNumber);
    }

    public Vector<String> getOrderNumber() {
        return orderNumber;
    }

    public static Person getCurrentUser() {
        return (Person) getCurrentUser(Person.class);
    }


   // Bmob bmob = new Bmob();
   /* private String username;
    private String mobilePhoneNumber;
    private String mobilePhoneNumberVerified;
    private String password;

    private Vector<Map> things;
    private Map<String, Integer> oneThing;


   // Bmob.initialize(this,"1d30b11d2f4bc8f7352ea055dcbb1230");


    public void setPersonName(String name){
        username = name;
    }
    public void setPersonNumber(String number){
        mobilePhoneNumber = number;
    }
    public void setPersonPassword(String password){
        password = password;
    }*/
   // public String getPersonPassword(){ return password; }
}
