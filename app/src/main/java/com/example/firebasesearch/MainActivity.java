package com.example.firebasesearch;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Map <String, Object> doctors = new HashMap<>();
    Map <String, Object> detailsOfEachDoctor = new HashMap<>();
    ArrayList<String> locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Query query = FirebaseDatabase.getInstance().getReference("Doctors");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctors = (Map) snapshot.getValue();

                for (Map.Entry<String, Object> entryL1 : doctors.entrySet()) {
                    detailsOfEachDoctor = (Map) entryL1.getValue();
                    locations = (ArrayList<String>) detailsOfEachDoctor.get("Locations");

                    Log.d(TAG, String.format("%s - %s", entryL1.getKey(), locations));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}