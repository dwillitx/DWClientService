package darin.com.dwclientservice.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import darin.com.dwclientservice.CertificateActivity;
import darin.com.dwclientservice.GraduateActivity;
import darin.com.dwclientservice.MainActivity;
import darin.com.dwclientservice.R;
import darin.com.dwclientservice.model.RegisData;

/**
 * Created by Darin on 7/6/2016.
 */
public class CourseAdapter extends ArrayAdapter{

    List list = new ArrayList();

    public CourseAdapter(Context context, int resource) {
        super(context, resource);
    }



    public void add(RegisData regisData) {
        super.add(regisData);

        //Add to ArrayList
        list.add(regisData);
    }



    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;

        row = convertView;
        MyViewHolder myViewHolder;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.course_row, parent, false);
            myViewHolder = new MyViewHolder();
            myViewHolder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            row.setTag(myViewHolder);

        } else{

            myViewHolder = (MyViewHolder) row.getTag();
        }

        RegisData regisData = (RegisData) this.getItem(position);
        myViewHolder.tx_name.setText(regisData.getName());

        return row;
    }


    /**
     * Bundle to hold refs to row items views.
     *
     */
    static class MyViewHolder {
        TextView tx_name;

    }
}

