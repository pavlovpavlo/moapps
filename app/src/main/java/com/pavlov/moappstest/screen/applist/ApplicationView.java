package com.pavlov.moappstest.screen.applist;

import com.pavlov.moappstest.pojo.Application;
import com.pavlov.moappstest.pojo.ApplicationResponse;
import com.pavlov.moappstest.pojo.User;

public interface ApplicationView {
    void getApplicationList(ApplicationResponse appList);
    void showError();
}
