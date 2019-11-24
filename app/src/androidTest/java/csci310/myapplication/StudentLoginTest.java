package csci310.myapplication;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static csci310.myapplication.MainActivity.tuteeRefe;
import static csci310.myapplication.MainActivity.tutorRefe;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Tutee;
import model.Tutor;


@RunWith(AndroidJUnit4.class)
public class StudentLoginTest {


    @Rule
    public ActivityTestRule<TuteeProfile> mActivityRule =
            new ActivityTestRule(TuteeProfile.class);
    public ActivityTestRule<TutorProfile> fActivityRule =
            new ActivityTestRule(TutorProfile.class);

    private TuteeProfile tp;
    private TutorProfile to;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference tuteeRefe = db.collection("tutees");
    private CollectionReference tutorRefe = db.collection("tutors");
    private String tuteeusername;
    private String tuteepassword;
    private String tuteename;
    private String tuteeemail;
    private String tuteestrudentID;
    private String tutorusername;
    private String tutorpassword;
    private String tutorname;
    private String tutoremail;
    private String tutorstrudentID;

    @Before
    public void setup() {
        tp = (TuteeProfile) mActivityRule.getActivity();

        tuteeusername   = "testTutee";
        tuteepassword   = "abcd1234";
        tuteename       = "testTutee";
        tuteeemail      = "testTutee@usc.edu";
        tuteestrudentID = "1234567890";

        tp.modifyName(tuteeusername,"newTestName");

        to = (TutorProfile) fActivityRule.getActivity();

        tutorusername   = "testTutor";
        tutorpassword   = "abcd1234";
        tutorname       = "testTutee";
        tutoremail      = "testTutee@usc.edu";
        tutorstrudentID = "1234567890";


    }



    @Test
    public void checkTuteeIsFoundInDB() {
        Query query = tuteeRefe.whereEqualTo("username", tuteeusername);
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

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                        assertTrue("User should exist in database", isExist);
                    }
                });
    }


    @Test
    public void checkTuteeIsNotFoundInDB() {
        Query query = tuteeRefe.whereEqualTo("username", "SHOULD_NOT_EXIST");
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

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                        assertEquals(false, isExist);
                    }
                });
    }

    @Test
    public void checkTutorIsFoundInDB() {
        Query query = tuteeRefe.whereEqualTo("username", tuteeusername);
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

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                        assertTrue("User should exist in database", isExist);
                    }
                });
    }

    @Test
    public void checkTutorIsNotFoundInDB() {
        Query query = tuteeRefe.whereEqualTo("username", "SHOULD_NOT_EXIST");
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

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                        assertEquals(false, isExist);
                    }
                });
    }


}
