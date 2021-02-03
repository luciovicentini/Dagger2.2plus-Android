package com.luciovicentini.daggerpractice.di;

import com.luciovicentini.daggerpractice.di.auth.AuthViewModelsModule;
import com.luciovicentini.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();
}
