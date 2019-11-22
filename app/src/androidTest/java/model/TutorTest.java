package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TutorTest {

    @Test
    public void setName() {
        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

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
        Tutor user = new Tutor(studentID, email, name, username, password);

        String check = user.getStudentID();

        user.setStudentID("2123");

        String check2 = user.getStudentID();

        assertEquals(check2,"2123");
        assertNotEquals(check,check2);

    }

    @Test
    public void getWeekNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setWeekNew(5);

        assertEquals(user.getWeekNew(),5);

    }

    @Test
    public void setWeekNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setWeekNew(2);

        assertEquals(user.getWeekNew(),2);

    }

    @Test
    public void getTimeNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setTimeNew(3);

        assertEquals(user.getTimeNew(),3);
    }

    @Test
    public void setTimeNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setTimeNew(1);

        assertEquals(user.getTimeNew(),1);

    }

    @Test
    public void getSubjectNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setSubjectNew("CSCI404");

        assertEquals(user.getSubjectNew(),"CSCI404");

    }

    @Test
    public void setSubjectNew() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        user.setSubjectNew("CSCI353");

        assertEquals(user.getSubjectNew(),"CSCI353");

    }

    @Test
    public void getNumRatings() {

        String studentID = "5837293";
        String email = "hello@usc.edu";
        String name = "Julia";
        String username = "King";
        String password = "securedPasswor";
        Tutor user = new Tutor(studentID, email, name, username, password);

        assertEquals(user.getNumRatings(),0);

    }


}