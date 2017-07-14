package com.teteran.rozkladjazdy;

/**
 * Created by Kacper on 2017-07-14.
 */

public class Course {


    String time;
    String type;



    public Course(String time, String type){
        this.time=time;
        this.type=type;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return time+"[3]"+type;
    }
}
