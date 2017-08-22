package com.imooc.addonitem;

/**
 * Created by 文静 on 2017/8/21.
 */

public class talkItemModel  {
    private int photo;
    private String name;
    private String talk;
    private String time;
    private String noSeeNumber;

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getTalk() {
        return talk;
    }

    public String getTime() {
        return time;
    }

    public String getNoSeeNumber() {
        return noSeeNumber;
    }

    public talkItemModel(int photo, String name, String talk, String time, String noSeeNumber){
        this.photo = photo;
        this.name = name;
        this.talk = talk;
        this.time = time;
        this.noSeeNumber = noSeeNumber;
    }

    public void setPhoto(int photo){
        this.photo = photo;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTalk(String talk){
        this.talk = talk;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setNoSeeNumber(String noSeeNumber){
        this.noSeeNumber = noSeeNumber;
    }


}
