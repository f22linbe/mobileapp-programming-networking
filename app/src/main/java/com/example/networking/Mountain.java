package com.example.networking;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Mountain {
    @SerializedName("ID")
    private String id;
    private String name;
    private String type;
    //private String location;
    private String company;
    private String category;
    @SerializedName("size")
    private int meters;
    private int cost;
    private int height;

    // Default constructor

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + meters+"m";
    }

}
