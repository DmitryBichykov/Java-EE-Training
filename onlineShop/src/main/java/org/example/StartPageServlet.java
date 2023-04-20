package org.example;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/online-shop"})
public class StartPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html><body style='font-family: Tahoma, Arial, Sans-serif;'>");
        writer.println("<p style='text-align: center; font-size: 18px; height: 40px; font-weight: normal;'>Welcome to Online Shop</p>");
        writer.println("<table style='width: 100%;'><tr><td style='width: 45%;'></td><td>");
        writer.println("<form align='left' method='post' action='/menuShop'>");
        writer.println("<p style='height: 10px;'><input type='text' name='clientName' placeholder='Enter your name' style='width: 150px;'></p>");
        writer.println("<p><input type='checkbox'>I agree with the terms of service</p>");
        writer.println("<button type='submit' style='background-color: #e6e6e6; width:150px; border-radius: 4px;'>Enter</button>");
        writer.println("</form></td></table>");
        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html><body>");
        writer.println("<h1 align='center'>Welcome to Online Shop</h1>");
        writer.println("</body></html>");
    }
}
