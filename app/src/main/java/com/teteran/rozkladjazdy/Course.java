package com.teteran.rozkladjazdy;

import android.app.Dialog;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Kacper on 2017-07-14.
 */

public class Course {


    String time;
    String type;
    List<String> details;



    public Course(String time, String type,  List<String> details){
        this.time=time;
        this.type=type;
        this.details=details;
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


    public  List<String> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return time+"[3]"+type;
    }

    public void setDetailsDialog(Dialog dialog) {
        TextView timeV = (TextView) dialog.findViewById(R.id.time);
        timeV.setText(time);

        TextView time_textV = (TextView) dialog.findViewById(R.id.time_text);
        time_textV.setText(details.get(0));

        TextView typeV = (TextView) dialog.findViewById(R.id.type);
        typeV.setText(type);

        TextView type_textV = (TextView) dialog.findViewById(R.id.type_text);
        type_textV.setText(details.get(1));
    }
}
