package model;

import java.util.ArrayList;

public class Session {
    private String tutee;
    private String tutor;
    private String subject;
    private int dayOfWeek;
    private int time;
    private String review;
    private String tutorEmail;
    private String tuteeEmail;
//
//    private ArrayList<Integer> weekList;
//    private ArrayList<Integer> timeList;
//    private ArrayList<String> subjectList;

    public Session(String tutorName, String tuteeName, String subject, int dayOfWeek, int time,String tutorEmail, String tuteeEmail){
        this.tutee = tuteeName;
        this.tutor = tutorName;
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.review = "";
        this.tutorEmail = tutorEmail;
        this.tuteeEmail =tuteeEmail;
//
//        weekList = tutor.getDay();
//        timeList = tutor.getTime();
//        subjectList = tutor.getSubject();
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getTuteeEmail() {
        return tuteeEmail;
    }

    public void setTuteeEmail(String tuteeEmail) {
        this.tuteeEmail = tuteeEmail;
    }

    public String getTutee() {
        return tutee;
    }

    public void setTutee(String tutee) {
        this.tutee = tutee;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
