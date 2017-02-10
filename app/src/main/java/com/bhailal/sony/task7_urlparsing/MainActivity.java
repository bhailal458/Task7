package com.bhailal.sony.task7_urlparsing;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Post> plist = new ArrayList<Post>();
    private ListView lsview;
    private CustomListAdapter adapter;
    private Exception exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String title = null,body = null;
        int userid = 0,id = 0;

        lsview = (ListView)findViewById(R.id.listview);
        adapter = new CustomListAdapter(this,plist);
        lsview.setAdapter(adapter);


        HttpURLConnection connection = null;

        URL url = null;
        try {
            url = new URL("https://jsonplaceholder.typicode.com/posts");
        try {
            connection = (HttpURLConnection) url.openConnection();   //bund connection
            connection.connect();




        InputStream stream =  connection.getInputStream();   //data get to the net

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));//


        StringBuffer buffer = new StringBuffer();
        String line= " ";
        while((line = reader.readLine())!=null)
        {
            buffer.append(line);
        }
            String bufferString = buffer.toString();



            try {
                JSONArray rootArray = new JSONArray(bufferString);

                for (int i = 0; i < rootArray.length(); i++) {

                    JSONObject myobj = rootArray.getJSONObject(i);

                    userid = myobj.getInt("userId");
                    id = myobj.getInt("id");
                    title = myobj.getString("title");
                    body = myobj.getString("body");

                    Post p = new Post();
                    p.setUseri(userid);
                    p.setId(id);
                    p.setTitle(title);
                    p.setBody(body);

                    plist.add(p);

                }

            } catch (Exception e) {
                this.exception = e;
            }
        } catch (Exception e) {
            this.exception = e;
        }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }





    }
}
