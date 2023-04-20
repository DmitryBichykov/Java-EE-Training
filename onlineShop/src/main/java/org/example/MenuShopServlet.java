package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/menuShop"})
public class MenuShopServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String clientName = req.getParameter("clientName");
        Good goods = new Good();

        writer.println("<html><body style='font-family: Tahoma, Arial, Sans-serif;'><table style='width: 100%;'><tr><td style='width: 45%;'></td><td>");
        writer.println("<p style='text-align: left; font-size: 18px; height: 30px; font-weight: normal;'>Hello " + clientName + "</p>");
        writer.println("<p style='text-align: left; font-size: 16px; height: 10px; font-weight: normal;'>Make your order</p>");
        writer.println("<form align='left' method='post' action='/check'>");
        writer.println("<input type='hidden' name='clientName' value='" + clientName + "'>");
        writer.println("<p ><select name='listGoods' style='width: 150px; height: 200px' multiple>");
        String value;
        for (Map.Entry<String, String> good : goods.getNameGood().entrySet()) {
            value = good.getKey() + " (" + good.getValue() + " $ )";
            writer.println(String.format("<option value='%s'>%s</option>", good.getKey(), value));
        }
        writer.println("</select></p>");
        writer.println("<button type='submit' style='background-color: #e6e6e6; width:100px; border-radius: 4px;'>Submit</button>");
        writer.println("</form>");
        writer.println("</td></tr></table></body></html>");

    }
}
