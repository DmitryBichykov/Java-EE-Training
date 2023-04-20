package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/check"})
public class CheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        String[] listGoods = req.getParameterValues("listGoods");
        String clientName = req.getParameter("clientName");
        Good good = new Good();

        writer.println("<html><body style='font-family: Tahoma, Arial, Sans-serif;'><table style='width: 100%;'><tr><td style='width: 45%;'></td><td>");
        writer.println("<p style='text-align: left; font-size: 18px; font-family: Tahoma, Arial, Sans-serif; height: 30px; font-weight: normal;'>Dear, " + clientName + " your order:</p>");

        double totalPrice = 0;
        int index = 1;
        for (String keyGood : listGoods) {
            writer.println("<p>" + (index++) + ") " + keyGood + " " + good.getNameGood().get(keyGood) + " $</p>");
            totalPrice += Double.parseDouble(good.getNameGood().get(keyGood));
        }

        writer.println("Total: $ " + totalPrice);
        writer.println("</body></html>");
    }

}
