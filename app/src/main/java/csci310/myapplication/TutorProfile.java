package csci310.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TutorProfile extends AppCompatActivity {
    private Button update;
    private TextView name;
    private TextView courseName;
    private TextView Dayofweek;
    private TextView email;
    private TextView period;
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
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), updateProfile.class);
                startActivity(i);
            }
        });
        if (MainActivity.tutorInfo == null) {
            name.setText("tutor is null");
        }
        //name.setText(MainActivity.tutorInfo.getName());
        //courseName.setText(MainActivity.tutorInfo.getSubjectNew());
        //period.setText(MainActivity.tutorInfo.getTimeNew());
        //Dayofweek.setText(week[MainActivity.tutorInfo.getWeekNew()%7]);
    }
}
