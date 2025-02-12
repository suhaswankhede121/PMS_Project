<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Pay Bill</title>
</head>
<body>
    <h2>Bill Payment</h2>
    <form action="CardPaymentServlet" method="post">
        <label>Amount to Pay:</label>
        <input type="text" name="amount" value="20.00" readonly>
        <br>
        <button type="submit">Proceed to Card Details</button>
    </form>
</body>
</html>
