package com.nadim.almourabi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nadim on 4/13/18.
 * from tunisia with love
 */
public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.MyViewHolder> {

    private List<TeacherList> teacherLists;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView FirstName, LastName, Subject;

        public MyViewHolder(View view) {
            super(view);
            FirstName = view.findViewById(R.id.TeacherList_fn);
            LastName = view.findViewById(R.id.TeacherList_ln);
            Subject = view.findViewById(R.id.TeacherList_sub);
        }
    }


    public TeacherListAdapter(List<TeacherList> teacherLists) {
        this.teacherLists = teacherLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacherlist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TeacherList teacherList = teacherLists.get(position);
        holder.FirstName.setText(teacherList.getFirstName());
        holder.LastName.setText(teacherList.getLastName());
        holder.Subject.setText(teacherList.getSubject());
    }

    @Override
    public int getItemCount() {
        return teacherLists.size();
    }
}