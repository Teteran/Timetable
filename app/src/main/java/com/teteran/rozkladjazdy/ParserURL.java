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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    if (tds.get(j).text().contains("*")) {
                        addCourse(tds.get(j).text(),tds.get(j).toString());

                    }
                }
            }
            Log.d("..","Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCourse(String string, String html){
        String[] fields = string.split(" ");
        parseHTML(html);
        Course c = new Course(fields[0],fields[1],parseHTML(html));
        MainActivity.courses.add(c);

    }

    private List<String> parseHTML(String html){
        Pattern pattern = Pattern.compile("&lt;/span&gt;(.*?)&lt;/li&gt;");
        Matcher matcher = pattern.matcher(html);
        List<String> details = new ArrayList<>();
        while (matcher.find()) {
            details.add(matcher.group(1));
        }

        return details;
    }




}