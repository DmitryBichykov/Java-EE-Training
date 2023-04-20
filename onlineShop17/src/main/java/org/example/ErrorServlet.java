package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = {"/error"})
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html><body style='font-family: Tahoma, Arial, Sans-serif;'><table style='width: 100%;'><tr><td style='width: 40%;'></td><td style='text-align: left; font-size: 18px; height: 40px; font-weight: normal;'>");
        writer.println("<p style='font-weight: bold;'>Oops!</p>");
        writer.println("<p>You shouldn't be here<br> Please, agree with the terms of service first.</p>");
        writer.println("<p><a href='" + req.getContextPath() + "/start-page'>Start page</a></p>");
        writer.println("</td></tr></table></body></html>");
    }

}
