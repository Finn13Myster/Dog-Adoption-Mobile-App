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
import com.example.frontend.model.User;
import com.example.frontend.retrofit.DogApi;
import com.example.frontend.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsUserView extends AppCompatActivity {
    List<Dog> allDogs;
    ArrayList<Dog> allDogsArrayList = new ArrayList<>();
    int selectedPosition;
    RetrofitService retrofitService = new RetrofitService();
    DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_user_view);

        Intent intent = getIntent();
        User loggedInUser = new User(intent.getStringExtra("loggedInUserUsername"),
                intent.getStringExtra("loggedInUserPassword"));
        loggedInUser.setId(Integer.parseInt(intent.getStringExtra("loggedInUserID")));
        Button adoptButton = findViewById(R.id.adoptButton);
        ListView dogListView = findViewById(R.id.dogListView);

        adoptButton.setEnabled(false);

        dogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                dogListView.setItemChecked(position, true);
                adoptButton.setEnabled(true);
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
                            dogListView.setAdapter(dogAdapter);
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

        adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dog dogToBeUpdated = allDogsArrayList.get(selectedPosition);
                dogToBeUpdated.setAdoptionStatus("Adopted");
                dogToBeUpdated.setAdoptedBy(loggedInUser.getUsername());
                dogApi.updateDog(dogToBeUpdated).enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        Toast.makeText(getBaseContext(), "Dog Updated!", Toast.LENGTH_SHORT).show();
                        dogApi.getAllDogs().enqueue(new Callback<List<Dog>>() {
                            @Override
                            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                                if (response.isSuccessful()) {
                                    allDogs = response.body();
                                    allDogsArrayList.clear();
                                    allDogsArrayList.addAll(allDogs);
                                    Toast.makeText(getBaseContext(), "Dogs Added", Toast.LENGTH_SHORT).show();

                                    // Notify the adapter that the data has changed
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            DogAdapter dogAdapter = new DogAdapter(getApplicationContext(), allDogsArrayList);
                                            dogListView.setAdapter(dogAdapter);
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
                    }

                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Dog Update Failed!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(AsUser.class.getName()).log(Level.SEVERE, "Error");
                    }
                });;
                Toast.makeText(getBaseContext(), "Dop Adopted " + loggedInUser.getUsername() + " + " + dogToBeUpdated.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
