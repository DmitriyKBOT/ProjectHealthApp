package com.example.dmitriyk_project1;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmitriyk_project1.Adapters.TodolistAdapter;
import com.example.dmitriyk_project1.Models.Task;
import com.example.dmitriyk_project1.Models.User;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private TextView txtVN;
    private TextView txtVDO;
    private ImageView imVA;
    private RecyclerView recyclerView;
    private TodolistAdapter adapter;
    private ArrayList<Task> tasks = new ArrayList<>();
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtVN = findViewById(R.id.txtVN);
        txtVDO = findViewById(R.id.txtVDO);
        imVA = findViewById(R.id.imVA);
        recyclerView = findViewById(R.id.recVList);
        user = (User) getIntent().getSerializableExtra("User");
        txtVN.setText(user.Name);
        Log.e("sfsdf",  user.tasks.size() + "");
        adapter = new TodolistAdapter(this, user.tasks, new TodolistAdapter.OnStateClickListener() {
            @Override
            public void OnClick(Task task) {

            }
        }, user);
        Log.e("ddddddddddddddddddd", adapter.getItemCount() + "");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.e("dddddddddddssssssssdddd", adapter.getItemCount() + "");
    }
}