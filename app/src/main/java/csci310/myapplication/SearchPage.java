package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import model.Tutor;

public class SearchPage extends AppCompatActivity {
    private Button save;
    private Button search;
    private ListView list;
    private SearchAdapter adapter;
    private List<Tutor> Requests = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpage);
        list = findViewById(R.id.listOfSearch);
        save = findViewById(R.id.SaveBut);
        search = findViewById(R.id.SearchBut);
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
            Tutor tutor = Groups.get(position);
            name.setText(tutor.getName());
            course.setText(tutor.getSubjectNew());
            period.setText(String.valueOf(tutor.getTimeNew()));
            return convertView;
        }
    }
}
