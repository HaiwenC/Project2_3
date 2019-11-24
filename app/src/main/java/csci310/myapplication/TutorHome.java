package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import model.Request;
import model.Session;
import model.Tutee;
import model.Tutor;

import static csci310.myapplication.MainActivity.requestRefe;
import static csci310.myapplication.MainActivity.sessionRefe;
import static csci310.myapplication.MainActivity.tuteeInfo;
import static csci310.myapplication.MainActivity.tutorInfo;


public class TutorHome extends AppCompatActivity {
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA-ESL_yo:APA91bEiMLxPBAp-OrOmfUiTWUQ8pK_VmoYs3nQyTEVkY7T9j7aQBqTZ3o4-JiEX6LUnJ9yJfTtf7R9sOdRneJNbXjHhxBr3sGqMMtDrHmdVka0vwBT4QVv_mMuqPtb42Q6BC8miZL6n";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private String NOTIFICATION_TITLE;
    private String NOTIFICATION_MESSAGE;
    private String TOPIC;
    private Button profile;
    private Button session;
    private ListView list;
    private RequestQueue mRequestQueue;
    private RequestAdapter adapter;
    public static List<Request> groups = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(tutorInfo==null) {
            tutorInfo = new Tutor("1234567890","testTutor@usc.edu","testTutor","testTutor","abcd1234");
        }
        setContentView(R.layout.tutorpage);
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/" + tutorInfo.getName());
        list = findViewById(R.id.listNotes);
        session = findViewById(R.id.Session);
        Log.d("debug listview", list.toString());
//        groups.add(new Request(new Tutee("1", "1", "Beiyou","1", "aaaaaaaaa"),
//                new Tutor("1", "1", "!", "1", "aaaaaaaaa"), "csci109", 1 ,8, 9));
        searchRequest(tutorInfo.getUsername());
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TutorProfile.class);
                startActivity(i);
            }
        });
        adapter = new RequestAdapter(getApplicationContext(), 0, groups);
        //Log.d("debug adapter", adapter.toString());
        list.setAdapter(adapter);
        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SessionHistory.class);
                startActivity(i);
            }
        });

    }
    private class RequestAdapter extends ArrayAdapter<Request> {
        List<Request> Groups;
        public RequestAdapter(Context context, int resource, List<Request> objects) {
            super(context, resource, objects);
            this.Groups = objects;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.requestlayout, null);
            }
            TextView name = convertView.findViewById(R.id.RequestName);
            TextView course = convertView.findViewById(R.id.RequestCourse);
            TextView period = convertView.findViewById(R.id.TimePeriod);
            Button accept = convertView.findViewById(R.id.Apply);
            Button reject = convertView.findViewById(R.id.reject);
            final Request gp = Groups.get(position);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groups.remove(position);
                    adapter.notifyDataSetChanged();
                    gp.setStatus("accepted");
                    requestRefe.document(gp.getTutor()+gp.getTutee()).set(gp);
                    Session sessionNew = new Session(gp.getTutor(),gp.getTutee(),gp.getSubject(),gp.getDayOfWeek(),gp.getTime(), gp.getTutorEmail(),gp.getTuteeEmail());
                    sessionRefe.document(gp.getTutor()+gp.getTutee()).set(sessionNew);
                    notifyTutee(gp, "accepted");

                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groups.remove(position);
                    adapter.notifyDataSetChanged();
                    gp.setStatus("rejected");
                    requestRefe.document(gp.getTutor()+gp.getTutee()).set(gp);
                    notifyTutee(gp, "rejected");
                }
            });
            name.setText(gp.getTutee());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime()) + ":00 - " + String.valueOf(gp.getTime()+1)+":00");
            return convertView;
        }
    }

    public void searchRequest(String tutorUN) {
        Query query = requestRefe.whereEqualTo("tutor", tutorUN);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            groups.clear();
                            boolean isExist = false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("datasearch", document.getId() + " => " + document.getData());
                                isExist = true;
                                Request requestNew = new Request(document.getData().get("tutee").toString(),document.getData().get("tutor").toString(),document.getData().get("subject").toString(),Integer.parseInt(document.getData().get("dayOfWeek").toString()),Integer.parseInt(document.getData().get("time").toString()),document.getData().get("tutorEmail").toString(),document.getData().get("tuteeEmail").toString());
                                requestNew.setStatus(document.getData().get("status").toString());
                                if (requestNew.getStatus().equals("available")){
                                    groups.add(requestNew);
                                }
                            }
                            if (!isExist) {
                                Toast.makeText(getApplicationContext(), "Session does not exist", Toast.LENGTH_LONG).show();
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d("qqqq","Error, can't run query");
                        }
                    }
                });
    }
    private void notifyTutee(Request rq, String accept){
        //code for notification
        TOPIC = "/topics/" + rq.getTutee(); //topic must match with what the receiver subscribed to
        NOTIFICATION_TITLE = "Your application decision is available";
        NOTIFICATION_MESSAGE = "Your application to tutor " + rq.getTutor() + " is " + accept;
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        sendNotification(notification);
    }
    private void sendNotification(JSONObject notification) {
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }
}
