package customer;

import java.io.IOException;
import java.sql.*;

import dbcon.ConDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ParcelBookingServlet")
public class ParcelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch form data
        request.setAttribute("senderName", request.getParameter("sender-name"));
        request.setAttribute("senderAddress", request.getParameter("sender-address"));
        request.setAttribute("senderContact", request.getParameter("sender-contact"));

        request.setAttribute("receiverName", request.getParameter("receiver-name"));
        request.setAttribute("receiverAddress", request.getParameter("receiver-address"));
        request.setAttribute("receiverPin", request.getParameter("receiver-pin-code"));
        request.setAttribute("receiverContact", request.getParameter("receiver-contact"));

        request.setAttribute("sizeWeight", request.getParameter("size-weight"));
        request.setAttribute("contents", request.getParameter("contents"));
        request.setAttribute("deliverySpeed", request.getParameter("delivery-speed"));
        request.setAttribute("date", request.getParameter("date"));
        request.setAttribute("time", request.getParameter("time"));
        request.setAttribute("cost", request.getParameter("cost"));
        request.setAttribute("paymentMethod", request.getParameter("payment-method"));

        // Forward to invoice page
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoice.jsp");
        dispatcher.forward(request, response);
    }
}