package com.luciovicentini.daggerpractice.di;

import com.luciovicentini.daggerpractice.di.auth.AuthViewModelsModule;
import com.luciovicentini.daggerpractice.network.auth.AuthModule;
import com.luciovicentini.daggerpractice.ui.auth.AuthActivity;
import com.luciovicentini.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
