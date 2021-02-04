package com.luciovicentini.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.luciovicentini.daggerpractice.SessionManager;
import com.luciovicentini.daggerpractice.models.User;
import com.luciovicentini.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;

        Log.d(TAG, "AuthViewModel: viewmodel is working");
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: Attempting to login userId = " + userId);
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @NonNull
                            @Override
                            public User apply(@NonNull Throwable throwable) throws Exception {
                                User user = new User();
                                user.setId(-1);
                                return user;
                            }
                        })
                        .map((Function<User, AuthResource<User>>) user -> {
                            if (user.getId() == -1) {
                                return AuthResource.error("Could not authenticate", null);
                            }
                            return AuthResource.authenticated(user);
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
