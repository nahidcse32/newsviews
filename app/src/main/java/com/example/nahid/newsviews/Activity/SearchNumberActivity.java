
package com.example.nahid.newsviews.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nahid.newsviews.ApiData.ApiServiceInterface;
import com.example.nahid.newsviews.ApiData.RetrofitClient;
import com.example.nahid.newsviews.ApiData.RetrofitClientNumber;
import com.example.nahid.newsviews.Model.News;
import com.example.nahid.newsviews.R;
import com.example.nahid.newsviews.Utils.StaticValue;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNumberActivity extends AppCompatActivity {

    private EditText editTextNumber;
    private TextView textViewResult;
    private Button buttonSearch;
    private String stringValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_number);

        initialize();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringValue = editTextNumber.getText().toString();
                textViewResult.setText("");
                if (stringValue.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter number or date", Toast.LENGTH_SHORT).show();
                } else {
                    textViewResult.setVisibility(View.VISIBLE);
                    if (stringValue.split("/").length < 2) {
                        getNumberData();
                    } else {
                        getDateData();
                    }

                }

            }
        });


    }

    private void getDateData() {

        ApiServiceInterface apiService = RetrofitClientNumber.getClient().create(ApiServiceInterface.class);
        Call<ResponseBody> call = apiService.getDateTrivia(stringValue);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    textViewResult.setText(response.body().string());
                } catch (IOException e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }

    private void getNumberData() {
        ApiServiceInterface apiService = RetrofitClientNumber.getClient().create(ApiServiceInterface.class);
        Call<ResponseBody> call = apiService.getNumberTrivia(stringValue);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    textViewResult.setText(response.body().string());
                } catch (IOException e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void initialize() {

        textViewResult = (TextView)findViewById(R.id.tv_result);
        editTextNumber = (EditText)findViewById(R.id.et_number);
        buttonSearch = (Button)findViewById(R.id.btn_search);


    }
}
