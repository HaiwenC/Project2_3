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
public class TutorProfileTest {


    @Rule
    public ActivityTestRule<TutorProfile> mActivityRule =
            new ActivityTestRule(TutorProfile.class);

    private TutorProfile tp;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference tuteeRefe = db.collection("tutees");
    private String username;
    private String password;
    private String name;
    private String email;
    private String strudentID;

    @Before
    public void setup() {
        tp = (TutorProfile) mActivityRule.getActivity();

        username   = "testTutee";
        password   = "abcd1234";
        name       = "testTutee";
        email      = "testTutee@usc.edu";
        strudentID = "1234567890";

        tp.modifyName(username,"newTestName");
    }


    @Test
    public void checkIsFoundInDB() {
        Query query = tutorRefe.whereEqualTo("username", username);
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
    public void checkIsModifiedInDB() {
        Query query = tutorRefe.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                assertEquals("Checking the new Name","newTestName",document.getData().get("name").toString());
                            }

                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

}
