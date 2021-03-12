package com.arjupta.daggerdemo.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;
    @Inject
    public AuthViewModel(AuthApi authApi){
        Log.d(TAG, "AuthViewModel: it was injected");
        this.authApi = authApi;
        
        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d(TAG, "onNext: "+user.getEmail());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
