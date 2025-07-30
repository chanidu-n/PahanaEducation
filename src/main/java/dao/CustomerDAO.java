package dao;

import java.sql.*;
import java.util.*;
import model.Customer;
import util.DBUtil;

public class CustomerDAO {

    public void addCustomer(Customer c) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, c.getAccountNumber());
        stmt.setString(2, c.getName());
        stmt.setString(3, c.getAddress());
        stmt.setString(4, c.getTelephone());
        stmt.setInt(5, c.getUnitsConsumed());
        stmt.executeUpdate();
    }

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

        while (rs.next()) {
            Customer c = new Customer();
            c.setAccountNumber(rs.getInt("account_number"));
            c.setName(rs.getString("name"));
            c.setAddress(rs.getString("address"));
            c.setTelephone(rs.getString("telephone"));
            c.setUnitsConsumed(rs.getInt("units_consumed"));
            list.add(c);
        }
        return list;
    }

    public void updateCustomer(Customer c) throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE customers SET name=?, address=?, telephone=?, units_consumed=? WHERE account_number=?"
        );
        stmt.setString(1, c.getName());
        stmt.setString(2, c.getAddress());
        stmt.setString(3, c.getTelephone());
        stmt.setInt(4, c.getUnitsConsumed());
        stmt.setInt(5, c.getAccountNumber());
        stmt.executeUpdate();
    }

    public class BillingService {
        public  double calculateBill(int unitsConsumed) {
            double ratePerUnit = 10.0; // Example rate per unit
            return unitsConsumed * ratePerUnit;
        }
    }

}
