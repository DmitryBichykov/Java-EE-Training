<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
             <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                         <meta http-equiv="X-UA-Compatible" content="ie=edge">
             <title>Online-Shop</title>
</head>
<body style='font-family: Tahoma, Arial, Sans-serif;'>
    <p style='text-align: center; font-size: 18px; height: 40px; font-weight: normal;'>Welcome to Online Shop</p>
    <table style='width: 100%;'>
        <tr>
            <td style='width: 45%;'></td>
            <td>
                <form align='left' method='post' action='<%= request.getContextPath() %>/menu-shop'>
                    <p style='height: 10px;'>
                        <input type='text' name='clientName' placeholder='Enter your name' style='width: 150px;'>
                    </p>
                    <p><input type='checkbox' name='checked' value='true'>I agree with the terms of service</p>
                    <button type='submit' style='background-color: #e6e6e6; width:150px; border-radius: 4px;'>Enter</button>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>