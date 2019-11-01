package csci310.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TuteeHome extends AppCompatActivity {
    private Button profile;
    private Button Search;
    private Button session;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuteepage);
        profile = findViewById(R.id.ProfileButton);
        Search = findViewById(R.id.Search);
        session = findViewById(R.id.TuteeSession);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TuteeProfile.class);
                startActivity(i);
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SearchPage.class);
                startActivity(i);
            }
        });
        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SessionHistory.class);
                startActivity(i);
            }
        });
    }
}
