package controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String telephone = req.getParameter("telephone");
            int unitsConsumed = Integer.parseInt(req.getParameter("unitsConsumed"));

            model.Customer customer = new model.Customer();
            customer.setAccountNumber(accountNumber);
            customer.setName(name);
            customer.setAddress(address);
            customer.setTelephone(telephone);
            customer.setUnitsConsumed(unitsConsumed);

            new dao.CustomerDAO().addCustomer(customer);
            res.sendRedirect(req.getContextPath() + "/forms/viewCustomer.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}