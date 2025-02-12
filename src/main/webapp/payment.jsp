<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Select Payment Method</title>
    <script>
        function showPayNow() {
            var paymentMode = document.getElementById("payment-mode").value;
            var payNowButton = document.getElementById("pay-now-btn");
            if (paymentMode === "card") {
                payNowButton.style.display = "block";
            } else {
                payNowButton.style.display = "none";
            }
        }

        function proceedToPay() {
            window.location.href = "paybill.jsp";
        }
    </script>
</head>
<body>
    <h2>Select Payment Method</h2>
    <form>
        <label>Mode of Payment:</label>
        <select id="payment-mode" onchange="showPayNow()">
            <option value="">Select...</option>
            <option value="card">Card Payment</option>
        </select>
        <br>
        <button type="button" id="pay-now-btn" style="display: none;" onclick="proceedToPay()">Proceed to Pay</button>
    </form>
</body>
</html>
