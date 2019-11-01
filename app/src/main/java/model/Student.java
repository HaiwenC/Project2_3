package model;

import android.se.omapi.Session;

import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * <h3>The Student class.</h3>
 *
 * <ul>
 *     Members:
 * <li>String: studentID;
 * <li>String: email;
 * <li>Calendar: calendar
 * <li>List<Request>: requestList
 * <li>List<Session>: sessionList
 * </ul>
 * <p>
 * @author      Mingchen Zhang
 */
abstract class Student {
    private String studentID;
    private String fName;
    private String lName;
    private String email;
    private String name;
    private Calendar calendar;
    List<Request> requestList;
    List<Session> sessionList;


<<<<<<< HEAD
    Student(String sID, String e, String fn, String ln) {
=======
    Student(String sID, String e, String n) {
        name = n;
>>>>>>> 161e56cdd9900b24b6cf1767e91e8c3d6fbcfef7
        studentID = sID;
        email = e;
        fName = fn;
        lName = ln;
    }

    public String getName(){
        return name;
    }

    /**
     * get calendar
     */
    public Calendar getCalendar() {

        return calendar;
    }
    /**
     * set calendar
     * @param calendar: The new calendar to set
     */
    public void setCalendar(Calendar calendar) {

        this.calendar = calendar;
    }
    /**
     * get requestList
     */
    public List<Request> getRequestList() {

        return requestList;
    }
    /**
     * set requestList
     * @param requestList: The new calendar to set
     */
    public void setRequestList(List<Request> requestList) {

        this.requestList = requestList;
    }
    /**
     * get sessionList
     */
    public List<Session> getSessionList() {

        return sessionList;
    }
    /**
     * set sessionList
     * @param sessionList: The new calendar to set
     */
    public void setSessionList(List<Session> sessionList) {

        this.sessionList = sessionList;
    }
    /**
     * get email
     */
    public String getEmail() {

        return email;
    }
    /**
     * set setEmail
     * @param email: The new calendar to set
     */
    public void setEmail(String email) {

        this.email = email;
    }
    /**
     * get studentID
     */
    public String getStudentID() {

        return studentID;
    }
    /**
     * set setEmail
     * @param studentID: The new calendar to set
     */
    public void setStudentID(String studentID) {

        this.studentID = studentID;
    }

        /**
         * validate email
         */
        public boolean validateEmail(String email){
            //Regex email verification
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            String[] data = email.split("@", 2);
            if (data.length != 2 || !data[1].equalsIgnoreCase("usc.edu") || email == null)
                return false;
            return pat.matcher(email).matches();
        }

}
