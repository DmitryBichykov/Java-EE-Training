<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Good" %>
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
      <%! public static final List<String> LISTSHOPPING = new ArrayList<>(); %>
      <%
            LISTSHOPPING.add(request.getParameter("listGoods"));
            session.setAttribute("LISTSHOPPING", LISTSHOPPING);
            Good good = new Good();

          for (int i = 0; i < LISTSHOPPING.size(); i++) {
      %>
              <p><%= (i + 1) %>)  <% out.print(LISTSHOPPING.get(i) + " " + good.getNameGood().get(LISTSHOPPING.get(i))); %> $</p>
      <% } %>
</body>
</html>