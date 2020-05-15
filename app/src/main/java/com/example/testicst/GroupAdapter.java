package com.example.testicst;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupVH> {

    private static final String TAG = "GroupAdapter";
    List<Group> GroupList;

    public GroupAdapter(List<Group> groupList) {
        this.GroupList = groupList;
    }

    @NonNull
    @Override
    public GroupVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.handbook_list_of_groups, parent, false);
        return new GroupVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupVH holder, int position) {

        Group Group = GroupList.get(position);
        holder.TextView_title.setText(Group.getTitle());
   }

    @Override
    public int getItemCount() {
        return GroupList.size();
    }

    class GroupVH extends RecyclerView.ViewHolder {

        private static final String TAG = "GroupVH";

        ConstraintLayout expandableLayout;
        TextView TextView_title;

        public GroupVH(@NonNull final View itemView) {
            super(itemView);

            TextView_title = itemView.findViewById(R.id.TextView_title);
          /*  TextView_description = itemView.findViewById(R.id.TextView_description);
            TextView_professions_f= itemView.findViewById(R.id.TextView_professions_f);
            expandableLayout = itemView.findViewById(R.id.constraintLayout);*/


            TextView_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Group Group = GroupList.get(getAdapterPosition());
                    Group.setExpanded(!Group.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
