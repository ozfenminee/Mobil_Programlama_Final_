package com.example.mobil_programlama_final.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobil_programlama_final.Model.GalleryCardModel;
import com.example.mobil_programlama_final.R;

import java.util.ArrayList;

public class GalleryCardAdaptor extends RecyclerView.Adapter<GalleryCardAdaptor.ItemHolder> {
    private ArrayList<GalleryCardModel> cards;
    private Context context;

    public GalleryCardAdaptor(Context context, ArrayList<GalleryCardModel> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerycard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        position = holder.getAdapterPosition();
        Glide.with(context).load(cards.get(position).getImg()).into(holder.img);
        holder.username.setText(cards.get(position).getUsername());
        holder.label.setText(cards.get(position).getLabel());
        holder.description.setText(cards.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView username, label, description;
        ImageView img;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.textView5);
            label = itemView.findViewById(R.id.textView8);
            description = itemView.findViewById(R.id.textView9);
            img = itemView.findViewById(R.id.imageView3);
        }
    }
}
