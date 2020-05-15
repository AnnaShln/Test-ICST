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
                specialityList.add(new Speciality("Тайтл", "Прикладная информатика",
                        "exams", "point", "places", 5));
                break;
            case 2:
                specialityList.add(new Speciality("Тайтл", "Информационные системы и базы данных",
                        "exams", "point", "places", 5));
                break;
            default:
                specialityList.add(new Speciality("Тайтл2", "Тупой матабес",
                        "exams", "point", "places", 5));
        }
    }
}
/* OLD CODE --------------------------------------------
private void initData_Group1() {
    specialityList = new ArrayList<>();
    specialityList.add(new Speciality(getString(R.string.Group1),
            getString(R.string.Group1_description),
            getString(R.string.Group1_professions), getString(R.string.Group1_salary),
            getString(R.string.Group1_name1), getString(R.string.Exam_MIR), getString(R.string.Group1_name1_points), getString(R.string.Group1_name1_places)));
    specialityList.add(new Speciality(getString(R.string.Group1),
            getString(R.string.Group1_name2), getString(R.string.Exam_MIR), getString(R.string.Group1_name2_points), getString(R.string.Group1_name2_places)));
    specialityList.add(new Speciality(getString(R.string.Group1),
            getString(R.string.Group1_name3), getString(R.string.Exam_MFR), getString(R.string.Group1_name3_points), getString(R.string.Group1_name3_places)));
    specialityList.add(new Speciality(getString(R.string.Group1),
            getString(R.string.Group1_name4), getString(R.string.Exam_MIR), getString(R.string.Group1_name4_points), getString(R.string.Group1_name4_places)));
    specialityList.add(new Speciality(getString(R.string.Group1),
            getString(R.string.Group1_name5), getString(R.string.Exam_MIR), getString(R.string.Group1_name5_points), getString(R.string.Group1_name5_places)));
}

/*switch (number_of_group)
        {
            case 1:  initData_Group1();
            case 2:  initData_Group2();
            case 3:  initData_Group3();
            case 4:  initData_Group4();
            case 5:  initData_Group5();
            case 6:  initData_Group6();
            case 7:  initData_Group7();
            case 8:  initData_Group8();
        }
*/

//initData_Group1();

       /*  initData_Group2();
          initData_Group3();
          initData_Group4();
          initData_Group5();
          initData_Group6();
          initData_Group7();
          initData_Group8();*/

/*  private void initData_Group2() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality(getString(R.string.Group2_name1), getString(R.string.Exam_MIR), getString(R.string.Group2_name1_points), getString(R.string.Group2_name1_places)));
        specialityList.add(new Speciality(getString(R.string.Group2_name2), getString(R.string.Exam_MIR), getString(R.string.Group2_name2_points), getString(R.string.Group2_name2_places)));
        specialityList.add(new Speciality(getString(R.string.Group2_name3), getString(R.string.Exam_MIR), getString(R.string.Group2_name3_points), getString(R.string.Group2_name3_places)));
    }
    private void initData_Group3() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР"));

    }
    private void initData_Group4() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР"));

    }
    private void initData_Group5() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР"));

    }
    private void initData_Group6() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР"));

    }
    private void initData_Group7() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР"));

    }
    private void initData_Group8() {
        specialityList = new ArrayList<>();
        specialityList.add(new Speciality("Прикладная информатика", "24", "275", "МИР")); */