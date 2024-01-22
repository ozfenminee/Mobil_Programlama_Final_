package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mobil_programlama_final.MainActivity;
import com.example.mobil_programlama_final.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (auth.getCurrentUser() != null) {
                    Toast.makeText(getApplicationContext(), "Anasayfaya Yönlendiriliyosunuz", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN GİRİŞ YAPINIZ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivitiy.class));
                    finish();
                }
            }
        }, 1000);

    }
}



