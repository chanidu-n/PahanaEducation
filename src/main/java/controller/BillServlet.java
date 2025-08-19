package controller;

import dao.BillDAO;
import dao.CustomerDAO;
import dao.ItemDAO;
import model.Bill;
import model.BillItem;
import model.Customer;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        try {
            if ("create".equals(action)) {
                createBill(req, res);
            } else if ("getCustomer".equals(action)) {
                getCustomerDetails(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/forms/createBill.jsp").forward(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        try {
            if ("view".equals(action)) {
                int billId = Integer.parseInt(req.getParameter("billId"));
                Bill bill = new BillDAO().getBillById(billId);
                req.setAttribute("bill", bill);
                req.getRequestDispatcher("/forms/viewBill.jsp").forward(req, res);
            } else if ("list".equals(action)) {
                List<Bill> bills = new BillDAO().getAllBills();
                req.setAttribute("bills", bills);
                req.getRequestDispatcher("/forms/billList.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/forms/dashboard.jsp");
        }
    }

    private void createBill(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
        
        // Get selected items and quantities
        String[] itemIds = req.getParameterValues("itemId");
        String[] quantities = req.getParameterValues("quantity");
        
        if (itemIds == null || quantities == null) {
            throw new Exception("Please select at least one item");
        }
        
        // Validate customer exists
        Customer customer = new CustomerDAO().getCustomerByAccountNumber(accountNumber);
        if (customer == null) {
            throw new Exception("Customer not found with account number: " + accountNumber);
        }
        
        // Create bill items
        List<BillItem> billItems = new ArrayList<>();
        double totalAmount = 0.0;
        ItemDAO itemDAO = new ItemDAO();
        
        for (int i = 0; i < itemIds.length; i++) {
            if (quantities[i] != null && !quantities[i].trim().isEmpty() && Integer.parseInt(quantities[i]) > 0) {
                int itemId = Integer.parseInt(itemIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                
                Item item = itemDAO.getItemById(itemId);
                if (item != null) {
                    BillItem billItem = new BillItem();
                    billItem.setItemId(itemId);
                    billItem.setItemName(item.getName());
                    billItem.setItemPrice(item.getPrice());
                    billItem.setQuantity(quantity);
                    billItem.setSubtotal(item.getPrice() * quantity);
                    
                    billItems.add(billItem);
                    totalAmount += billItem.getSubtotal();
                }
            }
        }
        
        if (billItems.isEmpty()) {
            throw new Exception("Please select valid items with quantities");
        }
        
        // Create bill
        Bill bill = new Bill();
        bill.setAccountNumber(accountNumber);
        bill.setTotalAmount(totalAmount);
        bill.setBillItems(billItems);
        
        int billId = new BillDAO().createBill(bill);
        
        // Redirect to view the created bill
        res.sendRedirect(req.getContextPath() + "/forms/bill?action=view&billId=" + billId);
    }

    private void getCustomerDetails(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
        Customer customer = new CustomerDAO().getCustomerByAccountNumber(accountNumber);
        
        if (customer != null) {
            req.setAttribute("customer", customer);
        } else {
            req.setAttribute("customerError", "Customer not found with account number: " + accountNumber);
        }
        
        // Get all items for selection
        List<Item> items = new ItemDAO().getAllItems();
        req.setAttribute("items", items);
        
        req.getRequestDispatcher("/forms/createBill.jsp").forward(req, res);
    }
}
