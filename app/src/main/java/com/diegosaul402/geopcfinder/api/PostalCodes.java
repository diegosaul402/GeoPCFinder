package com.diegosaul402.geopcfinder.api;

import com.diegosaul402.geopcfinder.entities.PostalCode;

import java.util.List;

/**
 * Created by diego on 01/12/2016.
 */

public class PostalCodes {
    private int count;
    private List<PostalCode> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PostalCode> getResults() {
        return results;
    }

    public void setResults(List<PostalCode> results) {
        this.results = results;
    }
}
