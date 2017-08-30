package com.example.ahsan.youtube.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ahsan.youtube.R;

import java.util.ArrayList;

import com.example.ahsan.youtube.Models.DataModel;
import com.example.ahsan.youtube.vedio_view;
import com.squareup.picasso.Picasso;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {

    private ArrayList<DataModel> dataSet;

    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewViews;
        TextView textViewDuration;
        ImageView imageViewIcon;
        ArrayList<DataModel> dataSet;
        public MyViewHolder(final View itemView, final ArrayList<DataModel> dataSet) {
            super(itemView);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.title);
            this.textViewAuthor = (TextView) itemView.findViewById(R.id.author);
            this.textViewViews = (TextView) itemView.findViewById(R.id.views);
            this.textViewDuration = (TextView) itemView.findViewById(R.id.duration);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);
            this.dataSet=dataSet;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Toast.makeText(itemView.getContext(),dataSet.get( getPosition()).getUrl(), Toast.LENGTH_SHORT).show();
                    String url=dataSet.get( getPosition()).getUrl();
                    String title =dataSet.get(getPosition()).getTitle();
                    Intent goToNewActivity = new Intent(itemView.getContext(),vedio_view.class);
                    goToNewActivity.putExtra("url", url);
                    goToNewActivity.putExtra("title",title);

                    context.startActivity(goToNewActivity);

                }

            });
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design, parent, false);
        context=parent.getContext();

        MyViewHolder myViewHolder = new MyViewHolder(view,dataSet);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        TextView textViewTitle = holder.textViewTitle;
        TextView textViewAuthor = holder.textViewAuthor;
        TextView textViewView = holder.textViewViews;
        TextView textViewDuration = holder.textViewDuration;
        ImageView imageView = holder.imageViewIcon;
        textViewTitle.setText(dataSet.get(listPosition).getTitle());
        textViewAuthor.setText(dataSet.get(listPosition).getAuthor());
        textViewView.setText(dataSet.get(listPosition).getViews());
        textViewDuration.setText(dataSet.get(listPosition).getDuration());
        Picasso.with(context).load(dataSet.get(listPosition).getImage()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}