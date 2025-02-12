<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %> <!-- Enable session tracking -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #8e9da8;
        }
        .navbar {
            background-color: hwb(180 6% 93%);
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }
        .navbar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex;
        }
        .navbar ul li {
            padding: 10px 15px;
        }
        .navbar ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        .navbar ul li a:hover {
            text-decoration: underline;
        }
        .welcome {
            display: flex;
            align-items: center;
            gap: 10px;
            padding-right: 20px;
            font-size: 18px;
            font-weight: bold;
        }
        .logout-form {
            margin: 0;
        }
        input[type="submit"] {
            background-color: red;
            color: white;
            padding: 8px 12px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <%
        String username2 = (String) session.getAttribute("username");
        if (username2 == null) {
            response.sendRedirect("CustomerLogin.html"); 
        }
        
    %>

    <div class="navbar">
        <ul>
            <li><a href="HomePage.jsp">Home</a></li>
            <li><a href="parcelBooking.jsp">Booking Service</a></li>
            <li><a href="TrackingCustomer.html">Tracking</a></li>
            <li><a href="BookingHistory.jsp">Previous Booking</a></li>
            <li><a href="CustomerSupport.jsp">Contact Support</a></li>
             <li><a href="success.jsp">Booking Service Records</a></li>
        </ul>
        <div class="welcome">
            Welcome, <span id="username"><%= username2 %></span>
            <form class="logout-form" action="LogoutServlet" method="post">
                <input type="submit" value="Logout">
            </form>
        </div>
    </div>

</body>
</html>
