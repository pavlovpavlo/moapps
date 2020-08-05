package com.pavlov.moappstest.screen.login;

import com.pavlov.moappstest.pojo.User;

public interface LoginView{
    void setUser(User login);
    void showError();
}
