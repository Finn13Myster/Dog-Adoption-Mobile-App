package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.frontend.model.Dog;
import com.example.frontend.retrofit.DogApi;
import com.example.frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsAdminViewForUpdate extends AppCompatActivity {

    List<Dog> allDogs;
    ArrayList<Dog> allDogsArrayList = new ArrayList<>();
    int selectedPosition;
    RetrofitService retrofitService = new RetrofitService();
    DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_admin_view_for_update);

        ListView updateListView = findViewById(R.id.updateListView);
        Button updateButton = findViewById(R.id.updateDogInformationButton);

        updateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                updateListView.setItemChecked(position, true);
                updateButton.setEnabled(true);
            }
        });

        dogApi.getAllDogs().enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if (response.isSuccessful()) {
                    allDogs = response.body();
                    allDogsArrayList.addAll(allDogs);
                    Toast.makeText(getBaseContext(), "Dogs Added", Toast.LENGTH_SHORT).show();

                    // Notify the adapter that the data has changed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DogAdapter dogAdapter = new DogAdapter(getApplicationContext(), allDogsArrayList);
                            updateListView.setAdapter(dogAdapter);
                            dogAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "Failed to get dogs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dog dogToBeUpdated = allDogsArrayList.get(selectedPosition);
                Intent intent = new Intent(getApplicationContext(), AsAdminViewForUpdateForm.class);
                intent.putExtra("id", String.valueOf(dogToBeUpdated.getId()));
                intent.putExtra("name", String.valueOf(dogToBeUpdated.getName()));
                intent.putExtra("imageLink", String.valueOf(dogToBeUpdated.getImageLink()));
                intent.putExtra("adoptionStatus", String.valueOf(dogToBeUpdated.getAdoptionStatus()));
                intent.putExtra("adoptedBy", String.valueOf(dogToBeUpdated.getAdoptedBy()));
                startActivity(intent);
            }
        });
    }
}