package model;

import java.util.ArrayList;

public class Session {
    private String tuteeName;
    private String tutorName;
    private String subject;
    private int day;
    private int time;
    private String review;

//
//    private ArrayList<Integer> weekList;
//    private ArrayList<Integer> timeList;
//    private ArrayList<String> subjectList;

    public Session(String tutorName, String tuteeName, String subject, int dayOfWeek, int time){
        this.tuteeName = tuteeName;
        this.tutorName = tutorName;
        this.subject = subject;
        this.day = dayOfWeek;
        this.time = time;
        this.review = "";
//
//        weekList = tutor.getDay();
//        timeList = tutor.getTime();
//        subjectList = tutor.getSubject();
    }

    public String getTutee() {
        return tuteeName;
    }

    public void setTutee(String tutee) {
        this.tuteeName = tutee;
    }

    public String getTutor() {
        return tutorName;
    }

    public void setTutor(String tutor) {
        this.tutorName = tutor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDayOfWeek() {
        return day;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.day = dayOfWeek;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

//
//    public ArrayList<Integer> getTimeList() {
//        return timeList;
//    }
//
//    public ArrayList<Integer> getWeekList() {
//        return weekList;
//    }
//
//    public ArrayList<String> getSubjectList() {
//        return subjectList;
//    }
//
//    public void setSubjectList(ArrayList<String> subjectList) {
//        this.subjectList = subjectList;
//    }
//
//    public void setTimeList(ArrayList<Integer> timeList) {
//        this.timeList = timeList;
//    }
//
//    public void setWeekList(ArrayList<Integer> weekList) {
//        this.weekList = weekList;
//    }
}
