<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking Service</title>
</head>
<body>
    <h2>Parcel Booking</h2>
    <%
        String paymentStatus = request.getParameter("payment");
        if ("success".equals(paymentStatus)) {
    %>
        <p style="color: green;">Payment successful! Proceed with booking.</p>
    <%
        }
    %>
    <form action="ParcelBookingServlet" method="post">
        <label>Sender Name:</label>
        <input type="text" name="sender-name" required><br>

        <label>Receiver Name:</label>
        <input type="text" name="receiver-name" required><br>

        <label>Address:</label>
        <input type="text" name="receiver-address" required><br>

        <label>Delivery Speed:</label>
        <select name="delivery-speed">
            <option value="Standard">Standard</option>
            <option value="Express">Express</option>
        </select><br>

        <button type="submit">Book Parcel</button>
    </form>
</body>
</html>
