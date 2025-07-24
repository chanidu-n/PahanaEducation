package controller;

import dao.ItemDAO;
import model.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Item item = new Item();
            item.setName(req.getParameter("name"));
            item.setPrice(Double.parseDouble(req.getParameter("price")));

            new ItemDAO().addItem(item);
            res.sendRedirect("/forms/viewItems.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/forms/addItem.jsp");
        dispatcher.forward(req, res);
    }
}
