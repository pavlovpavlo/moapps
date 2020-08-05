package com.pavlov.moappstest.pojo;

import com.google.gson.annotations.SerializedName;

public class ApplicationPostBody {

    @SerializedName("skip")
    private String skip;
    @SerializedName("take")
    private String take;
    @SerializedName("osType")
    private String osType;
    @SerializedName("userToken")
    private String userToken;

    public ApplicationPostBody(String skip, String take, String osType, String userToken) {
        this.skip = skip;
        this.take = take;
        this.osType = osType;
        this.userToken = userToken;
    }

}
