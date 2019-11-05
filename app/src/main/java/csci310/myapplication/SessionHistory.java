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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import model.Request;
import model.Tutee;
import model.Tutor;


public class SessionHistory extends AppCompatActivity {
    private ListView list;
    private SessionAdapter adapter;
    public static List<Request> groups = new ArrayList<Request>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessionhistory);
        list = findViewById(R.id.sessionHistory);
        Log.d("debug listview", list.toString());
        groups.add(new Request(new Tutee("1", "1", "Beiyou","1", "aaaaaaaaa"),
                new Tutor("1", "1", "!", "1", "aaaaaaaaa"), "csci109", 1 ,8, 9));
        adapter = new SessionAdapter(getApplicationContext(), 0, groups);
        //Log.d("debug adapter", adapter.toString());
        list.setAdapter(adapter);
    }
    private class SessionAdapter extends ArrayAdapter<Request> {
        List<Request> Groups;
        public SessionAdapter(Context context, int resource, List<Request> objects) {
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
            Request gp = Groups.get(position);
            name.setText(gp.getTutee().getName());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime()));
            return convertView;
        }
    }
}
