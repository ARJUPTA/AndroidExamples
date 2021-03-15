package com.arjupta.daggerdemo.di;

import com.arjupta.daggerdemo.di.auth.AuthModule;
import com.arjupta.daggerdemo.di.auth.AuthViewModelModule;
import com.arjupta.daggerdemo.di.main.MainFragmentsBuilderModule;
import com.arjupta.daggerdemo.di.main.MainModule;
import com.arjupta.daggerdemo.di.main.MainViewModelsModule;
import com.arjupta.daggerdemo.ui.auth.AuthActivity;
import com.arjupta.daggerdemo.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    public abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainViewModelsModule.class,
                    MainFragmentsBuilderModule.class,
                    MainModule.class
            }
    )
    public abstract MainActivity contributeMainActivity();
}
