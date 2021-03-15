package com.arjupta.daggerdemo.di.main;

import com.arjupta.daggerdemo.ui.main.posts.PostsFragment;
import com.arjupta.daggerdemo.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
