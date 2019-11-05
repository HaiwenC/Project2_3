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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import model.Request;
import model.Tutee;
import model.Tutor;


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
        groups.add(new Request(new Tutee("1", "1", "Beiyou","1", "aaaaaaaaa"),
                new Tutor("1", "1", "!", "1", "aaaaaaaaa"), "csci109", 1 ,8, 9));
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.requestlayout, null);
            }
            TextView name = convertView.findViewById(R.id.RequestName);
            TextView course = convertView.findViewById(R.id.RequestCourse);
            TextView period = convertView.findViewById(R.id.TimePeriod);
            Request gp = Groups.get(position);
            name.setText(gp.getTutee().getName());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime()));
            return convertView;
        }
    }
}
