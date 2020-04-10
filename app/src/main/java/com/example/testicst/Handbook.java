package com.example.testicst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
public class Handbook extends Fragment {

    RecyclerView recyclerView;
    List<Group> groupList;

    private void initRecyclerView() {
        GroupAdapter groupAdapter = new GroupAdapter(groupList);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_handbook, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        initRecyclerView();
        return rootView;
    }

    private void initData(){
        groupList = new ArrayList<>();
        groupList.add(new Group(getString(R.string.Group1), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
        groupList.add(new Group(getString(R.string.Group2), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
        groupList.add(new Group(getString(R.string.Group3), getString(R.string.Group1_description), getString(R.string.Group1_professions)));
    }
}
