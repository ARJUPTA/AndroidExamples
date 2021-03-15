package com.arjupta.daggerdemo.di.main;

import androidx.lifecycle.ViewModel;

import com.arjupta.daggerdemo.di.ViewModelKey;
import com.arjupta.daggerdemo.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

}
