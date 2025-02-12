package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import dbcon.ConDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CardPaymentServlet")
public class CardPaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardNumber = request.getParameter("card-number");
        String cardHolder = request.getParameter("card-holder");
        String expiryDate = request.getParameter("expiry-date");
        String cvv = request.getParameter("cvv");
        String amount = request.getParameter("amount");

        try {
            Connection con = new ConDB().getCon();
            String sql = "INSERT INTO Payment (booking_id, payment_method, card_number, card_holder, expiry_date, cvv, amount, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Completed')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 1); // Assuming booking_id = 1 (Replace with session-based ID)
            ps.setString(2, "Card Payment");
            ps.setString(3, cardNumber);
            ps.setString(4, cardHolder);
            ps.setString(5, expiryDate);
            ps.setString(6, cvv);
            ps.setString(7, amount);

            ps.executeUpdate();
            con.close();

            response.sendRedirect("booking-service.jsp?payment=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

