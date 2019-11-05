//package operator;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RequestOperator {
//    private FirebaseFirestore db;
//    private CollectionReference citiesRef;
//
//    private String tutor;
//    private String tutee;
//
//    //    verification = tutor username + start time
//    private String verification;
//
//    //    1-tutor 2-tutee
//    private int input;
//
//    RequestOperator(){}
//
//    RequestOperator(Tutor tutor){
//        this.db = FirebaseFirestore.getInstance();
//        citiesRef = db.collection("requests");
//        this.tutor = tutor.getUsername();
//        this.input = 1;
//    }
//    RequestOperator(Tutee tutee) {
//        this.db = FirebaseFirestore.getInstance();
//        citiesRef = db.collection("requests");
//        this.tutee = tutee.getUsername();
//        this.input = 2;
//    }
//
//    //    you can add or change a request in any condition even for different tutor or tutee.
//    public void addChangeRequest(Request request) {
//        request.setIsAccept(0);
//        db.collection("request").document(request.getTutor()+request.getTimeStart()).set(request);
//        Toast.makeText(getApplicationContext(), "Request added", Toast.LENGTH_LONG).show();
//    }
//
//    public void getRequestList() {
//        Query query = null;
//        if (input == 1){
//            query = citiesRef.whereEqualTo("tutor", tutor);
//        } else if (input == 2) {
//            query = citiesRef.whereEqualTo("tutee", tutee);
//        }
//
//        query.get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            List<Request> requestList = new ArrayList<Request>();
//                            boolean isExist = false;
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("qqqq", document.getId() + " => " + document.getData());
//                                isExist = true;
//                                String start = document.getData().get("timeStart").toString();
//                                String end = document.getData().get("timeEnd").toString();
//                                int status = Integer.parseInt(document.getData().get("isAccept").toString());
//                                String tuteeU = document.getData().get("tutee").toString();
//                                String tutorU = document.getData().get("tutor").toString();
//                                String verification = document.getData().get("verification").toString();
//                                requestList.add(new Request(tutorU, tuteeU, start, end, status));
//                            }
//                            if (!isExist) {
//                                Toast.makeText(getApplicationContext(), "Can't find request", Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Request list found", Toast.LENGTH_LONG).show();
//                            }
//                        } else {
//                            Log.d("qqqq","Error, can't run query");
//                        }
//                    }
//                });
//    }
//
//    public void denyRequet(Request request) {
//        request.setIsAccept(2);
//        db.collection("users").document(request.getTutor()+request.getTimeStart()).set(request);
//        Toast.makeText(getApplicationContext(), "request added", Toast.LENGTH_LONG).show();
//    }
//
//    public void acceptRequet(Request request) {
//        request.setIsAccept(1);
//        db.collection("users").document(request.getTutor()+request.getTimeStart()).set(request);
//        Toast.makeText(getApplicationContext(), "request added", Toast.LENGTH_LONG).show();
//    }
//}
