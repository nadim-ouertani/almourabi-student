package com.nadim.almourabi;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nadim on 4/18/18.
 * from tunisia with love
 */

public class EvaListAdapter extends RecyclerView.Adapter<EvaListAdapter.MyViewHolder> {

    private List<EvaList> evaLists;

    EvaListAdapter(List<EvaList> evaLists) {
        this.evaLists = evaLists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evalist_rows, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EvaList evaList = evaLists.get(position);
        holder.fromFirstName.setText(evaList.getFirstName());
        holder.fromLastName.setText(evaList.getLastName());
        holder.Subject.setText(evaList.getSubject());
        if (evaList.getEva().equals("GOOD")) {
            holder.yourEva.setText(evaList.getEva());
            holder.yourEva.setTextColor(Color.GREEN);
        } else {
            holder.yourEva.setText(evaList.getEva());
            holder.yourEva.setTextColor(Color.RED);
        }
        holder.yourEva.setText(evaList.getEva());

    }

    @Override
    public int getItemCount() {
        return evaLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fromFirstName, fromLastName, Subject, yourEva;

        MyViewHolder(View view) {
            super(view);
            fromFirstName = view.findViewById(R.id.fromTeacherFirstName);
            fromLastName = view.findViewById(R.id.fromTeacherLastName);
            Subject = view.findViewById(R.id.fromTeacherSubject);
            yourEva = view.findViewById(R.id.yourEva);
        }
    }
}
