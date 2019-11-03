package csci310.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

public class MainActivity extends AppCompatActivity {
    private Button Reg;
    private Button Login;
    private RadioGroup RButton;
    private boolean tutee = true;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText username;
    private EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RButton = findViewById(R.id.radioGroup);
        Reg = findViewById(R.id.ButRegister);
        Login = findViewById(R.id.ButLogin);
        username = findViewById(R.id.EditUsername);
        passWord = findViewById(R.id.EditPassword);
        if(RButton.getCheckedRadioButtonId() == R.id.radioTutee){
            tutee = true;
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if(tutee){
                    // 1. verify tutee in database
                    // 2. sign into firebase
                    // 3. confirm that email has been verified
                    mAuth.signInWithEmailAndPassword(username.getText().toString(), passWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()) {
                                 Toast.makeText(getApplicationContext(), "Task Unsuccessful", Toast.LENGTH_SHORT).show();
                             } else {
                                 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                 if (user.isEmailVerified())
                                 {
                                     finish();
                                 } else {
                                     FirebaseAuth.getInstance().signOut();
                                 }
                             }
                         }
                     });
                    i = new Intent(getApplicationContext(),TuteeHome.class);
                }
                else{
                    // 1. verify tutor in database
                    // 2. sign into firebase
                    // 3. confirm that email has been verified
                    mAuth.signInWithEmailAndPassword(username.getText().toString(), passWord.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Task Unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user.isEmailVerified())
                                {
                                    finish();
                                } else {
                                    FirebaseAuth.getInstance().signOut();
                                }
                            }
                        }
                    });
                    i = new Intent(getApplicationContext(),TutorHome.class);
                }
                startActivity(i);
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Registration.class);
                startActivity(i);
            }
        });
        RButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioTutee){
                    tutee = true;
                }
                else if(i == R.id.radioTutor){
                    tutee = false;
                }
            }
        });
    }
}
