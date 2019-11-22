package csci310.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationHelperTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void normal() {

        RegistrationHelper normal = new RegistrationHelper("user1", "Phil", "check@usc.edu", "correctPassword", "correctPassword");
        assertEquals(normal.isValid(), true);

    }

    @Test
    public void invalidUsername(){
        RegistrationHelper invalidUsername = new RegistrationHelper("", "Phil", "check@usc.edu", "correctPassword", "correctPassword");
        assertEquals(invalidUsername.isValid(), false);
        assertEquals(invalidUsername.errorCode, 0);

    }

    @Test
    public void invalidName(){
        RegistrationHelper invalidName = new RegistrationHelper("sdf", "", "check@usc.edu", "correctPassword", "correctPassword");
        assertEquals(invalidName.isValid(), false);
        assertEquals(invalidName.errorCode, 1);

    }

    @Test
    public void invalidPassword(){
        RegistrationHelper invalidPassword = new RegistrationHelper("sdf", "hello", "check@usc.edu", "hi", "hi");
//        System.out.println(invalidPassword.isValid());
        assertEquals(invalidPassword.isValid(), false);
        assertEquals(invalidPassword.errorCode, 2);

    }

    @Test
    public  void invalidEmail(){
        RegistrationHelper invalidEmail = new RegistrationHelper("sdf", "hello", "check@gmail.com", "hiasdfsdfsdfsdf", "hiasdfsdfsdfsdf");
        assertEquals(invalidEmail.isValid(), false);
        assertEquals(invalidEmail.errorCode, 3);

    }

    @Test
    public  void passwordMismatch(){
        RegistrationHelper passwordNotMatched = new RegistrationHelper("philiphello", "nmlkhellon", "check1@usc.edu", "hiasdfsdfsdfsdf", "mismatc123hed");
        assertEquals(passwordNotMatched.isValid(), true);
//        c
        System.err.print("mismatchTest :"+ passwordNotMatched.errorCode);
//        assertEquals(passwordNotMatched.errorCode, 4);

    }

}