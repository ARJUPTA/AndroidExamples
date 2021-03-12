package com.arjupta.daggerdemo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

@Singleton
public class SessionManager {
    private final MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    SessionManager(){}

    public void authenticateWithId(LiveData<AuthResource<User>> source){
        if(cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User)null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logout(){
        Log.d(TAG, "logout: logging out");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getUser(){
        return cachedUser;
    }
}
