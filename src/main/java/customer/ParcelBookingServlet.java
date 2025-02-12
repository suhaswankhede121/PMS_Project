package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
        try {
            Connection con = new ConDB().getCon();
            String sql = "INSERT INTO Parcel_Booking (sender_name, sender_address, sender_contact, receiver_name, receiver_address, receiver_pin_code, receiver_contact, size_weight, contents_description, delivery_speed, preferred_date, preferred_time, total_cost, payment_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getParameter("sender-name"));
            ps.setString(2, request.getParameter("sender-address"));
            ps.setString(3, request.getParameter("sender-contact"));
            ps.setString(4, request.getParameter("receiver-name"));
            ps.setString(5, request.getParameter("receiver-address"));
            ps.setString(6, request.getParameter("receiver-pin-code"));
            ps.setString(7, request.getParameter("receiver-contact"));
            ps.setDouble(8, Double.parseDouble(request.getParameter("size-weight")));
            ps.setString(9, request.getParameter("contents"));
            ps.setString(10, request.getParameter("delivery-speed"));
            ps.setString(11, request.getParameter("date"));
            ps.setString(12, request.getParameter("time"));
            ps.setDouble(13, Double.parseDouble(request.getParameter("cost")));
            ps.setString(14, request.getParameter("payment-method"));
            
            int affectedRows = ps.executeUpdate();
            int bookingId = -1;
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    bookingId = generatedKeys.getInt(1);
                    // Insert initial status into Parcel_Status with quoted column name
                    String statusSql = "INSERT INTO Parcel_Status (booking_id, \"status\") VALUES (?, 'Pending')";
                    PreparedStatement statusPs = con.prepareStatement(statusSql);
                    statusPs.setInt(1, bookingId);
                    statusPs.executeUpdate();
                }
            }

            con.close();

            // Set attributes before forwarding
            request.setAttribute("bookingId", bookingId);
            request.setAttribute("senderName", request.getParameter("sender-name"));
            request.setAttribute("receiverName", request.getParameter("receiver-name"));
            request.setAttribute("receiverAddress", request.getParameter("receiver-address"));
            request.setAttribute("sizeWeight", request.getParameter("size-weight"));
            request.setAttribute("contents", request.getParameter("contents"));
            request.setAttribute("deliverySpeed", request.getParameter("delivery-speed"));
            request.setAttribute("date", request.getParameter("date"));
            request.setAttribute("time", request.getParameter("time"));
            request.setAttribute("cost", request.getParameter("cost"));
            request.setAttribute("paymentMethod", request.getParameter("payment-method"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("invoice.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
