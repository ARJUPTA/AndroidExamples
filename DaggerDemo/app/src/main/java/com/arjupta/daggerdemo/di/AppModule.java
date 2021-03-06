package com.arjupta.daggerdemo.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.arjupta.daggerdemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Provides
    static RequestManager requestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable getDrawable(Application application){
        return ContextCompat.getDrawable(application,R.drawable.logo);
    }
}
