package com.example.frontend.retrofit;

import com.example.frontend.model.User;

import java.util.List;

import retrofit2.*;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/all-users")
    Call<List<User>> getAllUsers();

    @POST("/add-user")
    Call<User> addUser(@Body User user);

    @PUT("/update-user")
    Call<User> updateUser(@Body User user);

    @DELETE("/users/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
