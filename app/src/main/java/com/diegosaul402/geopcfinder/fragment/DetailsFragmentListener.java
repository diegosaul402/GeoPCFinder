package com.diegosaul402.geopcfinder.fragment;

import com.diegosaul402.geopcfinder.entities.PostalCode;

/**
 * Created by diego on 12/12/2016.
 */

public interface DetailsFragmentListener {
    void initList();
    void addToList(PostalCode postalCode);
    void clearList();
}
