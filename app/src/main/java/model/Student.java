package model;

import android.se.omapi.Session;

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
    private String email;
    private Calendar calendar;
    List<Request> requestList;
    List<Session> sessionList;

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


}