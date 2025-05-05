package com.example.frontend.retrofit;

import com.example.frontend.model.Admin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminApi {
    @GET("/all-admins")
    Call<List<Admin>> getAllAdmins();

    @POST("/add-admin")
    Call<Admin> addAdmin(@Body Admin admin);

    @PUT("/update-admin")
    Call<Admin> updateAdmin(@Body Admin admin);

    @DELETE("/admins/{id}")
    Call<Admin> deleteAdmin(@Path("id") int id);
}
