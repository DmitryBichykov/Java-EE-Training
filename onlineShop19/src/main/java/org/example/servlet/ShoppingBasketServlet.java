package org.example.servlet;
import org.example.model.Good;
import org.example.model.Order;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/shoppingBasket"})
public class ShoppingBasketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int selectGoodId = Integer.parseInt(req.getParameter("selectGood"));
            Order order = (Order) req.getSession().getAttribute("order");
            List<Good> good = (List) req.getSession().getAttribute("goods");

            order.setGoodId(selectGoodId);
            order.setTotalPrice(order.getTotalPrice() + good.get(selectGoodId).getPrice());
            req.getSession().setAttribute("shoppingBasket", order.getGoodsId());
            getServletContext().getRequestDispatcher("/shoppingBasket.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
