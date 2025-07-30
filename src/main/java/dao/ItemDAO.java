package dao;

import java.sql.*;
import java.util.*;
import model.Item;
import util.DBUtil;

public class ItemDAO {
    public void addItem(Item item) throws Exception {
        System.out.println("item works");
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO items (name, price) VALUES (?, ?)");
        stmt.setString(1, item.getName());
        stmt.setDouble(2, item.getPrice());
        stmt.executeUpdate();
    }

    public List<Item> getAllItems() throws Exception {
        List<Item> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM items");

        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getDouble("price"));
            list.add(item);
        }
        return list;
    }

    public void deleteItem(int id) throws Exception {
        Connection conn =DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM items WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
