package com.example.testicst.Catalog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.testicst.MainActivity;
import com.example.testicst.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
public class Handbook extends Fragment {

    RecyclerView recyclerView;
    List<Group> groupList;

    private void initRecyclerView() {
        GroupAdapter groupAdapter = new GroupAdapter(groupList);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupAdapter);
    }

    @Nullable
    @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                            @NonNull Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_handbook,container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        initRecyclerView();
        SharedPreferences sPref;
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        int idDirection = sPref.getInt(MainActivity.SAVED_RESULTS,  -1);
        GroupAdapter.greenIs(idDirection);
        return rootView;
    }

    private void initData(){
        groupList = new ArrayList<>();
        groupList.add(new Group(getString(R.string.Group1),
                getString(R.string.Group1_description),
                getString(R.string.Group1_professions), getString(R.string.Group1_salary), 1));
        groupList.add(new Group(getString(R.string.Group2),
                getString(R.string.Group2_description),
                getString(R.string.Group1_professions), getString(R.string.Group2_salary), 2));
        groupList.add(new Group(getString(R.string.Group3),
                getString(R.string.Group3_description),
                getString(R.string.Group1_professions), getString(R.string.Group3_salary), 3));
        groupList.add(new Group(getString(R.string.Group4),
                getString(R.string.Group4_description),
                getString(R.string.Group1_professions), getString(R.string.Group4_salary), 4));
        groupList.add(new Group(getString(R.string.Group5),
                getString(R.string.Group5_description),
                getString(R.string.Group1_professions), getString(R.string.Group5_salary), 5));
        groupList.add(new Group(getString(R.string.Group6),
                getString(R.string.Group6_description),
                getString(R.string.Group1_professions), getString(R.string.Group6_salary), 6));
        groupList.add(new Group(getString(R.string.Group7),
                getString(R.string.Group7_description),
                getString(R.string.Group1_professions), getString(R.string.Group7_salary), 7));
        groupList.add(new Group(getString(R.string.Group8),
                getString(R.string.Group8_description),
                getString(R.string.Group1_professions), getString(R.string.Group8_salary), 8));

    }
}

