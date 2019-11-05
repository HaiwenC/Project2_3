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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import model.Tutee;
import model.Tutor;

public class MainActivity extends AppCompatActivity {
    private Button Reg;
    private Button Login;
    private RadioGroup RButton;
    private boolean tutee = true;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText username;
    private EditText passWord;
    public static Tutee tuteeInfo;
    public static Tutor tutorInfo;

    public static CollectionReference tuteeRefe = db.collection("tutees");
    public static CollectionReference tutorRefe = db.collection("tutors");
    public static CollectionReference requestRefe = db.collection("requests");
    public static CollectionReference sessionRefe = db.collection("sessions");

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
                                     Toast.makeText(getApplicationContext(), "email verified", Toast.LENGTH_SHORT).show();
                                 } else {
                                     FirebaseAuth.getInstance().signOut();
                                     Toast.makeText(getApplicationContext(), "please verify email", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         }
                     });
                    checkPassword(username.getText().toString(), passWord.getText().toString());

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
                                    Intent i = new Intent(getApplicationContext(),TutorHome.class);
                                    startActivity(i);
                                } else {
                                    FirebaseAuth.getInstance().signOut();
                                    Toast.makeText(getApplicationContext(), "please verify email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
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
    public void checkPassword(final String email, final String inputPW) {
        CollectionReference citiesRef;
        if (tutee == true){
             citiesRef = tuteeRefe;
        }else {
            citiesRef = tutorRefe;
        }
        Query query = citiesRef.whereEqualTo("email", email);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String password = "";
                            String studentID = "";
                            String name      = "";
                            String username  = "";
                            boolean isExist = false;
                            Intent i;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                password = document.getData().get("password").toString();
                                studentID = document.getData().get("studentID").toString();
                                name      = document.getData().get("name").toString();
                                username  = document.getData().get("username").toString();

                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User is not existed", Toast.LENGTH_LONG).show();
                            } else if (!password.equals(inputPW)) {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is correct", Toast.LENGTH_LONG).show();
                                if(tutee == true) {
                                    tuteeInfo = new Tutee(studentID,email ,name, username, password);
                                    i = new Intent(getApplicationContext(),TuteeHome.class);
                                }else {
                                    tutorInfo = new Tutor(studentID,email ,name, username, password);
                                    i = new Intent(getApplicationContext(),TutorHome.class);
                                }
                                startActivity(i);
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }
}
