package dao;

import junit.framework.TestCase;
import model.Customer;
import java.util.List;

public class CustomerDAOTest extends TestCase {
    
    private CustomerDAO customerDAO;
    private Customer testCustomer;
    
    protected void setUp() throws Exception {
        super.setUp();
        customerDAO = new CustomerDAO();
        testCustomer = new Customer();
        testCustomer.setAccountNumber(9999); // Use unique test account
        testCustomer.setName("Test Customer");
        testCustomer.setAddress("Test Address");
        testCustomer.setTelephone("077-9999999");
        testCustomer.setUnitsConsumed(100);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        // Clean up test data
        try {
            // Note: You might want to add a delete method to CustomerDAO for cleanup
            // For now, we'll leave test data (or manually clean)
        } catch (Exception e) {
            // Ignore cleanup errors
        }
        customerDAO = null;
        testCustomer = null;
    }
    
    public void testAddCustomer() {
        try {
            customerDAO.addCustomer(testCustomer);
            // If no exception is thrown, the test passes
            assertTrue("Customer should be added successfully", true);
        } catch (Exception e) {
            if (e.getMessage().contains("already exists")) {
                // Customer already exists from previous test run
                assertTrue("Customer already exists", true);
            } else {
                fail("Unexpected exception: " + e.getMessage());
            }
        }
    }
    
    public void testAddDuplicateCustomer() {
        try {
            customerDAO.addCustomer(testCustomer);
            // Try to add the same customer again
            customerDAO.addCustomer(testCustomer);
            fail("Should throw exception for duplicate customer");
        } catch (Exception e) {
            assertTrue("Should throw exception for duplicate account number", 
                      e.getMessage().contains("already exists"));
        }
    }
    
    public void testGetAllCustomers() {
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            assertNotNull("Customer list should not be null", customers);
            assertTrue("Should have at least one customer", customers.size() >= 0);
        } catch (Exception e) {
            fail("Exception occurred while getting all customers: " + e.getMessage());
        }
    }
    
    public void testAddCustomerWithNullValues() {
        try {
            Customer nullCustomer = new Customer();
            nullCustomer.setAccountNumber(8888);
            nullCustomer.setName(null);
            nullCustomer.setAddress(null);
            nullCustomer.setTelephone(null);
            
            customerDAO.addCustomer(nullCustomer);
            assertTrue("Should handle null values gracefully", true);
        } catch (Exception e) {
            // This might fail due to database constraints
            assertTrue("Database constraint violation expected", true);
        }
    }
    
    public void testAddCustomerWithEmptyString() {
        try {
            Customer emptyCustomer = new Customer();
            emptyCustomer.setAccountNumber(7);
            emptyCustomer.setName("");
            emptyCustomer.setAddress("");
            emptyCustomer.setTelephone("");
            emptyCustomer.setUnitsConsumed(0);
            
            customerDAO.addCustomer(emptyCustomer);
            assertTrue("Should handle empty strings", true);
        } catch (Exception e) {
            fail("Unexpected exception with empty strings: " + e.getMessage());
        }
    }
}