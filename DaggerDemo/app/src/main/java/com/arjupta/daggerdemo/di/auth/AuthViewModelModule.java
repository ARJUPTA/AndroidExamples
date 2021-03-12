package com.arjupta.daggerdemo.di.auth;

import com.arjupta.daggerdemo.di.ViewModelKey;
import com.arjupta.daggerdemo.ui.auth.AuthViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
