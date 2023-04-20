package org.example.servlet;

import org.example.config.ContextInitializer;
import org.example.model.Good;
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

@WebServlet(urlPatterns = {("/menu-shop")})
public class GoodServlet extends HttpServlet {

    private Connection connection;
    private List<Good> goods = new ArrayList<>();

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
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

        try (PreparedStatement statement = this.connection.prepareStatement("Select * From good;")) {
            ResultSet result = statement.executeQuery();
            while (result != null && result.next()) {
                goods.add(new Good(result.getInt("good_id"),
                        result.getString("title"),
                        result.getFloat("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("goods", goods);
        getServletContext().getRequestDispatcher("/menuShop.jsp").forward(req, resp);
    }
}