package com.example.testicst;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        mGroup = view.findViewById(R.id.results_group);

        Intent intent = getActivity().getIntent();
        //Получаем баллы с теста
        points = intent.getIntArrayExtra("POINTS");

        String recommendedGroup = getGroupFromPoints(points);

        mGroup.setText(recommendedGroup);

        return view;
    }

    private String getGroupFromPoints(int[] points) {
        int max = points[0];
        int maxIndex = 0;
        //Находим группу с максимальным баллом (из равных выбирается первый)
        for (int i = 1; i < points.length; i++){
            if (points[i] > max) {
                max = points[i];
                maxIndex = i;
            }
        }

        return getStringResourceByName("Group" + Integer.toString(maxIndex + 1));
    }

    public String getStringResourceByName(String aString) {
        String packageName = getActivity().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }
}
