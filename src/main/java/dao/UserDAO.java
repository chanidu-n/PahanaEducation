package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.*;

public class UserDAO {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("accountnumber"), rs.getString("username"), rs.getString("passcode"), rs.getString("fullname"), rs.getString("address"), rs.getString("telephonenumber")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void insertUser(User user) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO user (accountnumber, username, passcode, fullname, address, telephonenumber) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, user.getAccountnumber());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasscode());
            stmt.setString(4, user.getFullname());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getTelephonenumber());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE id=?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(id, rs.getString("accountnumber"), rs.getString("username"), rs.getString("passcode"), rs.getString("fullname"), rs.getString("address"), rs.getString("telephonenumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE user SET accountnumber=?, username=?, passcode=?, fullname=?, address=?, telephonenumber=? WHERE id=?")) {
            stmt.setString(1, user.getAccountnumber());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPasscode());
            stmt.setString(4, user.getFullname());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getTelephonenumber());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
