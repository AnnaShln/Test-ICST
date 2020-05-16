package com.example.testicst.Catalog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testicst.R;

import java.util.List;
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupVH> {

    FragmentManager myFragmentManager;
    Context mContext;
    public static final String TAG = "GroupAdapter";
    List<Group> GroupList;

    public GroupAdapter(List<Group> groupList) {
        this.GroupList = groupList;
    }

    @NonNull
    @Override
    public GroupVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.handbook_list_of_groups, parent, false);
        mContext = parent.getContext();
        return new GroupVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupVH holder, int position) {
        Group group = GroupList.get(position);
        holder.bindTo(group);
    }

    @Override
    public int getItemCount() {
        return GroupList.size();
    }

    class GroupVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TextView_title;

        public GroupVH(@NonNull final View itemView) {
            super(itemView);

            TextView_title = itemView.findViewById(R.id.TextView_title);
            itemView.setOnClickListener(this);
        }

        void bindTo(Group currentGroup){
            TextView_title.setText(currentGroup.title);
        }

        @Override
        public void onClick(View v) {
            SpecialityList fragment = new SpecialityList();
            Bundle bundle = new Bundle();
            bundle.putInt(TAG, GroupList.get(getAdapterPosition()).id);
            fragment.setArguments(bundle);

            ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                    //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                            R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
