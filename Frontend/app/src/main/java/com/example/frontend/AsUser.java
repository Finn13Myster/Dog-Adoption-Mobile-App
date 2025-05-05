package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.model.User;
import com.example.frontend.retrofit.RetrofitService;
import com.example.frontend.retrofit.UserApi;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsUser extends AppCompatActivity {
    List<User> allUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_user_sign_up);

        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);
        EditText usernameInput = findViewById(R.id.usernameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        loginButton.setOnClickListener(view ->{
            User compare = new User(String.valueOf(usernameInput.getText()),
                    String.valueOf(passwordInput.getText()));

            userApi.getAllUsers().enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        allUsers = response.body();
                        for (User user: allUsers) {
                            if (compare.getUsername().equals(user.getUsername())) {
                                if (compare.getPassword().equals(user.getPassword())) {
                                    Toast.makeText(getBaseContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), AsUserView.class);
                                    intent.putExtra("loggedInUserID", String.valueOf(user.getId()));
                                    intent.putExtra("loggedInUserUsername", user.getUsername());
                                    intent.putExtra("loggedInUserPassword", user.getPassword());
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
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        });


        signUpButton.setOnClickListener(view ->{
            User user = new User(String.valueOf(usernameInput.getText()),
                    String.valueOf(passwordInput.getText()));

            userApi.addUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(getBaseContext(), "Save User!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Save Failed!", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(AsUser.class.getName()).log(Level.SEVERE, "Error");
                }
            });
        });
    }
}