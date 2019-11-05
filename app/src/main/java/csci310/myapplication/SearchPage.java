package csci310.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import model.Request;

public class SearchPage extends AppCompatActivity {
    private Button save;
    private Button search;
    private ListView list;
    private SearchAdapter adapter;
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
    }
    private class SearchAdapter extends ArrayAdapter<Request> {
        List<Request> Groups;
        public SearchAdapter(Context context, int resource, List<Request> objects) {
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
            Request gp = Groups.get(position);
            name.setText(gp.getTutee().getName());
            course.setText(gp.getSubject());
            period.setText(String.valueOf(gp.getTime()));
            return convertView;
        }
    }
}
