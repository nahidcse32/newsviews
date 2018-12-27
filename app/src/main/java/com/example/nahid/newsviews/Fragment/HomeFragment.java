package com.example.nahid.newsviews.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nahid.newsviews.Activity.DetailsActivity;
import com.example.nahid.newsviews.Adapter.NewsAdapter;
import com.example.nahid.newsviews.ApiData.ApiServiceInterface;
import com.example.nahid.newsviews.ApiData.RetrofitClient;
import com.example.nahid.newsviews.Model.Articles;
import com.example.nahid.newsviews.Model.News;
import com.example.nahid.newsviews.R;
import com.example.nahid.newsviews.Utils.NewsOnClick;
import com.example.nahid.newsviews.Utils.StaticValue;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewsOnClick{


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewHeadlines, recyclerViewSources;

    private NewsAdapter newsAdapter;

    private LinearLayoutManager layoutManager1, layoutManager2;

    private NewsOnClick newsOnClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        newsOnClick = this;
        recyclerViewHeadlines = (RecyclerView)view.findViewById(R.id.rv_news_headline);
        recyclerViewSources = (RecyclerView)view.findViewById(R.id.rv_news_source);
        layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewHeadlines.setLayoutManager(layoutManager1);
        recyclerViewSources.setLayoutManager(layoutManager2);
        recyclerViewHeadlines.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSources.setItemAnimator(new DefaultItemAnimator());

        getTopHeadlines();
        getSources();

        return view;
    }

    private void getSources() {
        ApiServiceInterface apiService = RetrofitClient.getClient().create(ApiServiceInterface.class);
        Call<News> call = apiService.getSources("bbc-news", StaticValue.API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                Log.e("data", String.valueOf(response.body()));
                newsAdapter = new NewsAdapter(getContext(), response.body().getArticles(), newsOnClick);
                recyclerViewSources.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                getTopHeadlines();
                getSources();

                break;
        }

        return true;
    }
    private void getTopHeadlines() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ApiServiceInterface apiService = RetrofitClient.getClient().create(ApiServiceInterface.class);
        Call<News> call = apiService.getTopHeadlines("espn-cric-info", StaticValue.API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                progressDialog.dismiss();
                Log.e("data", String.valueOf(response.body()));
                newsAdapter = new NewsAdapter(getContext(), response.body().getArticles(), newsOnClick);
                recyclerViewHeadlines.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onNewsClick(Articles article) {

        Intent intent=new Intent(getContext(),DetailsActivity.class);
        intent.putExtra(StaticValue.ARTICLE,  article);
        startActivity(intent);
    }
}
