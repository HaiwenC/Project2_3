package model;

import java.util.ArrayList;

public class Session {
    Tutee tutee;
    Tutor tutor;

    public Tutee getTutee() {
        return tutee;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutee(Tutee tutee) {
        this.tutee = tutee;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    ArrayList<Integer> weekList = tutor.getDay();
    ArrayList<Integer> timeList = tutor.getTime();
    ArrayList<String> subjectList = tutor.getSubject();
}
