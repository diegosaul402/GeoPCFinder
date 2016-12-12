package com.diegosaul402.geopcfinder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diegosaul402.geopcfinder.R;
import com.diegosaul402.geopcfinder.entities.PostalCode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diego on 11/12/2016.
 */

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.ViewHolder> {

    private Context context;
    private List<PostalCode> geoList;
    OnItemClickListener onItemClickListener;

    public GeoAdapter(Context context, List<PostalCode> GeoList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.geoList = GeoList;
        this.onItemClickListener = onItemClickListener;
    }

    public GeoAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return geoList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostalCode element = geoList.get(position);

        String colonia = element.getPostalCode() + ": " + element.getPlaceName() + ", " + element.getAdminName2() + ", " + element.getAdminName1()  + ", " + element.getCountryCode();
        holder.textResult.setText(colonia);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    public void add(PostalCode postalCode){
        geoList.add(postalCode);
        notifyDataSetChanged();
    }

    public void clear(){
        geoList.clear();
        notifyDataSetChanged();
    }

    public void init(){
        geoList = new ArrayList<PostalCode>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textResult)
        TextView textResult;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final PostalCode element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}
