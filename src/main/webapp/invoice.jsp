<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Invoice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .invoice-container {
            width: 60%;
            margin: auto;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .info {
            margin: 10px 0;
        }
        .home-button {
            display: block;
            width: 100px;
            margin: 20px auto;
            text-align: center;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .home-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="invoice-container">
        <h2>Invoice</h2>
        <p class="info"><strong>Sender Name:</strong> <%= request.getAttribute("senderName") %></p>
        <p class="info"><strong>Receiver Name:</strong> <%= request.getAttribute("receiverName") %></p>
        <p class="info"><strong>Receiver Address:</strong> <%= request.getAttribute("receiverAddress") %></p>
        <p class="info"><strong>Parcel Weight:</strong> <%= request.getAttribute("sizeWeight") %> kg</p>
        <p class="info"><strong>Contents:</strong> <%= request.getAttribute("contents") %></p>
        <p class="info"><strong>Delivery Speed:</strong> <%= request.getAttribute("deliverySpeed") %></p>
        <p class="info"><strong>Preferred Date:</strong> <%= request.getAttribute("date") %></p>
        <p class="info"><strong>Preferred Time:</strong> <%= request.getAttribute("time") %></p>
        <p class="info"><strong>Cost:</strong> $<%= request.getAttribute("cost") %></p>
        <p class="info"><strong>Payment Method:</strong> <%= request.getAttribute("paymentMethod") %></p>
        
        <a href="HomePage.jsp" class="home-button">Home</a>
    </div>
</body>
</html>
