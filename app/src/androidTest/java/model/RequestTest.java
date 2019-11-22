package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTutorEmail() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";

        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);
        String tutorEmail2 = user.getTutorEmail();
        assertEquals(tutorEmail,tutorEmail2);
    }

    @Test
    public void setTutorEmail() {
        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String tutorEmail1 = user.getTutorEmail();

        String newEmail = "hello@usc.edu";
        user.setTutorEmail(newEmail);

        assertEquals(user.getTutorEmail(), newEmail);
        assertNotEquals(user.getTutorEmail(),tutorEmail1);

    }

    @Test
    public void getTuteeEmail() {
        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);
        String tuteeEmail2 = user.getTuteeEmail();
        assertEquals(tuteeEmail,tuteeEmail2);
    }

    @Test
    public void setTuteeEmail() {
        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String tuteeEmail1 = user.getTuteeEmail();

        String newEmail = "hello@usc.edu";
        user.setTuteeEmail(newEmail);

        assertEquals(user.getTuteeEmail(), newEmail);
        assertNotEquals(user.getTuteeEmail(),tuteeEmail1);
    }

    @Test
    public void acceptRequest() {
        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String start = user.getStatus();

        user.acceptRequest("accepted");

        String newStatus = user.getStatus();

        assertNotEquals(start,newStatus);
        assertEquals(newStatus,"accepted");

    }

    @Test
    public void getDayOfWeek() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        int getDay = user.getDayOfWeek();

        assertEquals(getDay,dayOfWeek);

    }

    @Test
    public void getTime() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        int getTime = user.getTime();

        assertEquals(getTime,time);

    }

    @Test
    public void getTutor() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String tutor2 = user.getTutor();

        assertEquals(tutor2,tutor);

    }

    @Test
    public void getTutee() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String tutee2 = user.getTutee();

        assertEquals(tutee2,tutee);

    }


    @Test
    public void getSubject() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String subject2 = user.getSubject();

        assertEquals(subject2,subject);

    }

    @Test
    public void getStatus() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        String check = user.getStatus();

        assertEquals(check,"available");

    }

    @Test
    public void setStatus() {

        String tutee = "KevinLee";
        String tutor = "Sammy";
        String subject = "CSCI310";
        int dayOfWeek = 2;
        int time = 17;
        String tutorEmail = "kevin@usc.edu";
        String tuteeEmail = "sam@usc.edu";

        Request user = new Request(tutee, tutor, subject, dayOfWeek, time, tutorEmail, tuteeEmail);

        user.setStatus("busy");

        assertEquals(user.getStatus(),"busy");

    }
}