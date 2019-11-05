package csci310.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TutorProfile extends AppCompatActivity {
    private Button update;
    private Button returnToHome;
    volatile private TextView name;
    volatile private TextView courseName;
    volatile private TextView Dayofweek;
    volatile private TextView email;
    volatile private TextView period;
    private String[] week = new String[]{"SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);
        name = findViewById(R.id.TutorName);
        courseName = findViewById(R.id.CourseNameId);
        email = findViewById(R.id.ProfileEmail);
        Dayofweek = findViewById(R.id.ProfileDayOfWeek);
        period = findViewById(R.id.ProfileTimePeriod);
        update = findViewById(R.id.SaveBut);
        returnToHome = findViewById(R.id.returnToHome);
        returnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TutorHome.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), updateProfile.class);
                startActivity(i);
            }
        });
        name.setText(MainActivity.tutorInfo.getName());
        Log.d("qqqq",MainActivity.tutorInfo.getSubjectNew());
        courseName.setText(MainActivity.tutorInfo.getSubjectNew());
        int timeStart = MainActivity.tutorInfo.getTimeNew();
        int timeEnd = timeStart + 1;
        String strTimeStart = Integer.toString(timeStart);
        if (strTimeStart.length() == 1) strTimeStart = "0" + strTimeStart;
        String strTimeEnd = Integer.toString(timeEnd);
        if (strTimeEnd.length() == 1) strTimeEnd = "0" + strTimeEnd;
        String periodRange = strTimeStart + ":00 - " + strTimeEnd + ":00";
        period.setText(periodRange);
        email.setText(MainActivity.tutorInfo.getEmail());
        Dayofweek.setText(week[MainActivity.tutorInfo.getWeekNew()%7]);

    }
}
