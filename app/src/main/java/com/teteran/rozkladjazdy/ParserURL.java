package com.teteran.rozkladjazdy;

import android.os.AsyncTask;
import android.util.Log;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper on 2017-07-14.
 */

public class ParserURL extends AsyncTask<Void, Void, Void> {



    String URL = "http://pks.zgora.pl/index.php/pasazer/rozklad-jazdy/krosno-odrz.html";

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document doc = Jsoup.connect(URL).get();

            Element table = doc.select("table").first();
            Elements rows = table.select("tr");


            int rowIndexStart=0;
            int rowIndexStop=0;
            for(int i=0;i<rows.size();i++){
                Element row = rows.get(i);
                Elements tds = row.select("td");
                if(tds.get(0).text().equalsIgnoreCase("ZIELONA GÓRA")  && tds.get(1).text().equalsIgnoreCase("Łagów"))
                    rowIndexStart=i;
                if(!tds.get(0).text().equals("") && i > rowIndexStart)
                    rowIndexStop=i;
            }

            for(int i=rowIndexStart;i<rowIndexStop;i++){
                Element row = rows.get(i);
                Elements tds = row.select("td");
                for(int j=2;j<tds.size();j++){
                    if (tds.get(j).text().contains("*"))
                        addCourse(tds.get(j).text());
                }
            }
            Log.d("..","Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCourse(String string){
        String[] fields = string.split(" ");
        Course c = new Course(fields[0],fields[1]);
        MainActivity.courses.add(c);


    }





}