package com.example.mobil_programlama_final.Adaptors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Model.LabelModel;
import com.example.mobil_programlama_final.R;

import java.util.ArrayList;

public class LabelAdaptor extends RecyclerView.Adapter<LabelAdaptor.ItemHolder> {
    private ArrayList<LabelModel>labels;
    private  Context context;

    public LabelAdaptor(ArrayList<LabelModel> labels, Context context) {
        this.labels = labels;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabelAdaptor.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labelitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        position=holder.getAdapterPosition();
        holder.label.setText(labels.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return labels.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView label;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            label=itemView.findViewById(R.id.textView16);

        }
    }


}
