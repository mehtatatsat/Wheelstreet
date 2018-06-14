package com.tatsat.wheelstreet.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tatsat.wheelstreet.R;
import com.tatsat.wheelstreet.models.Questions;

import java.util.List;


/**
 * Created by shivlab-android1 on 16/11/16.
 */
public class AdapterHomeList extends BaseAdapter {
    private final Context context;
    //    private String[] name ;
    private List<Questions.Datum> detail;
    private LayoutInflater inflater;

    public AdapterHomeList(Context context, List<Questions.Datum> detail) {
        this.context = context;
        this.detail = detail;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return (detail != null ? detail.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_home, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

       /* if (detail.get(position).getOver18().equalsIgnoreCase("1")) {
            viewHolder.tv_title.setTextColor(ContextCompat.getColor(context, R.color.color_text_nsfw_dark));
        } else {
            viewHolder.tv_title.setTextColor(ContextCompat.getColor(context, R.color.color_text_sfw_dark));
        }*/

        viewHolder.tv_ques.setText(detail.get(position).getQuestion());
        viewHolder.tv_ans.setText(detail.get(position).getDataType());
        return convertView;
    }

    private class MyViewHolder {
        TextView tv_ques, tv_ans;

        public MyViewHolder(View item) {
            tv_ques = (TextView) item.findViewById(R.id.tv_ques);
            tv_ans = (TextView) item.findViewById(R.id.tv_ans);


        }
    }
}
