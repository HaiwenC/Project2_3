package model;

import java.util.ArrayList;

public class Session {
    private Tutee tutee;
    private Tutor tutor;

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

    private ArrayList<Integer> weekList = tutor.getDay();
    private ArrayList<Integer> timeList = tutor.getTime();
    private ArrayList<String> subjectList = tutor.getSubject();

    public ArrayList<Integer> getTimeList() {
        return timeList;
    }

    public ArrayList<Integer> getWeekList() {
        return weekList;
    }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList<String> subjectList) {
        this.subjectList = subjectList;
    }

    public void setTimeList(ArrayList<Integer> timeList) {
        this.timeList = timeList;
    }

    public void setWeekList(ArrayList<Integer> weekList) {
        this.weekList = weekList;
    }
}
