package com.arjupta.daggerdemo.di.auth;

import com.arjupta.daggerdemo.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {
    @Provides
    static AuthApi provideApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}
