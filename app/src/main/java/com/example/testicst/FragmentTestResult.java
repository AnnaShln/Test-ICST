package com.example.testicst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class FragmentTestResult extends Fragment {

    TextView mGroup;
    int[] points;   //Баллы

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_results, container, false);

        Button button = view.findViewById(R.id.button_pass_test_again);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Question1.class);
                startActivity(intent);
            }
        });

        SharedPreferences sPref;
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        int idDirection = sPref.getInt(MainActivity.SAVED_RESULTS,  1);

        mGroup = view.findViewById(R.id.results_group);

        String recommendedGroup = getStringResourceByName("Group" + Integer.toString(idDirection));

        mGroup.setText(recommendedGroup);

        return view;
    }

    public String getStringResourceByName(String aString) {
        String packageName = getActivity().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
}
