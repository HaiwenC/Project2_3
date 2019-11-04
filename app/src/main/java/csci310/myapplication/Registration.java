package csci310.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    private Button SaveButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText email;
    private EditText Username;
    private EditText Name;
    private EditText confirmPassword;
    private EditText passWord;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        SaveButton = findViewById(R.id.saveButton);
        email = findViewById(R.id.EditEmail);
        passWord = findViewById(R.id.EditPassword);
        Username = findViewById(R.id.EditUsername);
        Name = findViewById(R.id.EditName);
        confirmPassword = findViewById(R.id.EditConfirmPassword);
        //get all user input values;
        String user_name = Name.getText().toString();
        String user_username = Username.getText().toString();
        String user_password = passWord.getText().toString();
        String user_confirmPassword = confirmPassword.getText().toString();
        String user_email = email.getText().toString();
        //mAuth = FirebaseAuth.getInstance();
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), passWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "task unseccessful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(
                                    new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(), "register sucessfully", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "register Unsucessfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                            );
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "account created", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}