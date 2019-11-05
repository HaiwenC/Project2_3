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
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import model.Request;
import model.Session;
import model.Tutee;
import model.Tutor;

import static csci310.myapplication.MainActivity.requestRefe;
import static csci310.myapplication.MainActivity.sessionRefe;


public class TutorHome extends AppCompatActivity {
    private Button profile;
    private Button session;
    private ListView list;
    private RequestAdapter adapter;
    public static List<Request> groups = new ArrayList<Request>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorpage);
        list = findViewById(R.id.listNotes);
        session = findViewById(R.id.Session);
        Log.d("debug listview", list.toString());
//        groups.add(new Request(new Tutee("1", "1", "Beiyou","1", "aaaaaaaaa"),
//                new Tutor("1", "1", "!", "1", "aaaaaaaaa"), "csci109", 1 ,8, 9));
        searchRequest("tommy");
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
            Button accept = convertView.findViewById(R.id.reject);
            Button reject = convertView.findViewById(R.id.Apply);
            final Request gp = Groups.get(position);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groups.remove(position);
                    adapter.notifyDataSetChanged();
                    gp.setStatus("accepted");
                    requestRefe.document(gp.getTutor()+gp.getTutee()).set(gp);
                    Session sessionNew = new Session(gp.getTutor(),gp.getTutee(),gp.getSubject(),gp.getDayOfWeek(),gp.getTime());
                    sessionRefe.document(gp.getTutor()+gp.getTutee()).set(sessionNew);
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    groups.remove(position);
                    adapter.notifyDataSetChanged();
                    gp.setStatus("rejected");
                    requestRefe.document(gp.getTutor()+gp.getTutee()).set(gp);
                }
            });

            name.setText(gp.getTutee());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime()));
            return convertView;
        }
    }

    private void searchRequest(String tutorUN) {
        Query query = requestRefe.whereEqualTo("tutorUsername", tutorUN);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            boolean isExist = false;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("qqqq", document.getId() + " => " + document.getData());
                                isExist = true;
                                Request requestNew = new Request(document.getData().get("tuteeUsername").toString(),document.getData().get("tutorUsername").toString(),document.getData().get("subject").toString(),Integer.parseInt(document.getData().get("day").toString()),Integer.parseInt(document.getData().get("time").toString()));
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
