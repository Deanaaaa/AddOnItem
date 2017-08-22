package com.imooc.addonitem;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by 文静 on 2017/8/21.
 */

public class GatherPostTable extends BmobObject {
    private String createdUserName,itemName,remark,label,web,link;
    private BmobFile picFile;
    private Integer daysRemaining;
    private Map<String,Object> shoppingList;
    private boolean isManJian;
    private double goalMoney,nowMoney,progress;
    public Vector<String> userId = new Vector<>();

    public GatherPostTable(String itemName, String createdUserName, String remark, String label, String web, Integer daysRemaining,
                           double createdUserMoney, double goalMoney, boolean isManJian) {
        this.itemName = itemName;
        this.createdUserName = createdUserName;
        this.remark = remark;
        this.label = label;
        this.web = web;
        this.daysRemaining = daysRemaining;
        nowMoney = createdUserMoney;
        this.goalMoney = goalMoney;
        this.isManJian = isManJian;
        progress = (nowMoney/this.goalMoney <= 1)?(nowMoney/this.goalMoney*100):100;
        shoppingList = new HashMap<>();
        shoppingList.put(createdUserName,createdUserMoney);
        /////////////////////////////////////////
        // 添加图片，考虑未上传图片



    }
    public GatherPostTable(){

    }


    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public BmobFile getPicFile() {
        return picFile;
    }



    public void setPicFile(String picPath) {
        picFile = new BmobFile(new File(picPath));
        picFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Log.i(">>>>>>>>>>>>>>>>>>>>",picFile.getFileUrl());
                }
                else
                    Log.i(">>>>>>>>>>>>>>","上传文件失败");

            }

            @Override
            public void onProgress(Integer value) {
                //super.onProgress(value);
                ////返回上传文件的百分比
            }
        });
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public Map<String, Object> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Map<String, Object> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public boolean isManJian() {
        return isManJian;
    }

    public void setManJian(boolean manJian) {
        isManJian = manJian;
    }

    public double getGoalMoney() {
        return goalMoney;
    }

    public void setGoalMoney(double goalMoney) {
        this.goalMoney = goalMoney;
    }

    public double getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(double nowMoney) {
        this.nowMoney = nowMoney;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}


