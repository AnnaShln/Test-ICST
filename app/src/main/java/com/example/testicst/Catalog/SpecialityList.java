package com.example.testicst.Catalog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.testicst.R;

import java.util.ArrayList;
import java.util.List;

public class SpecialityList extends Fragment {

    RecyclerView recyclerView;
    List<Speciality> specialityList = new ArrayList<>();
    int idDirection;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_speciality,container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //initData(number_of_group);

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            idDirection = bundle.getInt(GroupAdapter.TAG);
        }
        else {
            idDirection = 1;
        }

        initData(idDirection);
        initRecyclerView();
        return rootView;
    }

   private void initRecyclerView() {
        SpecialityAdapter specialityAdapter = new SpecialityAdapter(specialityList);
        recyclerView.setAdapter(specialityAdapter);
    }


    private void initData(int idDirection)
    {
        switch (idDirection){
            case 1:
                specialityList.add(new Speciality(getString(R.string.Group1),getString(R.string.Group1_description),
                        getString(R.string.Group1_professions),getString(R.string.Group1_salary),
                        getString(R.string.Group1_name1), getString(R.string.Exam_MIR), getString(R.string.Group1_name1_points),
                        getString(R.string.Group1_name1_places),1));
                specialityList.add(new Speciality(getString(R.string.Group1),
                        getString(R.string.Group1_name2), getString(R.string.Exam_MIR),
                        getString(R.string.Group1_name2_points), getString(R.string.Group1_name2_places),1));
                specialityList.add(new Speciality(getString(R.string.Group1),
                        getString(R.string.Group1_name3), getString(R.string.Exam_MFR),
                        getString(R.string.Group1_name3_points), getString(R.string.Group1_name3_places),1));
                specialityList.add(new Speciality(getString(R.string.Group1),
                        getString(R.string.Group1_name4), getString(R.string.Exam_MIR),
                        getString(R.string.Group1_name4_points), getString(R.string.Group1_name4_places),1));
                specialityList.add(new Speciality(getString(R.string.Group1),
                        getString(R.string.Group1_name5), getString(R.string.Exam_MIR),
                        getString(R.string.Group1_name5_points), getString(R.string.Group1_name5_places),1));
                break;
            case 2:
                specialityList.add(new Speciality(getString(R.string.Group2),getString(R.string.Group2_description),
                        getString(R.string.Group2_professions),getString(R.string.Group2_salary),getString(R.string.Group2_name1), getString(R.string.Exam_MIR),
                        getString(R.string.Group2_name1_points), getString(R.string.Group2_name1_places),2));
                specialityList.add(new Speciality(getString(R.string.Group1),getString(R.string.Group2_name2), getString(R.string.Exam_MIR),
                        getString(R.string.Group2_name2_points), getString(R.string.Group2_name2_places),2));
                specialityList.add(new Speciality(getString(R.string.Group1),getString(R.string.Group2_name3), getString(R.string.Exam_MIR),
                        getString(R.string.Group2_name3_points), getString(R.string.Group2_name3_places),2));
                break;

            case 3:
                specialityList.add(new Speciality(getString(R.string.Group3),getString(R.string.Group3_description),
                        getString(R.string.Group3_professions),getString(R.string.Group3_salary),getString(R.string.Group3_name1), getString(R.string.Exam_MIR),
                        getString(R.string.Group3_name1_points), getString(R.string.Group3_name1_places),3));
                specialityList.add(new Speciality(getString(R.string.Group3),getString(R.string.Group3_name2), getString(R.string.Exam_MIR),
                        getString(R.string.Group3_name2_points), getString(R.string.Group3_name2_places),3));
                specialityList.add(new Speciality(getString(R.string.Group3),getString(R.string.Group3_name3), getString(R.string.Exam_MIR),
                        getString(R.string.Group3_name3_points), getString(R.string.Group3_name3_places),3));
                specialityList.add(new Speciality(getString(R.string.Group3),getString(R.string.Group2_name3), getString(R.string.Exam_MIR),
                        getString(R.string.Group3_name4_points), getString(R.string.Group3_name3_places),3));

                break;
            case 4:
                specialityList.add(new Speciality(getString(R.string.Group4),getString(R.string.Group4_description),
                        getString(R.string.Group4_professions),getString(R.string.Group4_salary),getString(R.string.Group4_name1), getString(R.string.Exam_MIR),
                        getString(R.string.Group4_name1_points), getString(R.string.Group4_name1_places),4));
                specialityList.add(new Speciality(getString(R.string.Group4),getString(R.string.Group4_name2), getString(R.string.Exam_MIR),
                        getString(R.string.Group4_name2_points), getString(R.string.Group4_name2_places),4));
                specialityList.add(new Speciality(getString(R.string.Group4),getString(R.string.Group4_name3), getString(R.string.Exam_MIR),
                        getString(R.string.Group4_name3_points), getString(R.string.Group4_name3_places),4));
                specialityList.add(new Speciality(getString(R.string.Group4),getString(R.string.Group4_name4), getString(R.string.Exam_MIR),
                        getString(R.string.Group4_name4_points), getString(R.string.Group4_name4_places),4));
                specialityList.add(new Speciality(getString(R.string.Group4),getString(R.string.Group4_name5), getString(R.string.Exam_MIR),
                        getString(R.string.Group4_name5_points), getString(R.string.Group4_name5_places),4));

                break;
            case 5:
                specialityList.add(new Speciality(getString(R.string.Group5),getString(R.string.Group5_description),
                        getString(R.string.Group5_professions),getString(R.string.Group5_salary),getString(R.string.Group5_name1), getString(R.string.Exam_MFR),
                        getString(R.string.Group5_name1_points), getString(R.string.Group5_name1_places),5));
                specialityList.add(new Speciality(getString(R.string.Group5),getString(R.string.Group5_name2), getString(R.string.Exam_MFR),
                        getString(R.string.Group5_name2_points), getString(R.string.Group5_name2_places),5));


                break;
            case 6:
                specialityList.add(new Speciality(getString(R.string.Group6),getString(R.string.Group6_description),
                        getString(R.string.Group6_professions),getString(R.string.Group6_salary),getString(R.string.Group6_name1), getString(R.string.Exam_MIR),
                        getString(R.string.Group6_name1_points), getString(R.string.Group6_name1_places),6));

                break;
            case 7:
                specialityList.add(new Speciality(getString(R.string.Group7),getString(R.string.Group7_description),
                        getString(R.string.Group7_professions),getString(R.string.Group7_salary),getString(R.string.Group7_name1), getString(R.string.Exam_MIR),
                        getString(R.string.Group7_name1_points), getString(R.string.Group7_name1_places),7));
                specialityList.add(new Speciality(getString(R.string.Group7),getString(R.string.Group7_name2), getString(R.string.Exam_MIR),
                        getString(R.string.Group7_name2_points), getString(R.string.Group7_name2_places),7));
                specialityList.add(new Speciality(getString(R.string.Group7),getString(R.string.Group7_name3), getString(R.string.Exam_MIR),
                        getString(R.string.Group7_name3_points), getString(R.string.Group7_name3_places),7));

                break;
            case 8:
                specialityList.add(new Speciality(getString(R.string.Group8),getString(R.string.Group8_description),
                        getString(R.string.Group8_professions),getString(R.string.Group8_salary),getString(R.string.Group8_name1), getString(R.string.Exam_MFR),
                        getString(R.string.Group8_name1_points), getString(R.string.Group8_name1_places),8));
                specialityList.add(new Speciality(getString(R.string.Group8),getString(R.string.Group8_name2), getString(R.string.Exam_MFR),
                        getString(R.string.Group8_name2_points), getString(R.string.Group8_name2_places),8));
                break;

        }
    }
}