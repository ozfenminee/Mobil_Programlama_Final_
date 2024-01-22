package com.example.mobil_programlama_final.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobil_programlama_final.Adaptors.LabelAdaptor;
import com.example.mobil_programlama_final.Model.LabelModel;
import com.example.mobil_programlama_final.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AddLabelFragment extends Fragment {
    private static final String TAG ="LABEL";
    EditText edtlabel ,edtDesc;
    Button btnEkle;
    ArrayList<LabelModel> labelItems;
    LabelAdaptor labelItemAdaptors;
    RecyclerView labelRV;
    FirebaseFirestore f = FirebaseFirestore.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_label, container, false);
        edtDesc = root.findViewById(R.id.textView11);
        edtlabel = root.findViewById(R.id.textView10);
        btnEkle = root.findViewById(R.id.button3);
        labelRV=root.findViewById(R.id.RecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        labelRV.setLayoutManager(llm);
        labelItems = new ArrayList<>();
      //  labelItemAdaptors = new LabelAdaptor(getActivity(),labelItems);
        labelRV.setAdapter(labelItemAdaptors);
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLabels();
            }
        });

        getlabelitems();

        return root;
    }

    private void getlabelitems() {
        f.collection("Labels").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
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

    private void addLabels() {
        String label = edtlabel.getText().toString();
        String desc = edtDesc.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("labels");
        LabelModel labelitems = new LabelModel(label, desc);
        ref.add(labelitems);
    }
}
