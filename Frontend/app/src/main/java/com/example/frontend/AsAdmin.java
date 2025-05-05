package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.model.Admin;
import com.example.frontend.model.User;
import com.example.frontend.retrofit.AdminApi;
import com.example.frontend.retrofit.RetrofitService;
import com.example.frontend.retrofit.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsAdmin extends AppCompatActivity {
    List<Admin> allAdmins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_admin);

        EditText usernameInput = findViewById(R.id.usernameInputAsAdmin);
        EditText passwordInput = findViewById(R.id.passwordInputAsAdmin);
        Button loginAsAdmin = findViewById(R.id.loginAsAdmin);

        RetrofitService retrofitService = new RetrofitService();
        AdminApi adminApi = retrofitService.getRetrofit().create(AdminApi.class);

        loginAsAdmin.setOnClickListener(view ->{
            User compare = new User(String.valueOf(usernameInput.getText()),
                    String.valueOf(passwordInput.getText()));

            adminApi.getAllAdmins().enqueue(new Callback<List<Admin>>() {
                @Override
                public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                    if (response.isSuccessful()) {
                        allAdmins = response.body();
                        for (Admin admin: allAdmins) {
                            if (compare.getUsername().equals(admin.getUsername())) {
                                if (compare.getPassword().equals(admin.getPassword())) {
                                    Toast.makeText(getBaseContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AsAdminView.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getBaseContext(), "Incorrect Password.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    } else {
                        Toast.makeText(getBaseContext(), "Failed to get users", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Admin>> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Test 2.2", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getBaseContext(), "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}