package com.example.nahid.newsviews.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nahid.newsviews.Model.Articles;
import com.example.nahid.newsviews.R;
import com.example.nahid.newsviews.Utils.StaticValue;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewAuthor, textViewUrl, textViewDescription;
    private ImageView imageView;
    private Articles articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initiaize();

        articles = (Articles) getIntent().getSerializableExtra(StaticValue.ARTICLE);

        setData(articles);

        alertDiaog();
    }

    private void alertDiaog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("NewsViews")
                .setMessage("Do you want to load content in browser?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra(StaticValue.WEB_URL,articles.getUrl());
                        intent.putExtra("option", "2");
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra(StaticValue.WEB_URL,articles.getUrl());
                        intent.putExtra("option", "1");
                        startActivity(intent);
                        finish();

                    }
                })

                .setCancelable(false)
                .show();
    }

    private void setData(Articles articles) {
        textViewTitle.setText(articles.getTitle());
        textViewAuthor.setText(articles.getAuthor());
        textViewDescription.setText(articles.getDescription());
        textViewUrl.setText(articles.getUrl());

        Picasso.with(this).load(articles.getUrlToImage()).into(imageView);
    }

    private void initiaize() {

        textViewTitle = (TextView) findViewById(R.id.tv_title);
        textViewAuthor = (TextView) findViewById(R.id.tv_author);
        textViewUrl = (TextView) findViewById(R.id.tv_url);
        textViewDescription = (TextView) findViewById(R.id.tv_description);
        imageView =(ImageView)findViewById(R.id.img_article);
    }
}
