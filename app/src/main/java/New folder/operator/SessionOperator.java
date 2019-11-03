package csci310.myapplication.operator;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import csci310.myapplication.model.Request;
import csci310.myapplication.model.Session;
import csci310.myapplication.model.Tutee;
import csci310.myapplication.model.Tutor;

public class SessionOperator extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    private String timeStart;
    private String timeEnd;
    private String tutor;
    private String tutee;
    private int rate;
    //    1-tutor 2-tutee
    private int input;

    SessionOperator(Tutor tutor){
        this.db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("sessions");
        this.tutor = tutor.getUsername();
        this.input = 1;
        this.rate  = -1;
    }
    SessionOperator(Tutee tutee) {
        this.db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("sessions");
        this.tutee = tutee.getUsername();
        this.input = 2;
        this.rate  = -1;
    }

    //    you can add or change a request in any condition even for different tutor or tutee.
    public void addRequest(Session session) {
        db.collection("sessions").document(session.getTutor()+session.getTimeStart()).set(session);
        Toast.makeText(getApplicationContext(), "Session added", Toast.LENGTH_LONG).show();
    }

    public void getRequestList() {
        Query query = null;
        if (input == 1) {
            query = citiesRef.whereEqualTo("tutor", tutor);
        } else if (input == 2) {
            query = citiesRef.whereEqualTo("tutee", tutee);
        }

        query.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Session> requestList = new ArrayList<Session>();
                    boolean isExist = false;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("qqqq", document.getId() + " => " + document.getData());
                        isExist = true;
                        String start = document.getData().get("timeStart").toString();
                        String end = document.getData().get("timeEnd").toString();
                        String tuteeU = document.getData().get("tutee").toString();
                        String tutorU = document.getData().get("tutor").toString();
                        int rateStar  = Integer.parseInt(document.getData().get("rate").toString());
                        String rev = document.getData().get("review").toString();
                        String verification = document.getData().get("verification").toString();
                        requestList.add(new Session(tutorU, tuteeU, start, end, rateStar, rev));
                    }
                    if (!isExist) {
                        Toast.makeText(getApplicationContext(), "Can't find session", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Session list found", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("qqqq", "Error, can't run query");
                }
                }
            });
    }

    public void removeSession(Session session) {

        citiesRef.document(session.getTutor()+session.getTimeStart()).delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("qqqq", "Session successfully deleted!");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("qqqq", "Error deleting session", e);
                }
            });
    }

    public void checkReview(Session session) {
        Query query = citiesRef.whereEqualTo("verification", session.getTutor()+session.getTimeStart());

        query.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    boolean isExist = false;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("qqqq", document.getId() + " => " + document.getData());
                        isExist = true;
                    }
                    if (!isExist) {
                        Toast.makeText(getApplicationContext(), "Can't find review", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Review found", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("qqqq", "Error, can't run query");
                }
                }
            });
    }
}
