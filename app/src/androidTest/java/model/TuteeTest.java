package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TuteeTest {

    @Test
    public void setName() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getName();

        user.setName("newName");

        String check2 = user.getName();

        assertEquals(check2, "newName");
        assertNotEquals(check,check2);
    }

    @Test
    public void getUsername() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getUsername();

        assertEquals(check, username);

    }

    @Test
    public void setUsername() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getUsername();

        user.setUsername("newName");

        String check2 = user.getUsername();

        assertEquals(check2, "newName");
        assertNotEquals(check,check2);
    }

    @Test
    public void getPassword() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getPassword();

        assertEquals(check, password);
    }

    @Test
    public void setPassword() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getPassword();

        user.setPassword("newerPassword");

        String check2 = user.getPassword();
        assertEquals(check2, "newerPassword");
        assertNotEquals(check,check2);

    }


    @Test
    public void getName() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getName();

        assertEquals(check, "Julia");

    }

    @Test
    public void getEmail() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getEmail();

        assertEquals(check, email);

    }

    @Test
    public void setEmail() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getEmail();

        user.setEmail("new@usc.edu");

        String check2 = user.getEmail();
        assertEquals(check2,"new@usc.edu");
        assertNotEquals(check, check2);
    }

    @Test
    public void getStudentID() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getStudentID();

        assertEquals(check,studentID);

    }

    @Test
    public void setStudentID() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getStudentID();

        assertEquals(check,studentID);

    }

    @Test
    public void getLastSearch_subject() {


        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        String check = user.getLastSearch_subject();

        assertEquals(check,"");

    }

    @Test
    public void setLastSearch_subject() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        user.setLastSearch_subject("CSCI104");

        String check = user.getLastSearch_subject();

        assertEquals(check,"CSCI104");
    }

    @Test
    public void getLastSearch_day() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        int check = user.getLastSearch_day();

        assertEquals(check, -1);

    }

    @Test
    public void setLastSearch_day() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        user.setLastSearch_day(3);

        int check = user.getLastSearch_day();

        assertEquals(check, 3);

    }

    @Test
    public void getLastSearch_time() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        int check = user.getLastSearch_time();

        assertEquals(check, -1);

    }

    @Test
    public void setLastSearch_time() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutee user = new Tutee(studentID, email, name, username, password);

        user.setLastSearch_time(5);

        int check = user.getLastSearch_time();

        assertEquals(check, 5);

    }
}