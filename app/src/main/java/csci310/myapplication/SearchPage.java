package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import model.Request;
import model.Tutee;
import model.Tutor;

import static csci310.myapplication.MainActivity.tuteeInfo;
import static csci310.myapplication.MainActivity.tuteeRefe;
import static csci310.myapplication.MainActivity.tutorRefe;

public class SearchPage extends AppCompatActivity {
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA-ESL_yo:APA91bEiMLxPBAp-OrOmfUiTWUQ8pK_VmoYs3nQyTEVkY7T9j7aQBqTZ3o4-JiEX6LUnJ9yJfTtf7R9sOdRneJNbXjHhxBr3sGqMMtDrHmdVka0vwBT4QVv_mMuqPtb42Q6BC8miZL6n";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private String NOTIFICATION_TITLE;
    private String NOTIFICATION_MESSAGE;
    private String TOPIC;
    public static int REQUEST_CODE = 1;
    private Button save;
    private Button search;
    private ListView list;
    private SearchAdapter adapter;
    private Spinner spinner_subject;
    private Spinner spinner_day;
    private Spinner spinner_period;
    private String subject;
    private int period;
    private RequestQueue mRequestQueue;
    private int day;
    //    private EditText subject;
    private List<Tutor> Requests = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
        list = findViewById(R.id.listOfSearch);
        save = findViewById(R.id.SaveBut);
        search = findViewById(R.id.SearchBut);
        spinner_subject = findViewById(R.id.CourseSelect);
        spinner_day = findViewById(R.id.daySelect);
        spinner_period = findViewById(R.id.periodSelect);
//        subject = findViewById(R.id.Subject);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapter = new SearchAdapter(getApplicationContext(), 0, Requests);
        list.setAdapter(adapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Requests.clear();
                subject = spinner_subject.getSelectedItem().toString();
                day    = spinner_day.getSelectedItemPosition();
                period  = Integer.valueOf(spinner_period.getSelectedItem().toString().substring(0,2));
                Log.d("debugSearch", subject+ String.valueOf(day) + " " + String.valueOf(period));
                Query query = tutorRefe.whereEqualTo("subjectNew", subject);
                query.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                boolean isExist = false;
                                String password = "";
                                String studentID = "";
                                String name      = "";
                                String username  = "";
                                String email     = "";
                                String sub       = "";
                                int ava_time     = -1;
                                int ava_day      = -1;
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("qqqq", document.getId() + " => " + document.getData());
                                        isExist = true;
                                        password = document.getData().get("password").toString();
                                        studentID = document.getData().get("studentID").toString();
                                        name      = document.getData().get("name").toString();
                                        username  = document.getData().get("username").toString();
                                        email  = document.getData().get("email").toString();
                                        sub    = document.getData().get("subjectNew").toString();
                                        ava_day = Integer.parseInt(document.getData().get("weekNew").toString());
                                        ava_time = Integer.parseInt(document.getData().get("timeNew").toString());
                                        Tutor tutorNew = new Tutor(studentID, email ,name, username, password);
                                        tutorNew.setSubjectNew(subject);
                                        tutorNew.setTimeNew(ava_time);
                                        tutorNew.setWeekNew(ava_day);
                                        if ((subject.equals(sub)) && (period==ava_time) && (day == ava_day)){
                                            Requests.add(tutorNew);
                                        }
                                    }
                                    if (isExist) {
                                        Toast.makeText(getApplicationContext(), "This user is existed", Toast.LENGTH_LONG).show();
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        CollectionReference citiesRefInside;
                                        Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Log.d("qqqq","Error, can't run query");
                                }
                            }
                        });
            }
        });
    }
    private class SearchAdapter extends ArrayAdapter<Tutor> {
        List<Tutor> Groups;
        public SearchAdapter(Context context, int resource, List<Tutor> objects) {
            super(context, resource, objects);
            this.Groups = objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.tutee_application_layout, null);
            }
            TextView name = convertView.findViewById(R.id.RequestName);
            TextView course = convertView.findViewById(R.id.RequestCourse);
            TextView period = convertView.findViewById(R.id.TimePeriod);
            Button apply = convertView.findViewById(R.id.Apply);
            final Tutor tutor = Groups.get(position);
            name.setText(tutor.getName());
            course.setText(tutor.getSubjectNew());
            period.setText(String.valueOf(tutor.getTimeNew()));
            apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Request r = new Request(MainActivity.tuteeInfo.getUsername(), tutor.getUsername(), tutor.getSubjectNew(), tutor.getWeekNew() ,tutor.getTimeNew());
                    MainActivity.requestRefe.document(tutor.getUsername()+MainActivity.tuteeInfo.getUsername()).set(r);
                    view.setEnabled(false);
                    TuteeHome.groups.add(r);
                    Toast.makeText(getApplicationContext(), "application sent", Toast.LENGTH_SHORT).show();
                    //code for notification
                    TOPIC = "/topics/" + tutor.getName(); //topic must match with what the receiver subscribed to
                    NOTIFICATION_TITLE = "A new application for your session";
                    NOTIFICATION_MESSAGE = "student " + tuteeInfo.getName() + " applied to your session" ;
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
            });
            return convertView;
        }
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
