package com.example.nahid.newsviews.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nahid.newsviews.Model.Articles;
import com.example.nahid.newsviews.Model.News;
import com.example.nahid.newsviews.R;
import com.example.nahid.newsviews.Utils.NewsOnClick;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<Articles> articlesList;
    private NewsOnClick newsOnClick;

    public NewsAdapter(Context context, List<Articles> articlesList, NewsOnClick newsOnClick) {
        this.context = context;
        this.articlesList = articlesList;
        this.newsOnClick= newsOnClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.news_adapter, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Articles articles = articlesList.get(i);
        myViewHolder.tvTitle.setText(articles.getTitle());
        myViewHolder.tvDescription.setText(articles.getDescription());
        myViewHolder.tvDate.setText(articles.getPublishedAt());
        Picasso.with(context).load(articles.getUrlToImage()).into(myViewHolder.imageView);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsOnClick.onNewsClick(articles);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDescription, tvDate;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }


}
