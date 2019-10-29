package model;
import java.util.*;
public class Calendars {
    Set<Calendar> myCalendars;

    public Set<Calendar> getMyCalendars() {
        return myCalendars;
    }

    public void setMyCalendars(Set<Calendar> myCalendars) {
        this.myCalendars = myCalendars;
    }

    public void addMyCalendars(Calendar calendar){
        myCalendars.add(calendar);
    }

    public void removeMyCalendars(Calendar calendar){
        myCalendars.remove(calendar);
    }

    public boolean checkAvalibleTime(Calendar calendar){
        if (myCalendars.contains(calendar))
            return true;
        return false;
    }
}
