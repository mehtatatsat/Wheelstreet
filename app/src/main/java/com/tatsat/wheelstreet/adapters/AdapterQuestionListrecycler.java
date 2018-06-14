package com.tatsat.wheelstreet.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tatsat.wheelstreet.R;
import com.tatsat.wheelstreet.models.Questions;

import java.util.List;

public class AdapterQuestionListrecycler extends RecyclerView.Adapter<AdapterQuestionListrecycler.MyViewHolder> {

    private List<Questions.Datum> dataSet;

    public AdapterQuestionListrecycler(Context act, List<Questions.Datum> data) {

        this.dataSet = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_ques,tv_ans,tv_answer;
        ImageView iv_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_ques = (TextView) itemView.findViewById(R.id.tv_ques);
            this.tv_ans = (TextView) itemView.findViewById(R.id.tv_ans);
            this.tv_answer = (TextView) itemView.findViewById(R.id.tv_answer);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TextView textViewName = holder.tv_ques;
        TextView imageView = holder.tv_ans;
        TextView txtans = holder.tv_answer;


        final Questions.Datum model = dataSet.get(position);


        if (!model.getGivenANs().equalsIgnoreCase("")){
            txtans.setText(""+model.getGivenANs());
            txtans.setVisibility(View.VISIBLE);
        }else{
            txtans.setVisibility(View.GONE);
        }
        textViewName.setText(model.getQuestion());
        imageView.setText(model.getDataType());
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
