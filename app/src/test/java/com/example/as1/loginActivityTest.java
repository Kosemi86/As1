package com.example.as1;
import org.junit.Test;
import static org.junit.Assert.*;

public class loginActivityTest {

    @Test
    public void testValidLogin() {
        String validUsername = "khalid";
        String validPassword = "CSY1020 ";
        boolean isValid = LoginDetails.isValidLogin(validUsername, validPassword);
        assertTrue(isValid);
    }

    @Test
    public void testInvalidUsername() {
        String invalidUsername = "not_a_student";
        String validPassword = "password1";
        boolean isValid = LoginDetails.isValidLogin(invalidUsername, validPassword);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidPassword() {
        String validUsername = "student1";
        String invalidPassword = "not_a_password";
        boolean isValid = LoginDetails.isValidLogin(validUsername, invalidPassword);
        assertFalse(isValid);
    }

    @Test
    public void testInvalidUsernameAndPassword() {
        String invalidUsername = "not_a_student";
        String invalidPassword = "not_a_password";
        boolean isValid = LoginDetails.isValidLogin(invalidUsername, invalidPassword);
        assertFalse(isValid);
    }

}

