package csci310.myapplication.model;

import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Request {

    private String tutor;
    private String tutee;
    private String timeStart;
    private String timeEnd;
    private int isAccept;




    public Request() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

//    isAccept:0-not decided; 1-accept; 2-deny
    public Request(String tutor, String tutee, String timeStart, String timeEnd, int isAccept) {
        this.tutor     = tutor;
        this.tutee     = tutee;
        this.timeStart = timeStart;
        this.timeEnd   = timeEnd;
        this.isAccept  = isAccept;

    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTutee() {
        return tutee;
    }

    public void setTutee(String tutee) {
        this.tutee = tutee;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }
}
