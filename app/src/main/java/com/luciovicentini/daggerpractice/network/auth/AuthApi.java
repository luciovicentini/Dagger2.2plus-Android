package com.luciovicentini.daggerpractice.network.auth;

import com.luciovicentini.daggerpractice.models.User;
import com.luciovicentini.daggerpractice.ui.auth.AuthResource;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("/users/{id}")
    Flowable<User> getUser(@Path("id") int userId);
}
