<%@ page import="org.example.model.Good" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
             <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                         <meta http-equiv="X-UA-Compatible" content="ie=edge">
             <title>Menu shop</title>
</head>
<body style='font-family: Tahoma, Arial, Sans-serif;'>
    <table style='width: 100%;'>
        <tr>
            <td style='width: 45%;'></td>
            <td>
                <p style='text-align: left; font-size: 18px; height: 10px; font-weight: normal;'>Hello <%= session.getAttribute("clientName") %>!</p>
                <form align='left'  method='post' action='<%= request.getContextPath() %>/check'>
                    <iframe marginwidth='0' srcdoc='<html>
                                                        <style>p{font-family: Tahoma, Arial, Sans-serif;text-align: left; font-size: 16px}</style>
                                                        <body><p>Make your order</p>' name='listChosen' frameborder='0' height='100%' allowtransparency='true' seamless>
                    </iframe>
                    <p>
                        <select name='selectGood' style='width: 150px; height: 30px'>
                          <%
                            String value;
                            List<Good> goods = (List) session.getAttribute("goods");
                            for (Good good : goods) {
                                value = good.getTitle() + " (" + good.getPrice() + " $ )";
                          %>
                                 <option value=<%= good.getId() %>><%= value %></option>
                          <% } %>
                        </select>
                    </p>
                    <p>
                        <button formaction='<%= request.getContextPath() %>/shoppingBasket' formtarget='listChosen' style='background-color: #e6e6e6; width:100px; border-radius: 4px;'>Add item</button>
                        <button type='submit' style='background-color: #e6e6e6; width:100px; border-radius: 4px;'>Submit</button>
                    </p>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>