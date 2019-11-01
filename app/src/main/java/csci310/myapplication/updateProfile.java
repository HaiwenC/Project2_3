package csci310.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class updateProfile extends AppCompatActivity {
    private CalendarView CV;
    private int day = 1;
    private TextView DayOfWeek;
    private String[] week = new String[]{"MON", "TUE", "WED", "THUR", "FRI"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileupdate);
        CV = findViewById(R.id.calendarView);
        DayOfWeek = findViewById(R.id.textView3);
        CV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                if (DayOfWeek == null) {
                    DayOfWeek = findViewById(R.id.textView3);
                }
                Calendar selected = Calendar.getInstance();
                selected.setTimeInMillis(calendarView.getDate());
                day = selected.get(Calendar.DAY_OF_WEEK);
                Log.d("day", String.valueOf(selected.getTime().toString()));
                DayOfWeek.setText(week[day-1]);
            }
        });
    }
}
