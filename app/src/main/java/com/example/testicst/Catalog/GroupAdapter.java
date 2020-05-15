package com.example.testicst.Catalog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testicst.Catalog.Group;
import com.example.testicst.R;

import java.util.List;
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupVH> {

    FragmentManager myFragmentManager;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*myFragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = myFragmentManager
                        .beginTransaction();
                fragmentTransaction.add(R.id.container, SpecialityList);
                fragmentTransaction.commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return GroupList.size();
    }

    class GroupVH extends RecyclerView.ViewHolder {

        private static final String TAG = "GroupVH";

        TextView TextView_title;

        public GroupVH(@NonNull final View itemView) {
            super(itemView);

            TextView_title = itemView.findViewById(R.id.TextView_title);
            TextView_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //   Group Group = GroupList.get(getAdapterPosition());

                }
            });
        }
    }
}
