package csci310.myapplication.model;

import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Session {

    private String tutor;
    private String tutee;
    private String timeStart;
    private String timeEnd;
    private int rate;
    private String review;




    public Session() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Session(String tutor, String tutee, String timeStart, String timeEnd, int rate, String review) {
        this.tutor     = tutor;
        this.tutee     = tutee;
        this.timeStart = timeStart;
        this.timeEnd   = timeEnd;
        this.rate      = rate;
        this.review    = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
