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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private RadioGroup RButton;
    private EditText passWord2;
    private boolean tutee = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        SaveButton = findViewById(R.id.saveButton);
        RButton = findViewById(R.id.radioGroup);
        user = findViewById(R.id.EditUsername);
        name = findViewById(R.id.EditName);
        email = findViewById(R.id.EditEmail);
        passWord = findViewById(R.id.EditPassword);
        passWord2 = findViewById(R.id.EditConfirmPassword);
        //mAuth = FirebaseAuth.getInstance();
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
    public void isExist(){
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                            }
                            Toast.makeText(getApplicationContext(), "This user is" + String.valueOf(isExist), Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    public void addChangeUser(csci310.myapplication.model.Tutee tutee) {
        db.collection("tutees").document(username).set(tutee);
        Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
    }
}