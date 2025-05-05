package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.model.Dog;
import com.example.frontend.model.User;
import com.example.frontend.retrofit.DogApi;
import com.example.frontend.retrofit.RetrofitService;
import com.example.frontend.retrofit.UserApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsAdminViewForAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_admin_view_for_add);

        EditText dogName = findViewById(R.id.addDogName);
        EditText dogImageLink = findViewById(R.id.addDogImageLink);
        Button addDog = findViewById(R.id.addDog);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        addDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dog dog = new Dog();
                dog.setName(String.valueOf(dogName.getText()));
                dog.setImageLink(String.valueOf(dogImageLink.getText()));

                dogApi.addDog(dog).enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        Toast.makeText(getBaseContext(), "Dog Added Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Failed to add dog", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(AsUser.class.getName()).log(Level.SEVERE, "Error");
                    }
                });
            }
        });
    }
}