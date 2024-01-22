package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobil_programlama_final.MainActivity;
import com.example.mobil_programlama_final.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivitiy extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton, signupButton;
    TextView login, or;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activitiy);
        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        passwordEditText = findViewById(R.id.editTextNumberPassword2);
        loginButton = findViewById(R.id.button5);
        signupButton = findViewById(R.id.button6);
        login = findViewById(R.id.textView6);
        or = findViewById(R.id.textView7);
        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivitiy.this, "Lütfen bu alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(LoginActivitiy.this, "Şifre 6 karakterden büyük olmalıdır", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivitiy.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivitiy.this, MainActivity.class));  // MainActivitiy'e yönlendir
                    finish();
                } else {
                    Toast.makeText(LoginActivitiy.this, "Giriş Başarısızdır", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Kayıt butonuna tıklanınca RegisterActivity'e yönlendir
        signupButton.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivitiy.this, RegisterActivity.class));
            finish();
        });
    }
}