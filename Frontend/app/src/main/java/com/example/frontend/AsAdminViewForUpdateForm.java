package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.model.Dog;
import com.example.frontend.retrofit.DogApi;
import com.example.frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsAdminViewForUpdateForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_admin_view_for_update_form);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        EditText formDogName = findViewById(R.id.formDogName);
        EditText formDogImageLink = findViewById(R.id.formDogImageLink);
        EditText formDogAdoptionStatus = findViewById(R.id.formDogAdoptionStatus);
        EditText formDogAdoptedBy = findViewById(R.id.formDogAdoptedBy);
        Button updateButton = findViewById(R.id.formUpdateButton);

        Intent intent = getIntent();
        Dog dogToBeUpdated = new Dog(
            Integer.parseInt(intent.getStringExtra("id")),
            intent.getStringExtra("name"),
            intent.getStringExtra("image link"),
            intent.getStringExtra("adoptionStatus"),
            intent.getStringExtra("adoptedBy")
        );

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(formDogName.getText()).trim().length() > 0){
                    dogToBeUpdated.setName(String.valueOf(formDogName.getText()));
                }
                if (String.valueOf(formDogImageLink.getText()).trim().length() > 0){
                    dogToBeUpdated.setImageLink(String.valueOf(formDogImageLink.getText()));
                }
                if (String.valueOf(formDogAdoptionStatus.getText()).trim().length() > 0){
                    dogToBeUpdated.setAdoptionStatus(String.valueOf(formDogAdoptionStatus.getText()));
                }
                if (String.valueOf(formDogAdoptedBy.getText()).trim().length() > 0){
                    dogToBeUpdated.setAdoptedBy(String.valueOf(formDogAdoptedBy.getText()));
                }

                dogApi.updateDog(dogToBeUpdated).enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        Toast.makeText(getBaseContext(), "Dog Information Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Dog Information Failed to be Updated", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }
}