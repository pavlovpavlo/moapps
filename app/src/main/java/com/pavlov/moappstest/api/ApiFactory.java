package com.pavlov.moappstest.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pavlov.moappstest.util.Util.BASE_URL;


public class ApiFactory {
    private static  ApiFactory apiFactory;
    private static Retrofit retrofit;

    public static ApiFactory getInstance(){
        if(apiFactory == null){
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    private ApiFactory(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

    }

    public  ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
