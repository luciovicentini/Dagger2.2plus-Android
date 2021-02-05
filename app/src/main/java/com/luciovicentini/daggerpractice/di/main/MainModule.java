package com.luciovicentini.daggerpractice.di.main;

import com.luciovicentini.daggerpractice.network.main.MainApi;
import com.luciovicentini.daggerpractice.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApi providesMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter provideAdapter() {
        return new PostsRecyclerAdapter();
    }
}
