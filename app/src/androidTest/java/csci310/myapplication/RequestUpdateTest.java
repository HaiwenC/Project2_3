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
import static csci310.myapplication.MainActivity.sessionRefe;
import static csci310.myapplication.MainActivity.requestRefe;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Request;
import model.Session;
import model.Tutee;
import model.Tutor;


@RunWith(AndroidJUnit4.class)
public class RequestUpdateTest {


    @Rule
    public ActivityTestRule<TuteeProfile> mActivityRule =
            new ActivityTestRule(TuteeProfile.class);

    private TuteeProfile tp;
    private TutorProfile to;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference sessionRefe = db.collection("sessions");
    private String tuteename;
    private String tutorname;
    private String subject = "CSCI310";
    private int dayOfWeek = 5;
    private int time = 10;
    private String tutorEmail = "philipju@usc.edu";
    private String tuteeEmail = "beiyouzh@usc.edu";

    @Before
    public void setup() {


        //    public Session(String tutorName, String tuteeName, String subject, int dayOfWeek, int time,String tutorEmail, String tuteeEmail){
        requestRefe.document(tuteename+tutorname).set(new Request(tuteename,tutorname,subject,dayOfWeek,time,tutorEmail,tuteeEmail));

    }


    @Test
    public void checkIsFoundInDB() {
        Query query = sessionRefe.whereEqualTo("tutee", "beiyouyou");
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
                        assertTrue("Session should exist in database", isExist);
                    }
                });
    }

    @Test
    public void checkIsModifiedInDB() {
        Query query = sessionRefe.whereEqualTo("tutee", "beiyouyou");
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                assertEquals("Checking the new time","10",document.getData().get("time").toString());
                            }

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

}