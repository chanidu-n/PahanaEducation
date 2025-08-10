package model;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {
    
    private Customer customer;
    
    protected void setUp() throws Exception {
        super.setUp();
        customer = new Customer();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        customer = null;
    }
    
    public void testSetAndGetAccountNumber() {
        int accountNumber = 1001;
        customer.setAccountNumber(accountNumber);
        assertEquals("Account number should match", accountNumber, customer.getAccountNumber());
    }
    
    public void testSetAndGetName() {
        String name = "John Doe";
        customer.setName(name);
        assertEquals("Name should match", name, customer.getName());
    }
    
    public void testSetAndGetAddress() {
        String address = "123 Main Street";
        customer.setAddress(address);
        assertEquals("Address should match", address, customer.getAddress());
    }
    
    public void testSetAndGetTelephone() {
        String telephone = "077-1234567";
        customer.setTelephone(telephone);
        assertEquals("Telephone should match", telephone, customer.getTelephone());
    }
    
    public void testSetAndGetUnitsConsumed() {
        int units = 150;
        customer.setUnitsConsumed(units);
        assertEquals("Units consumed should match", units, customer.getUnitsConsumed());
    }
    
    public void testNegativeAccountNumber() {
        int negativeAccount = -1;
        customer.setAccountNumber(negativeAccount);
        assertEquals("Should accept negative account number", negativeAccount, customer.getAccountNumber());
    }
    
    public void testZeroUnitsConsumed() {
        customer.setUnitsConsumed(0);
        assertEquals("Should accept zero units", 0, customer.getUnitsConsumed());
    }
    
    public void testNullName() {
        customer.setName(null);
        assertNull("Name should be null", customer.getName());
    }
    
    public void testDefaultValues() {
        Customer newCustomer = new Customer();
        assertEquals("Default account number should be 0", 0, newCustomer.getAccountNumber());
        assertEquals("Default units consumed should be 0", 0, newCustomer.getUnitsConsumed());
        assertNull("Default name should be null", newCustomer.getName());
        assertNull("Default address should be null", newCustomer.getAddress());
        assertNull("Default telephone should be null", newCustomer.getTelephone());
    }
}