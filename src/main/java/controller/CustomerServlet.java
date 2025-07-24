package controller;

import dao.CustomerDAO;
import model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Customer c = new Customer();
            c.setAccountNumber(Integer.parseInt(req.getParameter("accountNumber")));
            c.setName(req.getParameter("name"));
            c.setAddress(req.getParameter("address"));
            c.setTelephone(req.getParameter("telephone"));
            c.setUnitsConsumed(Integer.parseInt(req.getParameter("unitsConsumed")));

            new CustomerDAO().addCustomer(c);
            res.sendRedirect("viewCustomers.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
