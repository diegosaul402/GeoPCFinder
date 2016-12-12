package com.diegosaul402.geopcfinder.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diegosaul402.geopcfinder.R;
import com.diegosaul402.geopcfinder.adapter.GeoAdapter;
import com.diegosaul402.geopcfinder.adapter.OnItemClickListener;
import com.diegosaul402.geopcfinder.entities.PostalCode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements DetailsFragmentListener, OnItemClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    GeoAdapter geoAdapter;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(geoAdapter);
    }

    private void initAdapter() {
        if (geoAdapter == null) {
            geoAdapter = new GeoAdapter(getActivity().getApplicationContext(), this);
        }
    }

    @Override
    public void initList() {
        geoAdapter.init();
    }

    @Override
    public void addToList(PostalCode postalCode) {
        geoAdapter.add(postalCode);
    }

    @Override
    public void clearList() {
        geoAdapter.clear();
    }

    @Override
    public void onItemClick(PostalCode postalCode) {
        // Creates an Intent that will load a map of San Francisco
        String colonia = postalCode.getPlaceName().replace(" ", "+");
        String uri = "geo:" + Double.toString(postalCode.getLat()) + "," + Double.toString(postalCode.getLng()) + "?z=11&q=" + colonia;
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
