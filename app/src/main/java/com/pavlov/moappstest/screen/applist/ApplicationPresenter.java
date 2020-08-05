package com.pavlov.moappstest.screen.applist;

import com.pavlov.moappstest.api.ApiFactory;
import com.pavlov.moappstest.api.ApiService;
import com.pavlov.moappstest.pojo.Application;
import com.pavlov.moappstest.pojo.ApplicationPostBody;
import com.pavlov.moappstest.pojo.ApplicationResponse;
import com.pavlov.moappstest.pojo.User;
import com.pavlov.moappstest.screen.login.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import com.pavlov.moappstest.util.Util;

public class ApplicationPresenter {
    private CompositeDisposable compositeDisposable;
    private ApplicationView view;

    public ApplicationPresenter(ApplicationView view) {
        this.view = view;
    }

    public void getApplicationList(String userToken ){
        compositeDisposable = new CompositeDisposable();
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        ApplicationPostBody body = new ApplicationPostBody(
                Util.APPLICATION_SKIP_PARAMETERS,
                Util.APPLICATION_TAKE_PARAMETERS,
                Util.APPLICATION_OS_TYPE_PARAMETERS,
                userToken);

        Disposable disposable = apiService.getApplication(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApplicationResponse>() {
                    @Override
                    public void accept(ApplicationResponse response) throws Exception {
                        if(response.getData().size()>0)
                            view.getApplicationList(response);
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
