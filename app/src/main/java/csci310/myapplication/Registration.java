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
    private EditText email;
    private EditText passWord;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        SaveButton = findViewById(R.id.saveButton);
        email = findViewById(R.id.CreateEmail);
        passWord = findViewById(R.id.CreatePassword);
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