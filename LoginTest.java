package com.mycompany.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 *
 * @author Student
 */
public class LoginTest {
    



    Login login = new Login();

    @Test
    public void testUsernameFormatSuccess() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameFormatFailure() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordComplexitySuccess() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordComplexityFailure() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976", "Kyle", "LastName");
        assertEquals(expected, actual);
    }

    @Test
    public void testCellPhoneNumberSuccess() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneNumberFailure() {
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "LastName");
        assertEquals(expected, actual);
    }

    @Test
    public void testLoginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "LastName");
        assertFalse(login.loginUser("wrong_user", "wrong_pass"));
    }
}

