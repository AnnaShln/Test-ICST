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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class FragmentTestResult extends Fragment {

    TextView mGroup;
    TextView mSpeciality;
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
        mSpeciality = view.findViewById(R.id.results_branches);

        String recommendedGroup = getStringResourceByName("Group" + Integer.toString(idDirection));

        mSpeciality.setText(recommendedSpeciality(idDirection));
        mGroup.setText(recommendedGroup);

        return view;
    }

    public String getStringResourceByName(String aString) {
        String packageName = getActivity().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

   public String recommendedSpeciality(int idDirection) {
       String[] allSpecialities = getResources().getStringArray(R.array.allSpecialties);
       ArrayList<Integer> intArray2 = new ArrayList<>();
       switch (idDirection) {
           case 1:
               Collections.addAll(intArray2, 0, 1, 2, 3, 4);
               break;
           case 2:
               Collections.addAll(intArray2, 2, 3, 5);
               break;
           case 3:
               Collections.addAll(intArray2, 0, 1, 6, 7);
               break;
           case 4:
               Collections.addAll(intArray2, 0, 2, 3, 4, 5);
               break;
           case 5:
               Collections.addAll(intArray2, 5, 7);
               break;
           case 6:
               Collections.addAll(intArray2, 2);
               break;
           case 7:
               Collections.addAll(intArray2, 1, 6, 4);
               break;
           case 8:
               Collections.addAll(intArray2, 8, 9);
               break;
       }
       String recommendedS = "";
       for (int i = 0;i < intArray2.size(); i++)
       {
           recommendedS += allSpecialities[intArray2.get(i)] + "\n";
       }
       return recommendedS;
   }
}
