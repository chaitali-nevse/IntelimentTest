package com.android.test.inteliment.services;

import com.android.test.inteliment.model.TouristPlace;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Chaitali on 12/04/2017.
 */

public interface Services {

    @GET("/sample.json")
    void getTouristPlaces(Callback<List<TouristPlace>> callBack);

}

