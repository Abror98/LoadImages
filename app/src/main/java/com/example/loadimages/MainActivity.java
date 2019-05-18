package com.example.loadimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
         writeRecycler();
    }



    private void writeRecycler(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecInterface.JSONURL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        RecInterface api = retrofit.create(RecInterface.class);

        Call<List<ModelRec>> call = api.getString();
        call.enqueue(new Callback<List<ModelRec>>() {
            @Override
            public void onResponse(Call<List<ModelRec>> call, Response<List<ModelRec>> response) {
                ArrayList<ModelRec> heroList = (ArrayList<ModelRec>) response.body();
                ArrayList<ModelRec> modelRecyclerArrayList = new ArrayList<ModelRec>();
                //Creating an String array for the ListView
                Log.d("salomsalomsalomsal", String.valueOf(heroList.size()));
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    ModelRec modelRecycler = new ModelRec();
                    modelRecycler.setUrl(heroList.get(i).getUrl());
                    modelRecycler.setAlbumId(heroList.get(i).albumId);
                    modelRecycler.setId(heroList.get(i).getId());
                    modelRecycler.setTitle(heroList.get(i).getTitle());
                    modelRecyclerArrayList.add(modelRecycler);
                }

                retrofitAdapter = new RetrofitAdapter(getApplicationContext(),modelRecyclerArrayList);
                recyclerView.setAdapter(retrofitAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


            }

            @Override
            public void onFailure(Call<List<ModelRec>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
