package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBUtil;

public class User {

    private int id;
    private String accountnumber;
    private String username;
    private String passcode;
    private String fullname;
    private String address;
    private String telephonenumber;

    private Connection con;
    private Statement stmt;

    public User() {
    }

    public User(String accountnumber, String username, String passcode, String fullname, String address, String telephonenumber) {
        this.accountnumber = accountnumber;
        this.username = username;
        this.passcode = passcode;
        this.fullname = fullname;
        this.address = address;
        this.telephonenumber = telephonenumber;
    }

    public User(int id, String accountnumber, String username, String passcode, String fullname, String address, String telephonenumber) {
        this.id = id;
        this.accountnumber = accountnumber;
        this.username = username;
        this.passcode = passcode;
        this.fullname = fullname;
        this.address = address;
        this.telephonenumber = telephonenumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public boolean loginUser(String username, String password) {

        boolean flag = false;

        String sql;
        sql = "select * from user where username = '" + username + "' and passcode ='" + password + "'";

        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            ResultSet rest = stmt.executeQuery(sql);
            while (rest.next()) {
                flag = true;
            }

        } catch (SQLException ex) {

        }
        return flag;
    }

}
