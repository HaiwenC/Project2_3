package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import model.Request;
import model.Session;
import model.Tutee;

import static csci310.myapplication.MainActivity.requestRefe;
import static csci310.myapplication.MainActivity.sessionRefe;
import static csci310.myapplication.MainActivity.tuteeInfo;

public class TuteeHome extends AppCompatActivity {
    private Button profile;
    private Button Search;
    private Button session;
    private ListView list;
    private RequestAdapter adapter;
    public static List<Request>groups = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(tuteeInfo==null) {
            tuteeInfo = new Tutee("1234567890","testTutee@usc.edu","testTutee","testTutee","abcd1234");
        }
        setContentView(R.layout.tuteepage);
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/" + tuteeInfo.getName());
        profile = findViewById(R.id.ProfileButton);
        Search = findViewById(R.id.Search);
        session = findViewById(R.id.TuteeSession);
        list = findViewById(R.id.listNotes);
        searchRequest(tuteeInfo.getUsername());
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TuteeProfile.class);
                startActivity(i);
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchPage.class);
                startActivityForResult(i, SearchPage.REQUEST_CODE);
            }
        });
        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SessionHistory.class);
                startActivity(i);
            }
        });
        adapter = new RequestAdapter(getApplicationContext(), 0, groups);
        list.setAdapter(adapter);
        requestRefe.whereEqualTo("tutee", tuteeInfo.getUsername()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed:" + e);
                    return;
                }
                groups.clear();
                for(DocumentSnapshot doc: queryDocumentSnapshots){
                    groups.add( new Request(doc.getData().get("tutee").toString(),doc.getData().get("tutor").toString(),doc.getData().get("subject").toString(),Integer.parseInt(doc.getData().get("dayOfWeek").toString()),Integer.parseInt(doc.getData().get("time").toString()), doc.getData().get("tutorEmail").toString(),doc.getData().get("tuteeEmail").toString()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
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
            TextView status = convertView.findViewById(R.id.status);
            Button accept = convertView.findViewById(R.id.reject);
            Button reject = convertView.findViewById(R.id.Apply);
            accept.setVisibility(View.INVISIBLE);
            reject.setVisibility(View.INVISIBLE);
            status.setVisibility(View.VISIBLE);
            Request gp = Groups.get(position);
            name.setText(gp.getTutor());
            course.setText(gp.getSubject());
            status.setText(gp.getStatus());
            period.setText(String.valueOf(gp.getTime())+":00 - " + String.valueOf(gp.getTime()+1)+":00");
            return convertView;
        }
    }

    public void searchRequest(String tuteeUN) {
        Query query = requestRefe.whereEqualTo("tutee", tuteeUN);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            groups.clear();
                            boolean isExist = false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                Request requestNew = new Request(document.getData().get("tutee").toString(),document.getData().get("tutor").toString(),document.getData().get("subject").toString(),Integer.parseInt(document.getData().get("dayOfWeek").toString()),Integer.parseInt(document.getData().get("time").toString()), document.getData().get("tutorEmail").toString(),document.getData().get("tuteeEmail").toString());
                                requestNew.setStatus(document.getData().get("status").toString());
                                groups.add(requestNew);

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
}
