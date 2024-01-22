package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobil_programlama_final.MainActivity;
import com.example.mobil_programlama_final.Model.userModel;
import com.example.mobil_programlama_final.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText,FirstNameEditText,LastNameEditText;
    Button registerButton;
    Button registerLoginButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailEditText = findViewById(R.id.editTextTextEmailAddress4);
        passwordEditText = findViewById(R.id.editTextNumberPassword4);
        FirstNameEditText=findViewById(R.id.editTextText2);
        LastNameEditText=findViewById(R.id.editTextText3);

        registerButton = findViewById(R.id.button9);
        registerLoginButton = findViewById(R.id.button10);
        auth = FirebaseAuth.getInstance();

        registerLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivitiy.class));
            finish();
        });

        registerButton.setOnClickListener(v1 -> {
            String Email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String FirstName=FirstNameEditText.getText().toString();
            String LastName=LastNameEditText.getText().toString();


            if (Email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Lütfen bu alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "Şifre 6 karakterden büyük olmalıdır", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = task.getResult().getUser().getUid();
                    Toast.makeText(RegisterActivity.this, "Kayıt işlemi başarılıdır.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivitiy.class));
                    finish();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference ref = db.collection("UserModel");
                    userModel user = new userModel(Email,FirstName,LastName ,uid);
                    ref.add(user);
                } else {
                    Toast.makeText(RegisterActivity.this, "Kayıt İşlemi Başarısızdır.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        });
    }
}