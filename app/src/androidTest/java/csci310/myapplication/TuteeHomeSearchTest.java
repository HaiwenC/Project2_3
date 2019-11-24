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
import java.util.ArrayList;
import java.util.List;

import model.Request;
import model.Tutee;
import model.Tutor;

@RunWith(AndroidJUnit4.class)
public class TuteeHomeSearchTest {


    @Rule
    public ActivityTestRule<TuteeHome> mActivityRule =
            new ActivityTestRule(TuteeHome.class);

    private TuteeHome th;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference requestsRefe = db.collection("requests");
    private String tuteeUsername;
    private String password;
    private String name;
    private String tuteeEmail;
    private String strudentID;
    private int dayOfWeek;
    private String status;
    private String subject;
    private String tutorUsername;
    private String tutorEmail;
    private int time;
    public static List<Request> groups = new ArrayList<>();

    @Before
    public void setup() {
        th = (TuteeHome) mActivityRule.getActivity();


        tuteeUsername   = "testTutee";
        tuteeEmail      = "testTutee@usc.edu";
        strudentID = "1234567890";
        dayOfWeek = 5;
        status = "available";
        subject = "CSCI104";
        tutorUsername = "testTutor";
        tutorEmail = "testTutor@usc.edu";
        time = 10;

        requestsRefe.document(tutorUsername+tuteeUsername).set(new Request(tuteeUsername,tutorUsername,subject,dayOfWeek,time,tutorEmail,tuteeEmail));

        th.searchRequest(tuteeUsername);
        groups = th.groups;
    }


    @Test
    public void checkSearchRequest() {
        Query query = requestsRefe.whereEqualTo("tutee", tuteeUsername);
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
    public void checkDayOfWeek() {
        Query query = requestsRefe.whereEqualTo("tutee", tuteeUsername);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                assertEquals("Checking the dayOfWeek data",groups.get(0).getDayOfWeek(), Integer.parseInt(document.getData().get("dayOfWeek").toString()));
                            }

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    @Test
    public void checkSubject() {
        Query query = requestsRefe.whereEqualTo("tutee", tuteeUsername);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                assertEquals("Checking the subject data",groups.get(0).getSubject(), document.getData().get("subject").toString());
                            }

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    @Test
    public void checkStatus() {
        Query query = requestsRefe.whereEqualTo("tutee", tuteeUsername);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                assertEquals("Checking the status data",groups.get(0).getStatus(), document.getData().get("status").toString());
                            }

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

}
