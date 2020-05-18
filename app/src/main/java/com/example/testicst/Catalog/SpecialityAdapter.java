package com.example.testicst.Catalog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testicst.MainActivity;
import com.example.testicst.R;
import java.util.List;
public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.SpecialityVH> {

    private static final String TAG = "SpecialityAdapter";
    private static Object Handbook;
    List<Speciality> specialityList;

    public SpecialityAdapter(List<Speciality> specialityList) {
        this.specialityList =specialityList;
    }

    @NonNull
    @Override
    public SpecialityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_information, parent, false);
        Context mContext = parent.getContext();
        return new SpecialityVH(view);
    }



    @Override
    public void onBindViewHolder(@NonNull  SpecialityVH holder, int position) {

        Speciality speciality = specialityList.get(position);
        holder.nameTextView.setText(speciality.getName());
        holder.examsTextView.setText(speciality.getExams());
        holder.pointsTextView.setText(speciality.getPoints());
        holder.placesTextView.setText(speciality.getPlaces());
        if (speciality.getDescription() != "false")
        {
            holder.descriptionTextView.setVisibility(View.VISIBLE);
            holder.salaryTextView.setVisibility(View.VISIBLE);
            holder.professionsTextView.setVisibility(View.VISIBLE);
            holder.textView6.setVisibility(View.VISIBLE);
            holder.textView7.setVisibility(View.VISIBLE);
            holder.titleTextView.setVisibility(View.VISIBLE);
            holder.descriptionTextView.setText(speciality.getDescription());
            holder.salaryTextView.setText(speciality.getSalary());
            holder.professionsTextView.setText(speciality.getProfessions());
            holder.titleTextView.setText(speciality.getTitle());
        }
        else
        {
            holder.descriptionTextView.setVisibility(View.GONE);
            holder.salaryTextView.setVisibility(View.GONE);
            holder.professionsTextView.setVisibility(View.GONE);
            holder.textView6.setVisibility(View.GONE);
            holder.textView7.setVisibility(View.GONE);
            holder.titleTextView.setVisibility(View.GONE);
        }
        boolean isExpanded = specialityList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }




    @Override
    public int getItemCount() {
        return specialityList.size();
    }

    class SpecialityVH extends RecyclerView.ViewHolder {

        private static final String TAG = "SpecialityVH";

        ConstraintLayout expandableLayout;
        TextView nameTextView, examsTextView, pointsTextView, placesTextView, descriptionTextView,
                professionsTextView, salaryTextView, titleTextView, textView6,textView7 ;

        public SpecialityVH(@NonNull final View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7= itemView.findViewById(R.id.textView7);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            professionsTextView = itemView.findViewById(R.id.professionsTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            examsTextView = itemView.findViewById(R.id.examsTextView);
            pointsTextView = itemView.findViewById(R.id.pointsTextView);
            placesTextView = itemView.findViewById(R.id.placesTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Speciality speciality = specialityList.get(getAdapterPosition());
                    speciality.setExpanded(!speciality.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
