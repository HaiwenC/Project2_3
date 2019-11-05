package csci310.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    private Button SaveButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText user;
    private EditText name;
    private EditText email;
    private EditText passWord;
    private EditText passWord2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        SaveButton = findViewById(R.id.saveButton);
        user = findViewById(R.id.CreateUser);
        name = findViewById(R.id.CreateName);
        email = findViewById(R.id.CreateEmail);
        passWord = findViewById(R.id.CreatePassword);
        passWord2 = findViewById(R.id.CreateConfirm);
        //mAuth = FirebaseAuth.getInstance();
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationHelper rh = new RegistrationHelper(user.getText().toString(), name.getText().toString(), email.getText().toString(), passWord.getText().toString(), passWord2.getText().toString());

                if (rh.isValid()) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), passWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Task Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(
                                        new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(), "Register Unsuccessfully", Toast.LENGTH_SHORT).show();
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
                // form is invalid, attempt to resolve below
                else if (rh.errorCode == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid User Name", Toast.LENGTH_SHORT).show();
                } else if (rh.errorCode == 1) {
                    Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
                } else if (rh.errorCode == 2) {
                    Toast.makeText(getApplicationContext(), "Invalid Password. Please enter at least 8 characters.", Toast.LENGTH_SHORT).show();
                } else if (rh.errorCode == 3) {
                    Toast.makeText(getApplicationContext(), "Invalid Email. Please user your @USC email address.", Toast.LENGTH_SHORT).show();
                } else if (rh.errorCode == 4) {
                    Toast.makeText(getApplicationContext(), "Error. Passwords do not match.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid form. Please try again.", Toast.LENGTH_SHORT).show();

                }




            }
        });
    }
}