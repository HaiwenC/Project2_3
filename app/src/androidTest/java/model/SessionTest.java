package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void getTutorEmail() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutorEmail();

        assertEquals(check, tutorEmail);

    }

    @Test
    public void setTutorEmail() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutorEmail();

        sample.setTutorEmail("newemail@usc.edu");

        String check2 = sample.getTutorEmail();

        assertEquals(check2, "newemail@usc.edu");

        assertNotEquals(check,check2);

    }

    @Test
    public void getTuteeEmail() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTuteeEmail();

        assertEquals(check, tuteeEmail);

    }

    @Test
    public void setTuteeEmail() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTuteeEmail();

        sample.setTuteeEmail("newemail@usc.edu");

        String check2 = sample.getTuteeEmail();

        assertEquals(check2, "newemail@usc.edu");

        assertNotEquals(check,check2);

    }

    @Test
    public void getTutee() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutee();

        assertEquals(check, tutee);

    }

    @Test
    public void setTutee() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutee();

        sample.setTutee("newTutee");

        String check2 = sample.getTutee();

        assertEquals(check2, "newTutee");

        assertNotEquals(check,check2);

    }

    @Test
    public void getTutor() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutor();

        assertEquals(check, tutor);

    }

    @Test
    public void setTutor() {


        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getTutor();

        sample.setTutor("NewTutor");

        String check2 = sample.getTutor();

        assertEquals(check2, "NewTutor");

        assertNotEquals(check,check2);

    }

    @Test
    public void getSubject() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getSubject();

        assertEquals(check, subject);

    }

    @Test
    public void setSubject() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        String check = sample.getSubject();

        sample.setSubject("CSCI310");

        String check2 = sample.getSubject();

        assertEquals(check2, "CSCI310");

        assertNotEquals(check,check2);

    }

    @Test
    public void getDayOfWeek() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        int check = sample.getDayOfWeek();

        sample.setDayOfWeek(3);

        int check2 = sample.getDayOfWeek();

        assertEquals(check2, 3);

        assertNotEquals(check,check2);
    }

    @Test
    public void setDayOfWeek() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 1;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        int check = sample.getDayOfWeek();

        sample.setDayOfWeek(2);

        int check2 = sample.getDayOfWeek();

        assertEquals(check2, 2);

        assertNotEquals(check,check2);

    }

    @Test
    public void getTime() {
        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 2;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        int check = sample.getTime();

        assertEquals(check, time);

    }

    @Test
    public void setTime() {

        String tutee = "Angela";
        String tutor = "Kingston";
        String subject = "CSCI201";
        int dayOfWeek = 1;
        int time = 15;
        String tutorEmail = "angela@usc.edu";
        String tuteeEmail = "king@usc.edu";

        Session sample = new Session(tutor,tutee,subject,dayOfWeek,time,tutorEmail,tuteeEmail);

        int check = sample.getTime();

        sample.setTime(5);

        int check2 = sample.getTime();

        assertEquals(check2, 5);

        assertNotEquals(check,check2);

    }

}