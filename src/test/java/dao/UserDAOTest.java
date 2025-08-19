package dao;

import junit.framework.TestCase;
import model.User;

public class UserDAOTest extends TestCase {
    
    private UserDAO userDAO;
    private User testUser;
    
    protected void setUp() throws Exception {
        super.setUp();
        userDAO = new UserDAO();
        testUser = new User();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        userDAO = null;
        testUser = null;
    }
    
    public void testValidateExistingUser() {
        // Test with known user from database (admin/admin123)
        testUser.setUsername("admin");
        testUser.setPassword("admin123");
        
        boolean result = userDAO.validate(testUser);
        assertTrue("Should validate existing user", result);
    }
    
    public void testValidateInvalidUser() {
        testUser.setUsername("nonexistent");
        testUser.setPassword("wrongpassword");
        
        boolean result = userDAO.validate(testUser);
        assertFalse("Should not validate non-existent user", result);
    }
    
    public void testValidateNullUsername() {
        testUser.setUsername(null);
        testUser.setPassword("admin123");
        
        boolean result = userDAO.validate(testUser);
        assertFalse("Should not validate null username", result);
    }
    
    public void testValidateNullPassword() {
        testUser.setUsername("admin");
        testUser.setPassword(null);
        
        boolean result = userDAO.validate(testUser);
        assertFalse("Should not validate null password", result);
    }
    
    public void testValidateEmptyCredentials() {
        testUser.setUsername("");
        testUser.setPassword("");
        
        boolean result = userDAO.validate(testUser);
        assertFalse("Should not validate empty credentials", result);
    }
    
    public void testValidateWrongPassword() {
        testUser.setUsername("admin");
        testUser.setPassword("wrongpassword");
        
        boolean result = userDAO.validate(testUser);
        assertFalse("Should not validate wrong password", result);
    }
    
    public void testValidateNullUser() {
        try {
            boolean result = userDAO.validate(null);
            assertFalse("Should handle null user gracefully", result);
        } catch (Exception e) {
            // Expected behavior - should handle null gracefully
            assertTrue("Should handle null user", true);
        }
    }
}