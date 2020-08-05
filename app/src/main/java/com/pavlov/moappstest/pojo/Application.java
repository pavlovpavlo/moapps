package com.pavlov.moappstest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Application {
    @SerializedName("applicationToken")
    @Expose
    private String applicationToken;
    @SerializedName("isPayment")
    @Expose
    private boolean isPayment;
    @SerializedName("applicationStatus")
    @Expose
    private boolean applicationStatus;
    @SerializedName("applicationName")
    @Expose
    private String applicationName;
    @SerializedName("endOfPayment")
    @Expose
    private Object endOfPayment;
    @SerializedName("applicationIcoUrl")
    @Expose
    private String applicationIcoUrl;
    @SerializedName("applicationUrl")
    @Expose
    private String applicationUrl;

    public String getApplicationToken() {
        return applicationToken;
    }

    public boolean isPayment() {
        return isPayment;
    }

    public boolean isApplicationStatus() {
        return applicationStatus;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public Object getEndOfPayment() {
        return endOfPayment;
    }

    public String getApplicationIcoUrl() {
        return applicationIcoUrl;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }
}
