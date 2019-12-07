package csci310.myapplication;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import model.Tutee;

import static csci310.myapplication.MainActivity.requestRefe;
import static csci310.myapplication.MainActivity.tuteeInfo;
import static csci310.myapplication.MainActivity.tuteeRefe;
import static csci310.myapplication.MainActivity.tutorRefe;

public class TuteeProfile extends AppCompatActivity {
    private EditText name;
    private Button update;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(tuteeInfo==null) {
            tuteeInfo = new Tutee("1234567890","testTutee@usc.edu","testTutee","testTutee","abcd1234");
        }
        setContentView(R.layout.tuteeprofilepage);
        name = findViewById(R.id.EditUsername);
        update = findViewById(R.id.SaveBut);
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

    public void modifyName(String username,String newName){
        tuteeRefe.document(username).set(new Tutee("1234567890","testTutee@usc.edu",newName,"testTutee","abcd1234"));
    }
}
