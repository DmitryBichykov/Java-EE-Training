<%@ page import="org.example.model.Good" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
             <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                         <meta http-equiv="X-UA-Compatible" content="ie=edge">
             <title>Check</title>
</head>
<body style='font-family: Tahoma, Arial, Sans-serif;'>
    <table style='width: 100%;'>
        <tr>
            <td style='width: 45%;'></td>
            <td>
                <p style='text-align: left; font-size: 18px; font-family: Tahoma, Arial, Sans-serif; height: 30px; font-weight: normal;'>Dear, <%= session.getAttribute("clientName") %> your order:</p>
            <%
                List<Good> goods = (List) session.getAttribute("goods");
                float totalPrice = 0;
                List<Integer> shoppingBasket = (List) session.getAttribute("shoppingBasket");
                for (int i = 0; i < shoppingBasket.size(); i++) {
            %>
                  <p><%= (i + 1) %>) <% out.print(goods.get(shoppingBasket.get(i)).getTitle() + " " + goods.get(shoppingBasket.get(i)).getPrice()); %> $</p>
            <% }%>
          <p>Total: $ <%= session.getAttribute("totalPrice") %></p>
</body>
</html>