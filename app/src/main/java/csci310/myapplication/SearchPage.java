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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import model.Request;
import model.Tutee;
import model.Tutor;

import static csci310.myapplication.MainActivity.tuteeRefe;
import static csci310.myapplication.MainActivity.tutorRefe;

public class SearchPage extends AppCompatActivity {
    private Button save;
    private Button search;
    private ListView list;
    private SearchAdapter adapter;
    private Spinner spinner_subject;
    private Spinner spinner_day;
    private Spinner spinner_period;
    private String subject;
    private int period;
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
                    Request r = new Request(MainActivity.tuteeInfo.getName(), tutor.getName(), tutor.getSubjectNew(), tutor.getWeekNew() ,tutor.getTimeNew());
                    MainActivity.requestRefe.document(tutor.getName()+MainActivity.tuteeInfo.getName()).set(r);
                    view.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "application sent", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }
}
