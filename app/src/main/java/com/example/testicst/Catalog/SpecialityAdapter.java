package com.example.testicst.Catalog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testicst.R;

import java.util.List;
public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.SpecialityVH> {

    private static final String TAG = "SpecialityAdapter";
    List<Speciality> specialityList;

    public SpecialityAdapter(List<Speciality> specialityList) {
        this.specialityList =specialityList;
    }

    @NonNull
    @Override
    public SpecialityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_information, parent, false);
        return new SpecialityVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  SpecialityVH holder, int position) {

        Speciality speciality = specialityList.get(position);
        holder.nameTextView.setText(speciality.getName());
        holder.examsTextView.setText(speciality.getExams());
        holder.pointsTextView.setText(speciality.getPoints());
        holder.placesTextView.setText(speciality.getPlaces());
     /*   holder.salaryTextView.setText(speciality.getSalary());
        holder.placesTextView.setText(speciality.getPlaces());*/
        if (speciality.getDescription() != "false")
        {
            holder.descriptionTextView.setVisibility(View.VISIBLE);
            holder.salaryTextView.setVisibility(View.VISIBLE);
            holder.professionsTextView.setVisibility(View.VISIBLE);
            holder.textView6.setVisibility(View.VISIBLE);
            holder.textView7.setVisibility(View.VISIBLE);
            holder.descriptionTextView.setText(speciality.getDescription());
            holder.salaryTextView.setText(speciality.getSalary());
            holder.professionsTextView.setText(speciality.getProfessions());
        }
        else
        {
            holder.descriptionTextView.setVisibility(View.GONE);
            holder.salaryTextView.setVisibility(View.GONE);
            holder.professionsTextView.setVisibility(View.GONE);
            holder.textView6.setVisibility(View.GONE);
            holder.textView7.setVisibility(View.GONE);
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
        TextView nameTextView, examsTextView, pointsTextView, placesTextView, descriptionTextView, professionsTextView, salaryTextView, textProfessions,textSalary,textView6,textView7 ;

        public SpecialityVH(@NonNull final View itemView) {
            super(itemView);

            textView6 = itemView.findViewById(R.id.textView6);
            textView7= itemView.findViewById(R.id.textView7);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            professionsTextView = itemView.findViewById(R.id.professionsTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            examsTextView = itemView.findViewById(R.id.pointsTextView);
            pointsTextView = itemView.findViewById(R.id.placesTextView);
            placesTextView = itemView.findViewById(R.id.examsTextView);
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
