package com.android.test.inteliment.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class TouristPlace {
    private String id;

    private Location location;

    private String name;

    @SerializedName("fromcentral")
    private FromCentral fromCentral;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public FromCentral getFromCentral ()
    {
        return fromCentral;
    }

    public void setFromCentral (FromCentral fromCentral)
    {
        this.fromCentral = fromCentral;
    }

    @Override
    public String toString() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
