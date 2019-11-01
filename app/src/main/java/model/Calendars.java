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
    private Set<Calendar> myCalendars;
    /**
     * get myCalendars
     */
    public Set<Calendar> getMyCalendars() {
        return myCalendars;
    }
    /**
     * set myCalendars
     */
    public void setMyCalendars(Set<Calendar> myCalendars) {
        this.myCalendars = myCalendars;
    }
    /**
     * add calendar to myCalendars
     */
    public void addMyCalendars(Calendar calendar){
        myCalendars.add(calendar);
    }
    /**
     * remove calendar to myCalendars
     */
    public void removeMyCalendars(Calendar calendar){
        myCalendars.remove(calendar);
    }
    /**
     * check calendar in myCalendars
     */
    public boolean checkAvalibleTime(Calendar calendar){
        if (myCalendars.contains(calendar))
            return true;
        return false;
    }
}
