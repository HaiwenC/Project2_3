package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarTest {

    @Test
    public void getAvailable() {
        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        int check = tester.getAvailable();

        assertEquals(tester.getAvailable(),available);

    }

    @Test
    public void setAvailable() {

        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        int check = tester.getAvailable();

        tester.setAvailable(3);

        int check2 = tester.getAvailable();

        assertEquals(check2,3);
        assertNotEquals(check,check2);

    }

    @Test
    public void getLength() {

        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        int check = tester.getLength();

        assertEquals(check,length);

    }

    @Test
    public void setLength() {


        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        int check = tester.getLength();

        tester.setLength(1);

        int check2 = tester.getLength();

        assertEquals(check2,1);
        assertNotEquals(check,check2);

    }

    @Test
    public void getLocation() {

        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        String check = tester.getLocation();

        assertEquals(check,location);


    }

    @Test
    public void setLocation() {

        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        String check = tester.getLocation();

        tester.setLocation("TCC");

        String check2 = tester.getLocation();

        assertEquals(check2,"TCC");
        assertNotEquals(check,check2);

    }

    @Test
    public void getTutorName() {
        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        String check = tester.getTutorName();

        assertEquals(check,tutor);

    }

    @Test
    public void setTutorName() {

        int available = 5;
        int length = 2;
        String location = "SAL109A";
        String tutor = "Jack";

        Calendars tester = new Calendars(available,length,location,tutor);

        String check = tester.getTutorName();

        tester.setTutorName("Phil");

        String check2 = tester.getTutorName();

        assertEquals(check2,"Phil");
        assertNotEquals(check,check2);

    }
}