package com.teteran.rozkladjazdy;

/**
 * Created by Kacper on 2017-07-14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kacper on 2017-07-14.
 */

public class AdapterForCourse extends ArrayAdapter<Course> {


    Context ctx;

    public AdapterForCourse(Context context, int simple_list_item_1, List<Course> objects) {
        super(context, R.layout.course, objects);
        ctx = context;
    }


    public View getView(int pos, View v, ViewGroup p) {
        View courseView = v;

        if (courseView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            courseView = inflater.inflate(R.layout.course, p, false);
        }

        Course course = getItem( pos);
        ((TextView)courseView.findViewById(R.id.Time)).setText(course.getTime());
        ((TextView)courseView.findViewById(R.id.Symbol)).setText(course.getType());



        return courseView;
    }
}

