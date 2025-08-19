package controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        try {
            if ("add".equals(action)) {
                addCustomer(req, res);
            } else if ("update".equals(action)) {
                updateCustomer(req, res);
            } else if ("delete".equals(action)) {
                deleteCustomer(req, res);
            } else {
                // Default to add for backward compatibility
                addCustomer(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/forms/viewCustomer.jsp").forward(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("edit".equals(action)) {
            try {
                int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
                model.Customer customer = new dao.CustomerDAO().getCustomerByAccountNumber(accountNumber);
                req.setAttribute("customer", customer);
                req.getRequestDispatcher("/forms/editCustomer.jsp").forward(req, res);
            } catch (Exception e) {
                e.printStackTrace();
                res.sendRedirect(req.getContextPath() + "/forms/viewCustomer.jsp");
            }
        }
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
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
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
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

        new dao.CustomerDAO().updateCustomer(customer);
        res.sendRedirect(req.getContextPath() + "/forms/viewCustomer.jsp");
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
        new dao.CustomerDAO().deleteCustomer(accountNumber);
        res.sendRedirect(req.getContextPath() + "/forms/viewCustomer.jsp");
    }
}