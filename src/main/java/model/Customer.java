package model;

import java.sql.Connection;
import java.sql.Statement;

public class Customer {

    private int accountNumber;
    private String name;
    private String address;
    private String telephone;
    private int unitsConsumed;

    private Connection con;
    private Statement stmt;

    public Customer(Statement stmt, Connection con, int unitsConsumed, String telephone, String address, String name, int accountNumber) {
        this.stmt = stmt;
        this.con = con;
        this.unitsConsumed = unitsConsumed;
        this.telephone = telephone;
        this.address = address;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public Customer(int accountNumber, String name, String address, String telephone, int unitsConsumed) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.unitsConsumed = unitsConsumed;
    }

    public Customer() {

    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }


}
