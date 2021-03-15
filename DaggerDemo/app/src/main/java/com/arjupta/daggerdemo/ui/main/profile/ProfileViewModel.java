package com.arjupta.daggerdemo.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arjupta.daggerdemo.SessionManager;
import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.ui.auth.AuthResource;

import javax.inject.Inject;
import static com.arjupta.daggerdemo.BaseApplication.TAG;

public class ProfileViewModel extends ViewModel {

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: was injected");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getUser();
    }
}
