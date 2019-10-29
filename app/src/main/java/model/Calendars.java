package model;
import java.util.*;
public class Calendars {
    List<Calendar> myCalendars;

    public List<Calendar> getMyCalendars() {
        return myCalendars;
    }

    public void setMyCalendars(List<Calendar> myCalendars) {
        this.myCalendars = myCalendars;
    }

    public void addMyCalendars(Calendar calendar){
        myCalendars.add(calendar);
    }

    public void removeMyCalendars(Calendar calendar){
        myCalendars.remove(calendar);
    }
}
