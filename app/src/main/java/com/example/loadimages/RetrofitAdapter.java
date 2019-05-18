package com.example.loadimages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    private Context inflater;
    private ArrayList<ModelRec> dataModelArrayList;

    public RetrofitAdapter(Context ctx, ArrayList<ModelRec> dataModelArrayList){

        inflater = ctx;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);        return holder;
    }

    @Override
    public void onBindViewHolder(RetrofitAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(dataModelArrayList.get(position).getUrl()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getId());
        holder.country.setText(dataModelArrayList.get(position).getAlbumId());
        holder.city.setText(dataModelArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country, name, city;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.city);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}