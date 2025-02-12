package officer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.ConDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateDeliveryStatusServlet")
public class UpdateDeliveryStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String bookingId = request.getParameter("bookingId");

        if (bookingId == null || bookingId.isEmpty()) {
            out.write("Invalid Booking ID");
            return;
        }

        try {
            Connection con = new ConDB().getCon();

            if ("search".equals(action)) {
                // Fetch status from Parcel_Status table
                String sql = "SELECT \"status\" FROM Parcel_Status WHERE booking_id = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(bookingId));
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    out.write(rs.getString("status")); // Use "status" instead of STATUS
                } else {
                    out.write("Not Found");
                }
            } else if ("update".equals(action)) {
                String newStatus = request.getParameter("newStatus");
                if (newStatus == null || newStatus.isEmpty()) {
                    out.write("Invalid Status");
                    return;
                }

                // Update the status in Parcel_Status table
                String sql = "UPDATE Parcel_Status SET \"status\" = ? WHERE booking_id = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, Integer.parseInt(bookingId));

                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    out.write("Status Updated Successfully");
                } else {
                    out.write("Failed to Update Status");
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.write("Error: " + e.getMessage());
        }
    }
}
