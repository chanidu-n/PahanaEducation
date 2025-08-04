package dao;

import model.Bill;
import model.BillItem;
import model.Customer;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public int createBill(Bill bill) throws Exception {
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        
        try {
            // Insert bill
            PreparedStatement billStmt = conn.prepareStatement(
                "INSERT INTO bills (account_number, bill_date, total_amount) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            billStmt.setInt(1, bill.getAccountNumber());
            billStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            billStmt.setDouble(3, bill.getTotalAmount());
            billStmt.executeUpdate();
            
            // Get generated bill ID
            ResultSet rs = billStmt.getGeneratedKeys();
            int billId = 0;
            if (rs.next()) {
                billId = rs.getInt(1);
            }
            
            // Insert bill items
            PreparedStatement itemStmt = conn.prepareStatement(
                "INSERT INTO bill_items (bill_id, item_id, item_name, item_price, quantity, subtotal) VALUES (?, ?, ?, ?, ?, ?)"
            );
            
            for (BillItem item : bill.getBillItems()) {
                itemStmt.setInt(1, billId);
                itemStmt.setInt(2, item.getItemId());
                itemStmt.setString(3, item.getItemName());
                itemStmt.setDouble(4, item.getItemPrice());
                itemStmt.setInt(5, item.getQuantity());
                itemStmt.setDouble(6, item.getSubtotal());
                itemStmt.executeUpdate();
            }
            
            conn.commit();
            return billId;
            
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public List<Bill> getAllBills() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement(
            "SELECT b.*, c.name as customer_name FROM bills b " +
            "JOIN customers c ON b.account_number = c.account_number " +
            "ORDER BY b.bill_date DESC"
        );
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Bill bill = new Bill();
            bill.setBillId(rs.getInt("bill_id"));
            bill.setAccountNumber(rs.getInt("account_number"));
            bill.setBillDate(rs.getTimestamp("bill_date"));
            bill.setTotalAmount(rs.getDouble("total_amount"));
            bill.setCustomerName(rs.getString("customer_name"));
            bills.add(bill);
        }
        
        return bills;
    }

    public Bill getBillById(int billId) throws Exception {
        Connection conn = DBUtil.getConnection();
        
        // Get bill details with customer info
        PreparedStatement billStmt = conn.prepareStatement(
            "SELECT b.*, c.name, c.address, c.telephone FROM bills b " +
            "JOIN customers c ON b.account_number = c.account_number " +
            "WHERE b.bill_id = ?"
        );
        billStmt.setInt(1, billId);
        ResultSet billRs = billStmt.executeQuery();
        
        if (!billRs.next()) {
            return null;
        }
        
        Bill bill = new Bill();
        bill.setBillId(billRs.getInt("bill_id"));
        bill.setAccountNumber(billRs.getInt("account_number"));
        bill.setBillDate(billRs.getTimestamp("bill_date"));
        bill.setTotalAmount(billRs.getDouble("total_amount"));
        bill.setCustomerName(billRs.getString("name"));
        bill.setCustomerAddress(billRs.getString("address"));
        bill.setCustomerTelephone(billRs.getString("telephone"));
        
        // Get bill items
        PreparedStatement itemsStmt = conn.prepareStatement(
            "SELECT * FROM bill_items WHERE bill_id = ?"
        );
        itemsStmt.setInt(1, billId);
        ResultSet itemsRs = itemsStmt.executeQuery();
        
        List<BillItem> billItems = new ArrayList<>();
        while (itemsRs.next()) {
            BillItem item = new BillItem();
            item.setBillItemId(itemsRs.getInt("bill_item_id"));
            item.setBillId(itemsRs.getInt("bill_id"));
            item.setItemId(itemsRs.getInt("item_id"));
            item.setItemName(itemsRs.getString("item_name"));
            item.setItemPrice(itemsRs.getDouble("item_price"));
            item.setQuantity(itemsRs.getInt("quantity"));
            item.setSubtotal(itemsRs.getDouble("subtotal"));
            billItems.add(item);
        }
        
        bill.setBillItems(billItems);
        return bill;
    }
}
