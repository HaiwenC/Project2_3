package model;
import java.util.*;
/**
 * <h3>The Calendars class.</h3>
 *
 * <ul>
 *     Members:
 * <li>Set<Calendar> myCalendars
 * </ul>
 * <p>
 * @author      Mingchen Zhang
 */
public class Calendars {

    private int available;
    private int length;
    private String location;
    private String tutorName;
    private Set<Integer> myCalendars;

    public Calendars(int a,int l, String loc, String tutor){
        available = a;
        length = l;
        location = loc;
        tutorName = tutor;
    }



    /**
     * get myCalendars
     */
    public Set<Integer> getMyCalendars() {
        return myCalendars;
    }
    /**
     * set myCalendars
     */
    public void setMyCalendars(Set<Integer> myCalendars) {
        this.myCalendars = myCalendars;
    }
    /**
     * add calendar to myCalendars
     */
    public void addMyCalendars(Integer calendar){
        myCalendars.add(calendar);
    }
    /**
     * remove calendar to myCalendars
     */
    public void removeMyCalendars(Integer calendar){
        myCalendars.remove(calendar);
    }
    /**
     * check calendar in myCalendars
     */
    public boolean checkAvalibleTime(Integer calendar){
        if (myCalendars.contains(calendar))
            return true;
        return false;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }
}
