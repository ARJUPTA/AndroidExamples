package com.arjupta.daggerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.ui.auth.AuthActivity;
import com.arjupta.daggerdemo.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import static com.arjupta.daggerdemo.BaseApplication.TAG;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers(){
        sessionManager.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    if(userAuthResource.status.equals(AuthResource.AuthStatus.NOT_AUTHENTICATED)){
                        navigateToLogin();
                        Log.d(TAG, "onChanged: navigated to AuthActivity");
                    }
                }
            }
        });
    }

    private void navigateToLogin(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
