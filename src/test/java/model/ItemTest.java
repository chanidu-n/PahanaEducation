package model;

import junit.framework.TestCase;

public class ItemTest extends TestCase {
    
    private Item item;
    
    protected void setUp() throws Exception {
        super.setUp();
        item = new Item();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        item = null;
    }
    
    public void testSetAndGetId() {
        int id = 1;
        item.setId(id);
        assertEquals("ID should match", id, item.getId());
    }
    
    public void testSetAndGetName() {
        String name = "Scientific Calculator";
        item.setName(name);
        assertEquals("Name should match", name, item.getName());
    }
    
    public void testSetAndGetPrice() {
        double price = 2500.50;
        item.setPrice(price);
        assertEquals("Price should match", price, item.getPrice(), 0.001);
    }
    
    public void testZeroPrice() {
        item.setPrice(0.0);
        assertEquals("Should accept zero price", 0.0, item.getPrice(), 0.001);
    }
    
    public void testNegativePrice() {
        double negativePrice = -100.0;
        item.setPrice(negativePrice);
        assertEquals("Should accept negative price", negativePrice, item.getPrice(), 0.001);
    }
    
    public void testNullName() {
        item.setName(null);
        assertNull("Name should be null", item.getName());
    }
    
    public void testEmptyName() {
        item.setName("");
        assertEquals("Name should be empty string", "", item.getName());
    }
    
    public void testDefaultValues() {
        Item newItem = new Item();
        assertEquals("Default ID should be 0", 0, newItem.getId());
        assertEquals("Default price should be 0.0", 0.0, newItem.getPrice(), 0.001);
        assertNull("Default name should be null", newItem.getName());
    }
    
    public void testLargePrice() {
        double largePrice = 999999.99;
        item.setPrice(largePrice);
        assertEquals("Should handle large price", largePrice, item.getPrice(), 0.001);
    }
}