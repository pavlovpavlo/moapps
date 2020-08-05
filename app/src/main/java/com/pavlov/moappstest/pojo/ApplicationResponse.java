package com.pavlov.moappstest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationResponse {
    @SerializedName("data")
    @Expose
    private List<Application> data = null;

    public List<Application> getData() {
        return data;
    }

}
