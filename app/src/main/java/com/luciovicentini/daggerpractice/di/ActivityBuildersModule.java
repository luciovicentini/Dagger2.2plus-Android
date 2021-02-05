package com.luciovicentini.daggerpractice.di;

import com.luciovicentini.daggerpractice.di.auth.AuthScope;
import com.luciovicentini.daggerpractice.di.auth.AuthViewModelsModule;
import com.luciovicentini.daggerpractice.di.main.MainFragmentBuildersModule;
import com.luciovicentini.daggerpractice.di.main.MainModule;
import com.luciovicentini.daggerpractice.di.main.MainScope;
import com.luciovicentini.daggerpractice.di.main.MainViewModelsModule;
import com.luciovicentini.daggerpractice.di.auth.AuthModule;
import com.luciovicentini.daggerpractice.ui.auth.AuthActivity;
import com.luciovicentini.daggerpractice.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
