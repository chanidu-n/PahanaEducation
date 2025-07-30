package dao;


import model.Customer;
import model.Item;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers (accountNumber,name,address,telephone,unitsConsumed) VALUES (?,?,?,?,?)");
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
            customer.setAccountNumber(rs.getInt("accountNumber"));
            customer.setName(rs.getString("name"));
            customer.setAddress(rs.getString("address"));
            customer.setTelephone(rs.getString("telephone"));
            customer.setUnitsConsumed(rs.getInt("unitsConsumed"));
            list.add(customer);
        }
        return list;
    }
}
