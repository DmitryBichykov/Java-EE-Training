package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/shopping-basket"})
public class shoppingBasketServlet extends HttpServlet {

    public static final List<String> LISTSHOPPING = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String listGoods = req.getParameter("listGoods");
        Good good = new Good();
        LISTSHOPPING.add(listGoods);

        writer.println("<html><body style='font-family: Tahoma, Arial, Sans-serif;'><table style='width: 100%;'>");
        writer.println("<p>You have already chosen:</p>");
        for (int i = 0; i < LISTSHOPPING.size(); i++) {
            writer.println("<p>" + (i + 1) + ") " + LISTSHOPPING.get(i) + " " + good.getNameGood().get(LISTSHOPPING.get(i)) + " $</p>");
        }
        writer.println("</body></html>");
    }

}
