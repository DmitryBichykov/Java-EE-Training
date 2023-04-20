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
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = {("/check")})
public class CheckServlet extends HttpServlet {

    private Connection connection;

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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = (Order) req.getSession().getAttribute("order");
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        List<Good> goods = (List) req.getSession().getAttribute("goods");
        float totalPrice = order.getTotalPrice();
        req.getSession().setAttribute("totalPrice", totalPrice);

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?)")) {
            statement.setInt(1, order.getId());
            statement.setInt(2, customer.getId());
            statement.setFloat(3, totalPrice);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO order_good (order_id, good_id) VALUES ( ?, ?)")) {
            for (Integer goodId : order.getGoodsId()) {
                statement.setInt(1, order.getId());
                statement.setInt(2, goodId);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement statement = this.connection.prepareStatement(
                "SELECT customer.user_id, customer.login, orders.order_id, good.title FROM customer INNER JOIN orders ON customer.user_id=orders.user_id " +
                        "INNER JOIN order_good ON order_good.order_id=orders.order_id " +
                        "INNER JOIN good ON order_good.good_id=good.good_id")){
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("user_id") + "|" + result.getString("login") +
                        "|" + result.getInt("order_id")+"|" + result.getString("title"));
            }
            System.out.println("______________________________________");
        }catch(SQLException e){
            e.printStackTrace();
        }

        getServletContext().getRequestDispatcher("/check.jsp").forward(req, resp);
    }


}
