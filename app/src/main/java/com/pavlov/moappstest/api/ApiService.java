package com.pavlov.moappstest.api;

import com.pavlov.moappstest.pojo.ApplicationPostBody;
import com.pavlov.moappstest.pojo.ApplicationResponse;
import com.pavlov.moappstest.pojo.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.pavlov.moappstest.util.Util.APPLICATION_LIST_RESPONSE;
import static com.pavlov.moappstest.util.Util.LOGIN_RESPONSE;

public interface ApiService {
    @POST(LOGIN_RESPONSE)
    Observable<User> authUser(@Body User user);

    @POST(APPLICATION_LIST_RESPONSE)
    Observable<ApplicationResponse> getApplication(@Body ApplicationPostBody body);
}
