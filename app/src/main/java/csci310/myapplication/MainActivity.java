package csci310.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button Reg;
    private Button Login;
    private RadioGroup RButton;
    private boolean tutee = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RButton = findViewById(R.id.radioGroup);
        Reg = findViewById(R.id.ButRegister);
        Login = findViewById(R.id.ButLogin);
        if(RButton.getCheckedRadioButtonId() == R.id.radioTutee){
            tutee = true;
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i;
                if(tutee){
                    i = new Intent(getApplicationContext(),TuteeHome.class);
                }
                else{
                    i = new Intent(getApplicationContext(),TutorHome.class);
                }
                startActivity(i);
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Registration.class);
                startActivity(i);
            }
        });
        RButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioTutee){
                    tutee = true;
                }
                else if(i == R.id.radioTutor){
                    tutee = false;
                }
            }
        });
    }
}
