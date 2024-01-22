package com.example.mobil_programlama_final.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Adaptors.GalleryCardAdaptor;
import com.example.mobil_programlama_final.Model.GalleryCardModel;
import com.example.mobil_programlama_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private static final String TAG = "CARD";
    private ArrayList<GalleryCardModel> cardItems;
    private GalleryCardAdaptor cardAdapter;
    private RecyclerView labelView;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        labelView = root.findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        labelView.setLayoutManager(manager);
        cardItems = new ArrayList<>();
        cardAdapter = new GalleryCardAdaptor(getActivity(), cardItems);
        labelView.setAdapter(cardAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getCardItems();
    }

    private void getCardItems() {
        firebaseFirestore.collection("Gallery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    cardItems.clear(); //
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        GalleryCardModel cardModel = document.toObject(GalleryCardModel.class);
                        cardItems.add(cardModel);
                    }
                    cardAdapter.notifyDataSetChanged();
                } else {
                    //
                }
            }
        });
    }
}
