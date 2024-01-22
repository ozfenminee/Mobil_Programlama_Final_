package com.example.mobil_programlama_final.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobil_programlama_final.R;



public class AboutFragment extends Fragment {
    Button btnGithub,btnLinkedin;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_about,container,false);
        btnGithub=root.findViewById(R.id.button8);
        btnLinkedin=root.findViewById(R.id.button11);
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGithub=new Intent();
                intentGithub.setAction(Intent.ACTION_VIEW);
                intentGithub.addCategory(Intent.CATEGORY_BROWSABLE);
                intentGithub.setData(Uri.parse("https://github.com/ozfenminee"));
                startActivity(intentGithub);

            }
        });
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intentLinkedin=new Intent();
                    intentLinkedin.setAction(Intent.ACTION_VIEW);
                    intentLinkedin.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentLinkedin.setData(Uri.parse("https://www.linkedin.com/feed/?trk=homepage-basic_sign-in-submit"));
                    startActivity(intentLinkedin);

            }
        });

        return root;
    }
}

