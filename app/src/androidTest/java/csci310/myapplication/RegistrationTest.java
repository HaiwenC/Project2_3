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
public class RegistrationTest {


    @Rule
    public ActivityTestRule<Registration> mActivityRule =
            new ActivityTestRule(Registration.class);

    private Registration re;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference tuteeRefe = db.collection("tutees");
    private String username;
    private String password;
    private String name;
    private String email;
    private String strudentID;

    @Before
    public void setup() {
        re = (Registration) mActivityRule.getActivity();

        username   = "testTutee";
        password   = "abcd1234";
        name       = "testTutee";
        email      = "testTutee@usc.edu";
        strudentID = "1234567890";

        re.checkAddUser(username,password,name,email,strudentID);
    }


    @Test
    public void checkIsRegistedInDB() {
        Query query = tuteeRefe.whereEqualTo("username", username);
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

}