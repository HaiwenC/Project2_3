package model;


import java.util.ArrayList;
import java.util.*;
// @Philip since we know what all known sections will be in our dropdown,
// let's use enum to keep a range of constants and just add/delete from this list
//enum Session {
//    CSCI109, CSCI102, CSCI103, CSCI104, CSCI201, CSCI270,CSCI310,CSCI356, CSCI350, CSCI360,
//    EE109, EE250, EE364;
//}


public class Tutor extends Student {

    private ArrayList<Session> sectionsTeaching;

    private ArrayList<Integer> week;
    private ArrayList<Integer> time;
    private ArrayList<String> subject;

    private ArrayList<Request> requestsReceived;

//
//    public void addTime(int week, int startTime){
//        calendars.get(week).add(startTime);
//    }
//
//    public void removeTime(int week, int startTime){
//        calendars.get(week).remove(startTime);
//    }
//
//    public void editTime(int week, int oldTime, int newTime){
//        calendars.get(week).remove(oldTime);
//        calendars.get(week).remove(newTime);
//    }

    private double ratingTotal;
    private int numRatings;
    private ArrayList<String> reviews;

    public Tutor(String studentID, String email, String name, String username, String password) {
        super(studentID, email, name, username, password);
        sectionsTeaching = new ArrayList<Session>();
        numRatings=0;
        ratingTotal=0;
//        reviews = "";
    }


    public double getRating(){
        return ratingTotal/numRatings;
    }

    public void addRating(int rate){
        ratingTotal += rate;
        numRatings++;
    }

    public ArrayList<Integer> getDay() {
        return week;
    }

    public ArrayList<Integer> getTime() {
        return time;
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public void setTime(ArrayList<Integer> time) {
        this.time = time;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }

    public void setWeek(ArrayList<Integer> week) {
        this.week = week;
    }

    public void addObject(int W, int T, String S){

        week.add(W);
        time.add(T);
        subject.add(S);
    }

    public void removeObject(int index){
        week.remove(index);
        time.remove(index);
        subject.remove(index);
    }


    public boolean addSections(Session s, int time, int week) {
        if (sectionsTeaching.contains(s)) {return false;}
        else {
            for (int i = 0 ; i < requestsReceived.size(); i++ ){
                if(requestsReceived.get(i).getDayOfWeek() == week && requestsReceived.get(i).getTime() == time) {
                    requestsReceived.get(i).rejectRequest();
                    removeObject(i);
                }
            }
            sectionsTeaching.add(s);
        }
        return true;
    }
    public boolean removeSections(Session s) {
       return sectionsTeaching.remove(s);
        // returns true if removed, false otherwise
    }

    public void addReview(String newReview){
        reviews.add(newReview);
    }

    public ArrayList<String> getReviews() {
        // perform sql command to retrieve all reviews into resultset
        // and store that list in result set to this reviews arraylist
        // On the other hand, should this not be a string, but Review objects
        // because reviews will also hold session info like rating, student, subject, etc.
        return reviews;
    }

    public ArrayList<Request> getRequestsReceived() {
        return requestsReceived;
    }

    public void setRequestsReceived(ArrayList<Request> requestsReceived) {
        this.requestsReceived = requestsReceived;
    }

}
