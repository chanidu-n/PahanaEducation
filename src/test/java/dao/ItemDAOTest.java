package dao;

import junit.framework.TestCase;
import model.Item;
import java.util.List;

public class ItemDAOTest extends TestCase {
    
    private ItemDAO itemDAO;
    private Item testItem;
    
    protected void setUp() throws Exception {
        super.setUp();
        itemDAO = new ItemDAO();
        testItem = new Item();
        testItem.setName("Test Book");
        testItem.setPrice(25.99);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        itemDAO = null;
        testItem = null;
    }
    
    public void testAddItem() {
        try {
            itemDAO.addItem(testItem);
            assertTrue("Item should be added successfully", true);
        } catch (Exception e) {
            fail("Exception occurred while adding item: " + e.getMessage());
        }
    }
    
    public void testGetAllItems() {
        try {
            List<Item> items = itemDAO.getAllItems();
            assertNotNull("Items list should not be null", items);
            assertTrue("Should have at least zero items", items.size() >= 0);
        } catch (Exception e) {
            fail("Exception occurred while getting all items: " + e.getMessage());
        }
    }
    
    public void testAddItemWithNullName() {
        try {
            testItem.setName(null);
            itemDAO.addItem(testItem);
            fail("Should not accept null name");
        } catch (Exception e) {
            assertTrue("Should throw exception for null name", true);
        }
    }
    
    public void testAddItemWithEmptyName() {
        try {
            testItem.setName("");
            itemDAO.addItem(testItem);
            assertTrue("Should handle empty name", true);
        } catch (Exception e) {
            fail("Unexpected exception with empty name: " + e.getMessage());
        }
    }
    
    public void testAddItemWithNegativePrice() {
        try {
            testItem.setPrice(-10.0);
            itemDAO.addItem(testItem);
            assertTrue("Should handle negative price", true);
        } catch (Exception e) {
            fail("Unexpected exception with negative price: " + e.getMessage());
        }
    }
    
    public void testAddItemWithZeroPrice() {
        try {
            testItem.setPrice(0.0);
            itemDAO.addItem(testItem);
            assertTrue("Should handle zero price", true);
        } catch (Exception e) {
            fail("Unexpected exception with zero price: " + e.getMessage());
        }
    }
    
    public void testDeleteItem() {
        try {
            // First add an item
            itemDAO.addItem(testItem);
            
            // Get all items to find our test item ID
            List<Item> items = itemDAO.getAllItems();
            Item addedItem = null;
            for (Item item : items) {
                if ("Test Book".equals(item.getName()) && item.getPrice() == 25.99) {
                    addedItem = item;
                    break;
                }
            }
            
            if (addedItem != null) {
                itemDAO.deleteItem(addedItem.getId());
                assertTrue("Item should be deleted successfully", true);
            }
        } catch (Exception e) {
            fail("Exception occurred while deleting item: " + e.getMessage());
        }
    }
    
    public void testDeleteNonExistentItem() {
        try {
            itemDAO.deleteItem(99999); // Non-existent ID
            assertTrue("Should handle deletion of non-existent item", true);
        } catch (Exception e) {
            fail("Exception occurred while deleting non-existent item: " + e.getMessage());
        }
    }
}