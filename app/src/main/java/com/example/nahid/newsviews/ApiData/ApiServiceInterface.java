package com.example.nahid.newsviews.ApiData;

import com.example.nahid.newsviews.Model.News;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceInterface {

    @GET("everything")
    Call<News> getTopHeadlines(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<News> getSources(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey);

    @GET("{number}/trivia")
    Call<ResponseBody> getNumberTrivia(
            @Path("number") String number);

    @GET("{dateInput}/date")
    Call<ResponseBody> getDateTrivia(
            @Path("dateInput") String date);

}
