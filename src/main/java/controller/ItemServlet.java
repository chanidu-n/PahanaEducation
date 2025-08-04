package controller;

import dao.ItemDAO;
import model.Item;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ItemServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        try {
            if ("add".equals(action)) {
                addItem(req, res);
            } else if ("update".equals(action)) {
                updateItem(req, res);
            } else if ("delete".equals(action)) {
                deleteItem(req, res);
            } else {
                // Default to add for backward compatibility
                addItem(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/forms/viewItems.jsp").forward(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Item item = new ItemDAO().getItemById(id);
                req.setAttribute("item", item);
                req.getRequestDispatcher("/forms/editItem.jsp").forward(req, res);
            } catch (Exception e) {
                e.printStackTrace();
                res.sendRedirect(req.getContextPath() + "/forms/viewItems.jsp");
            }
        }
    }

    private void addItem(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        Item item = new Item();
        item.setName(req.getParameter("name"));
        item.setPrice(Double.parseDouble(req.getParameter("price")));

        new ItemDAO().addItem(item);
        res.sendRedirect(req.getContextPath() + "/forms/viewItems.jsp");
    }

    private void updateItem(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);

        new ItemDAO().updateItem(item);
        res.sendRedirect(req.getContextPath() + "/forms/viewItems.jsp");
    }

    private void deleteItem(HttpServletRequest req, HttpServletResponse res) throws Exception, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        new ItemDAO().deleteItem(id);
        res.sendRedirect(req.getContextPath() + "/forms/viewItems.jsp");
    }
}
