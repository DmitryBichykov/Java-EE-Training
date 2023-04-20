<%@ page import="org.example.Good" %>
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
                <p style='text-align: left; font-size: 18px; font-family: Tahoma, Arial, Sans-serif; height: 30px; font-weight: normal;'>Dear, <%= request.getParameter("clientName") %> your order:</p>
            <%
                Good good = new Good();
                double totalPrice = 0;
                List<String> listShopping = (List<String>) session.getAttribute("LISTSHOPPING");
                for (int i = 0; i < listShopping.size(); i++) {
            %>
                  <p><%= (i + 1) %>) <% out.print(listShopping.get(i) + " " + good.getNameGood().get(listShopping.get(i))); %> $</p>
                  <% totalPrice += Double.parseDouble(good.getNameGood().get(listShopping.get(i))); %>
            <% } %>
          <p>Total: $ <%= totalPrice %></p>
          <% listShopping.clear(); %>
</body>
</html>