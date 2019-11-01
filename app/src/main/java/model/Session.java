package model;

import java.util.ArrayList;

public class Session {
    private Tutee tutee;
    private Tutor tutor;
    private String subject;
    private int dayOfWeek;
    private int time;


    private ArrayList<Integer> weekList;
    private ArrayList<Integer> timeList;
    private ArrayList<String> subjectList;

    public Session(Tutor tutor, Tutee tutee, String subject, int dayOfWeek, int time){
        this.tutee = tutee;
        this.tutor = tutor;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.time = time;

        weekList = tutor.getDay();
        timeList = tutor.getTime();
        subjectList = tutor.getSubject();
    }

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
