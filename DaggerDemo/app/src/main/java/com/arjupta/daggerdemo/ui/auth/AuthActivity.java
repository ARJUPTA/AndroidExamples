package com.arjupta.daggerdemo.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.arjupta.daggerdemo.R;
import com.arjupta.daggerdemo.model.User;
import com.arjupta.daggerdemo.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import static com.arjupta.daggerdemo.BaseApplication.TAG;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener{

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private EditText userId;

    private AuthViewModel viewModel;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    private void subscribeObservers(){
        viewModel.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource!=null){
                    switch (userAuthResource.status){
                        case LOADING: {
                            showProgress(true);
                            break;
                        }
                        case AUTHENTICATED: {
                            showProgress(false);
                            Toast.makeText(AuthActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case ERROR: {
                            showProgress(false);
                            Toast.makeText(AuthActivity.this,"Login Unsuccessful. Did you entered a number between 1 and 10?",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgress(false);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void showProgress(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticate(Integer.parseInt(userId.getText().toString()));
    }
}