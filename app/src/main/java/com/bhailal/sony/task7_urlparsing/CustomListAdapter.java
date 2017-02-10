package com.bhailal.sony.task7_urlparsing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sony on 08-02-2017.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Post>p;


    public CustomListAdapter(Activity activity,List<Post>p){
        this.activity = activity;
        this.p =p ;

    }
    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView =inflater.inflate(R.layout.list_row,null);

        TextView t1 = (TextView)convertView.findViewById(R.id.txtuserid);
        TextView t2 = (TextView)convertView.findViewById(R.id.txtid);
        TextView t3 = (TextView)convertView.findViewById(R.id.txttitle);
        TextView t4 = (TextView)convertView.findViewById(R.id.txtbody);


        Post m = p.get(position);
        t1.setText("UserId: "+String.valueOf(m.getUseri()));
        t2.setText("ID: "+String.valueOf(m.getId()));
        t3.setText("Title: "+m.getTitle());
        t4.setText("Body: "+m.getBody());
        




        return convertView;
    }
}
