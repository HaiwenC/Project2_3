package csci310.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TuteeProfile extends AppCompatActivity {
    private EditText name;
    private Button update;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuteeprofilepage);
        name = findViewById(R.id.EditUsername);
        update.findViewById(R.id.SaveBut);
        name.setText(MainActivity.tuteeInfo.getName());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tuteeInfo.setName(name.getText().toString());
                MainActivity.tuteeRefe.document(MainActivity.tuteeInfo.getUsername()).set(MainActivity.tuteeInfo);
                Toast.makeText(getApplicationContext(), "User updated", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
