package com.example.mobil_programlama_final.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.NonUiContext;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Adaptors.LabelAdaptor;
import com.example.mobil_programlama_final.Model.LabelModel;
import com.example.mobil_programlama_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddPhotoFragment extends Fragment {
    private static final String TAG = "LABEL";

    Button btnCamera;
    ImageView imgPhoto;
    RecyclerView labelphotoRV;
    ArrayList<LabelModel> labelItems;
    LabelAdaptor labelItemAdaptors;


    FirebaseFirestore f = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_photo, container, false);
        btnCamera = root.findViewById(R.id.button13);
        imgPhoto = root.findViewById(R.id.imageView4);
        labelphotoRV = root.findViewById(R.id.photoRV);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        labelphotoRV.setLayoutManager(llm);
        labelItems = new ArrayList<>();
        //   labelItemAdaptors = new LabelAdaptor(getActivity(), labelItems); // Düzeltildi
        labelphotoRV.setAdapter(labelItemAdaptors);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        getlabelitems();


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void getlabelitems() {
        f.collection("Labels").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) { // Düzeltildi
                    for (QueryDocumentSnapshot eleman : task.getResult()) {
                        LabelModel lb = eleman.toObject(LabelModel.class);
                        labelItems.add(lb);
                    }
                    labelItemAdaptors.notifyDataSetChanged();
                } else {
                    Log.d(TAG, task.getException().toString());
                }
            }
        });
    }
}


