package csci310.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class updateProfile extends AppCompatActivity {
    private CalendarView CV;
    private EditText name;
    private int day = 1;
    private TextView DayOfWeek;
    private String[] week = new String[]{"SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT"};
    private Spinner courseSpinner;
    private Spinner timeSpinner;
    private Button saveBut;
    int dayOfWeek;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileupdate);
        name = findViewById(R.id.EditName);
        courseSpinner = findViewById(R.id.courseSpinner);
        CV = findViewById(R.id.calendarView);
        DayOfWeek = findViewById(R.id.textView3);
        saveBut = findViewById(R.id.SaveBut);
        timeSpinner = findViewById(R.id.spinnerTimePeriod);
        DayOfWeek.setText(MainActivity.tutorInfo.getName());
        CV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                if (DayOfWeek == null) {
                    DayOfWeek = findViewById(R.id.textView3);
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Log.d("day output", String.valueOf(dayOfWeek));
                DayOfWeek.setText(week[dayOfWeek-1]);
            }
        });
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String course = courseSpinner.getSelectedItem().toString();
                MainActivity.tutorInfo.setSubjectNew(course);
                final String time = timeSpinner.getSelectedItem().toString();
                MainActivity.tutorInfo.setTimeNew(Integer.valueOf(time.substring(0, 2)));
                MainActivity.tutorInfo.setWeekNew(dayOfWeek);
                MainActivity.tutorInfo.setName(name.getText().toString());
                MainActivity.tutorRefe.document(MainActivity.tutorInfo.getUsername()).set(MainActivity.tutorInfo);
                //MainActivity.tuteeRefe.document(MainActivity.tuteeInfo.getUsername()).set(MainActivity.tuteeInfo);
                Toast.makeText(getApplicationContext(), "Successfully updated your profile", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
