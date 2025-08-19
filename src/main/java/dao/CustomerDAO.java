package dao;


import model.Customer;
import model.Item;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer) throws Exception {
        System.out.println("cus works");
        Connection conn = DBUtil.getConnection();
        
        // First check if customer already exists
        PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM customers WHERE account_number = ?");
        checkStmt.setInt(1, customer.getAccountNumber());
        java.sql.ResultSet rs = checkStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        
        if (count > 0) {
            throw new Exception("Customer with account number " + customer.getAccountNumber() + " already exists");
        }
        
        // If customer doesn't exist, insert
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (account_number,name,address,telephone,units_consumed) VALUES (?,?,?,?,?)");
        stmt.setInt(1, customer.getAccountNumber());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getAddress());
        stmt.setString(4, customer.getTelephone());
        stmt.setInt(5, customer.getUnitsConsumed());
        stmt.executeUpdate();
    }

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> list = new java.util.ArrayList<>();
        Connection conn = DBUtil.getConnection();
        java.sql.Statement stmt = conn.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setAccountNumber(rs.getInt("account_number"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setTelephone(rs.getString("telephone"));
            customer.setUnitsConsumed(rs.getInt("units_consumed"));
            list.add(customer);
        }
        return list;
    }

    public Customer getCustomerByAccountNumber(int accountNumber) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE account_number = ?");
        stmt.setInt(1, accountNumber);
        java.sql.ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Customer customer = new Customer();
            customer.setAccountNumber(rs.getInt("account_number"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setTelephone(rs.getString("telephone"));
            customer.setUnitsConsumed(rs.getInt("units_consumed"));
            return customer;
        }
        return null;
    }

    public void updateCustomer(Customer customer) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            "UPDATE customers SET name=?, address=?, telephone=?, units_consumed=? WHERE account_number=?"
        );
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getAddress());
        stmt.setString(3, customer.getTelephone());
        stmt.setInt(4, customer.getUnitsConsumed());
        stmt.setInt(5, customer.getAccountNumber());
        stmt.executeUpdate();
    }

    public void deleteCustomer(int accountNumber) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM customers WHERE account_number = ?");
        stmt.setInt(1, accountNumber);
        stmt.executeUpdate();
    }
}
