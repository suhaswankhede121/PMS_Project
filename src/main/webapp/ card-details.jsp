<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Card Payment</title>
</head>
<body>
    <h2>Enter Card Details</h2>
    <form action="CardPaymentServlet" method="post">
        <label>Card Number:</label>
        <input type="text" name="card-number" required>
        <br>
        <label>Card Holder Name:</label>
        <input type="text" name="card-holder" required>
        <br>
        <label>Expiry Date:</label>
        <input type="text" name="expiry-date" placeholder="MM/YY" required>
        <br>
        <label>CVV:</label>
        <input type="text" name="cvv" required>
        <br>
        <input type="hidden" name="amount" value="20.00">
        <button type="submit">Submit Payment</button>
    </form>
</body>
</html>
