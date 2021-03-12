package com.arjupta.daggerdemo.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.arjupta.daggerdemo.SessionManager;
import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

public class AuthViewModel extends ViewModel {

    private AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager){
        Log.d(TAG, "AuthViewModel: it was injected");
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticate(int Id){
        Log.d(TAG, "authenticate: Trying to login");
        sessionManager.authenticateWithId(queryUserId(Id));
    }

    private LiveData<AuthResource<User>> queryUserId(int Id){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(Id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @NonNull
                            @Override
                            public User apply(@NonNull Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @NonNull
                            @Override
                            public AuthResource<User> apply(@NonNull User user) throws Exception {
                                if(user.getId()==-1){
                                    return AuthResource.error("Couldn't Authenticate",(User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> getUser(){
        return sessionManager.getUser();
    }


}
