package com.example.testicst;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
public class Handbook extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Group> groupList;

    private void initRecyclerView() {
        GroupAdapter groupAdapter = new GroupAdapter(groupList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handbook);
        recyclerView = findViewById(R.id.recyclerView);
        initData();
        initRecyclerView();
    }

    private void initData(){
        groupList = new ArrayList<>();
        groupList.add(new Group(getString(R.string.Group1), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
        groupList.add(new Group(getString(R.string.Group2), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
        groupList.add(new Group(getString(R.string.Group3), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
    }
}
