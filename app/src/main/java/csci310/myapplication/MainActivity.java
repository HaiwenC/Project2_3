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
                                     checkPassword(username.getText().toString(), passWord.getText().toString());

                                     Toast.makeText(getApplicationContext(), "email verified", Toast.LENGTH_SHORT).show();
                                 } else {
                                     //user.sendEmailVerification();
                                     FirebaseAuth.getInstance().signOut();
                                     Toast.makeText(getApplicationContext(), "please verify email address", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         }
                     });

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
                                    checkPassword(username.getText().toString(), passWord.getText().toString());

                                } else {
                                    //user.sendEmailVerification();
                                    FirebaseAuth.getInstance().signOut();
                                    Toast.makeText(getApplicationContext(), "please verify email address", Toast.LENGTH_SHORT).show();
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
                            int last_time    = -1;
                            int last_day     = -1;
                            String last_subject = "";
                            int tutor_day    = -1;
                            int tutor_time     = -1;
                            String tutor_subject = "";
                            int tutor_totalRating = -1;
                            int tutor_numRating   = -1;
                            boolean isExist = false;
                            Intent i;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                password = document.getData().get("password").toString();
                                studentID = document.getData().get("studentID").toString();
                                name      = document.getData().get("name").toString();
                                username  = document.getData().get("username").toString();
                                if(tutee) {
                                    last_time = document.getData().get("last_time") == null? 0:Integer.parseInt(document.getData().get("last_time").toString());
                                    last_day  = document.getData().get("last_day") == null? 0: Integer.parseInt(document.getData().get("last_day").toString());
                                    last_subject = document.getData().get("last_subject") == null? null: document.getData().get("last_subject").toString();
                                }else{
                                    if (document.getData().get("tutor_day") != null) {
                                        tutor_day = Integer.parseInt(document.getData().get("tutor_day").toString());
                                    } else tutor_day = 0;
                                    if (document.getData().get("timeNew") != null) {
                                        tutor_time  = Integer.parseInt(document.getData().get("timeNew").toString());
                                    } else tutor_time = 0;
                                    if (document.getData().get("subjectNew") != null) {
                                        tutor_subject = document.getData().get("subjectNew").toString();
                                    } else tutor_subject = "";
                                    if (document.getData().get("totalRating") != null) {
                                        tutor_totalRating = Integer.parseInt(document.getData().get("totalRating").toString());
                                    } else tutor_totalRating = 0;
                                    if (document.getData().get("numRating") != null) {
                                        tutor_numRating   = Integer.parseInt(document.getData().get("numRating").toString());
                                    } else tutor_numRating = 0;
                                }
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            } else if (!password.equals(inputPW)) {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is correct", Toast.LENGTH_LONG).show();
                                if(tutee == true) {
                                    tuteeInfo = new Tutee(studentID,email ,name, username, password);
                                    tuteeInfo.setLastSearch_day(last_day);
                                    tuteeInfo.setLastSearch_time(last_time);
                                    tuteeInfo.setLastSearch_subject(last_subject);
                                    i = new Intent(getApplicationContext(),TuteeHome.class);
                                }else {
                                    tutorInfo = new Tutor(studentID,email ,name, username, password);
                                    tutorInfo.setSubjectNew(tutor_subject);
                                    tutorInfo.setTimeNew(tutor_time);
                                    tutorInfo.setWeekNew(tutor_day);
                                    tutorInfo.setNumRatings(tutor_numRating);
                                    tutorInfo.setRatingTotal(tutor_totalRating);
                                    i = new Intent(getApplicationContext(),TutorHome.class);
                                }
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }
}
