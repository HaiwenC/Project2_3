package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import model.Request;
import model.Session;
import model.Tutee;
import model.Tutor;

import static csci310.myapplication.MainActivity.sessionRefe;
import static csci310.myapplication.MainActivity.tutorRefe;


public class SessionHistory extends AppCompatActivity {
    private ListView list;
    private SessionAdapter adapter;
    private String tutorName;
    public static List < Session > groups = new ArrayList < Session > ();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionhistory);
        list = findViewById(R.id.sessionHistory);
        tutorName = "Math";


        //        list.l(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        Query query = sessionRefe.whereEqualTo("tutorName", "tommy");
        query.get()
                .addOnCompleteListener(new OnCompleteListener < QuerySnapshot > () {
                    @Override
                    public void onComplete(@NonNull Task < QuerySnapshot > task) {
                        boolean isExist = false;
                        ////                                String password = "";
                        ////                                String studentID = "";
                        ////                                String name      = "";
                        ////                                String username  = "";
                        ////                                String email     = "";
                        int day = -1;
                        String review = "";
                        String subject = "";
                        int time = -1;
                        String tuteeName = "";
                        String tutorName = "";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                Log.d("stark", document.getId() + " => " + document.getData());
                                isExist = true;
                                day = Integer.parseInt(document.getData().get("day").toString());
                                time = Integer.parseInt(document.getData().get("time").toString());
                                review = document.getData().get("review").toString();
                                subject = document.getData().get("subject").toString();
                                tuteeName = document.getData().get("tuteeName").toString();
                                tutorName = document.getData().get("tutorName").toString();
                                //                                        Tutor tutorNew = new Tutor(studentID, email ,name, username, password);
                                Session curSession = new Session(tutorName, tuteeName, subject, day, time);
                                groups.add(curSession);
                            }
                            if (isExist) {
                                Toast.makeText(getApplicationContext(), "This session is existed", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            } else {
                                CollectionReference citiesRefInside;
                                Toast.makeText(getApplicationContext(), "session added", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Log.d("qqqq", "Error, can't run query");
                        }
                    }
                });
        //            }
        //        });





        Log.d("debug listview", list.toString());
        //        groups.add(new Session());
        adapter = new SessionAdapter(getApplicationContext(), 0, groups);
        //Log.d("debug adapter", adapter.toString());
        list.setAdapter(adapter);
    }
    private class SessionAdapter extends ArrayAdapter < Session > {
        List < Session > Groups;
        public SessionAdapter(Context context, int resource, List < Session > objects) {
            super(context, resource, objects);
            this.Groups = objects;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.requestlayout, null);
            }
            TextView name = convertView.findViewById(R.id.RequestName);
            TextView course = convertView.findViewById(R.id.RequestCourse);
            TextView period = convertView.findViewById(R.id.TimePeriod);
            Button accept = convertView.findViewById(R.id.Apply);
            Button reject = convertView.findViewById(R.id.reject);
            accept.setVisibility(View.INVISIBLE);
            reject.setVisibility(View.INVISIBLE);
            Session gp = Groups.get(position);
            name.setText(gp.getTutee());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime())+":00 - " + String.valueOf(gp.getTime()+1)+":00");
            return convertView;
        }
    }
}