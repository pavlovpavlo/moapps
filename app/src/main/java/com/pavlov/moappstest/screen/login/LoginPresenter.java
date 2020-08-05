package com.pavlov.moappstest.screen.login;

import com.pavlov.moappstest.api.ApiFactory;
import com.pavlov.moappstest.api.ApiService;
import com.pavlov.moappstest.pojo.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private CompositeDisposable compositeDisposable;
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void authUser(String userNick, String password){
        compositeDisposable = new CompositeDisposable();
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        User user = new User(userNick, password);

        Disposable disposable = apiService.authUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User response) throws Exception {
                        if(!response.getData().equals("null"))
                            view.setUser(response);
                        else
                            view.showError();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable(){
        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }

}
