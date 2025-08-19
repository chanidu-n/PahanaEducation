package model;

import junit.framework.TestCase;

public class UserTest extends TestCase {
    
    private User user;
    
    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        user = null;
    }
    
    public void testSetAndGetUsername() {
        String username = "testuser";
        user.setUsername(username);
        assertEquals("Username should match", username, user.getUsername());
    }
    
    public void testSetAndGetPassword() {
        String password = "testpass123";
        user.setPassword(password);
        assertEquals("Password should match", password, user.getPassword());
    }
    
    public void testUsernameNull() {
        user.setUsername(null);
        assertNull("Username should be null", user.getUsername());
    }
    
    public void testPasswordNull() {
        user.setPassword(null);
        assertNull("Password should be null", user.getPassword());
    }
    
    public void testEmptyUsername() {
        user.setUsername("");
        assertEquals("Username should be empty string", "", user.getUsername());
    }
    
    public void testEmptyPassword() {
        user.setPassword("");
        assertEquals("Password should be empty string", "", user.getPassword());
    }
}