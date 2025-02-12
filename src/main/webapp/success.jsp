<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="dbcon.ConDB" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Parcel Booking Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Parcel Booking Records</h2>
    
    <table>
        <tr>
            <th>Booking ID</th>
            <th>Sender Name</th>
            <th>Sender Address</th>
            <th>Sender Contact</th>
            <th>Receiver Name</th>
            <th>Receiver Address</th>
            <th>Receiver Pin Code</th>
            <th>Receiver Contact</th>
            <th>Size & Weight</th>
            <th>Contents Description</th>
            <th>Delivery Speed</th>
            <th>Preferred Date</th>
            <th>Preferred Time</th>
            <th>Total Cost</th>
            <th>Payment Method</th>
            <th>Booking Date</th>
        </tr>
        <%
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = new ConDB().getCon();
                String sql = "SELECT * FROM Parcel_Booking ORDER BY booking_date DESC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("booking_id") %></td>
            <td><%= rs.getString("sender_name") %></td>
            <td><%= rs.getString("sender_address") %></td>
            <td><%= rs.getString("sender_contact") %></td>
            <td><%= rs.getString("receiver_name") %></td>
            <td><%= rs.getString("receiver_address") %></td>
            <td><%= rs.getString("receiver_pin_code") %></td>
            <td><%= rs.getString("receiver_contact") %></td>
            <td><%= rs.getBigDecimal("size_weight") %></td>
            <td><%= rs.getString("contents_description") %></td>
            <td><%= rs.getString("delivery_speed") %></td>
            <td><%= rs.getDate("preferred_date") %></td>
            <td><%= rs.getTime("preferred_time") %></td>
            <td><%= rs.getBigDecimal("total_cost") %></td>
            <td><%= rs.getString("payment_method") %></td>
            <td><%= rs.getTimestamp("booking_date") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<tr><td colspan='16'>Error retrieving data: " + e.getMessage() + "</td></tr>");
            } finally {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            }
        %>
    </table>
</body>
</html>
