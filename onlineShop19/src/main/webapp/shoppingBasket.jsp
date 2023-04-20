<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.model.Good" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
             <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                         <meta http-equiv="X-UA-Compatible" content="ie=edge">
             <title>Shopping basket</title>
</head>
<body style='font-family: Tahoma, Arial, Sans-serif;'><table style='width: 100%;'>
      <p>You have already chosen:</p>
      <%
            List<Integer> shoppingBasket = (List) session.getAttribute("shoppingBasket");
            List<Good> goods = (List) session.getAttribute("goods");
          for (int i = 0; i < shoppingBasket.size(); i++) {
      %>
              <p><%= (i + 1) %>)  <% out.print(goods.get(shoppingBasket.get(i)).getTitle() +
                                        " " + goods.get(shoppingBasket.get(i)).getPrice());  %> $</p>
      <% } %>
</body>
</html>