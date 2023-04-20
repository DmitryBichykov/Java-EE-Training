package org.example.servlet;

import org.example.config.ContextInitializer;
import org.example.model.Customer;
import org.example.model.Good;
import org.example.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = {"/customerServlet"})
public class CustomerServlet extends HttpServlet {

    private Connection connection;
    private final List<Customer> customer = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(ContextInitializer.DB_PROPS_PATH)) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String databaseURL = props.getProperty("db.url");

        try {
            this.connection = DriverManager.getConnection(databaseURL + ";DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM customer;")) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                this.customer.add(new Customer(result.getInt("user_id"),
                        result.getString("login"),
                        result.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM orders")) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                this.orders.add(new Order(result.getInt("order_id"),
                        result.getInt("user_id"),
                        result.getFloat("total_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("checked") == null) {
            resp.sendRedirect(req.getContextPath() + "/error");
        } else {

            String clientName = req.getParameter("clientName");
            req.getSession().setAttribute("clientName", clientName);

            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO customer (user_id, login, password) VALUES (?, ?, ?)")) {
                int customerId = this.customer.size();
                statement.setInt(1, customerId);
                statement.setString(2, clientName);
                statement.setString(3, "");
                statement.executeUpdate();

                this.customer.add(new Customer(customerId, clientName, ""));

                int orderId = this.orders.size();
                this.orders.add(new Order(orderId, customerId, 0));

                req.getSession().setAttribute("customer", this.customer.get(customerId));
                req.getSession().setAttribute("order", this.orders.get(orderId));
                resp.sendRedirect(req.getContextPath() + "/menu-shop");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
