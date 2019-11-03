package csci310.myapplication.operator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import csci310.myapplication.model.Tutor;


public class TutorOperator extends AppCompatActivity {
    private FirebaseFirestore db;
    private String username;
    private String password;
    private String name;
    private CollectionReference citiesRef;

    public TutorOperator() {

    }

    public TutorOperator(String username){
        this.db = FirebaseFirestore.getInstance();
        this.username        = username;
        citiesRef = db.collection("tutors");
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

    public void addChangeUser(Tutor tutor) {
        db.collection("tutors").document(username).set(tutor);
        Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
    }

    public void removeUser() {
        citiesRef.document(username)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("qqqq", "User successfully deleted!");
                        Toast.makeText(getApplicationContext(), "User successfully deleted", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("qqqq", "Error deleting user", e);
                        Toast.makeText(getApplicationContext(), "User deleted error", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void checkPassword(final String inputPW) {
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                password = document.getData().get("password").toString();
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            } else if (password != inputPW) {
                                Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password is correct", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    public void getName() {
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                name = document.getData().get("name").toString();
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "User's name is "+ name, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    public void getReviewNum() {
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            int reviewNum = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                reviewNum = Integer.parseInt(document.getData().get("reviewNum").toString());
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "User's review number is "+ String.valueOf(reviewNum), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    public void getTotalReview() {
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            int totalreview = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                totalreview = Integer.parseInt(document.getData().get("totalreview").toString());
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "User's totalreview is "+ String.valueOf(totalreview), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }

    public void getRate() {
        Query query = citiesRef.whereEqualTo("username", username);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            double rate = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                int totalreview = Integer.parseInt(document.getData().get("totalreview").toString());
                                int reviewNum   = Integer.parseInt(document.getData().get("reviewNum").toString());
                                rate = (double)totalreview/reviewNum;
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "User's rate is "+ String.valueOf(rate), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }
}
