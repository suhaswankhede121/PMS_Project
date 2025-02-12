package customer;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbcon.ConDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayParcelBookings")
public class DisplayCustomers extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        try (Connection con = new ConDB().getCon()) {
            if (con == null) {
                out.write("<h3>Database connection failed!</h3>");
                return;
            }

            String sql = "SELECT booking_id, sender_name, sender_address, sender_contact, "
                    + "receiver_name, receiver_address, receiver_pin_code, receiver_contact, "
                    + "size_weight, contents_description, delivery_speed, preferred_date, "
                    + "preferred_time, total_cost, payment_method, booking_date FROM Parcel_Booking";

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                out.write("<h2>Parcel Booking Records</h2>");
                out.write("<table border='1'>");
                out.write("<tr><th>Booking ID</th><th>Sender Name</th><th>Sender Address</th><th>Sender Contact</th>"
                        + "<th>Receiver Name</th><th>Receiver Address</th><th>Receiver Pin Code</th><th>Receiver Contact</th>"
                        + "<th>Size & Weight</th><th>Contents Description</th><th>Delivery Speed</th>"
                        + "<th>Preferred Date</th><th>Preferred Time</th><th>Total Cost</th><th>Payment Method</th>"
                        + "<th>Booking Date</th></tr>");

                while (rs.next()) {
                    out.write("<tr>");
                    out.write("<td>" + rs.getInt("booking_id") + "</td>");
                    out.write("<td>" + rs.getString("sender_name") + "</td>");
                    out.write("<td>" + rs.getString("sender_address") + "</td>");
                    out.write("<td>" + rs.getString("sender_contact") + "</td>");
                    out.write("<td>" + rs.getString("receiver_name") + "</td>");
                    out.write("<td>" + rs.getString("receiver_address") + "</td>");
                    out.write("<td>" + rs.getString("receiver_pin_code") + "</td>");
                    out.write("<td>" + rs.getString("receiver_contact") + "</td>");
                    out.write("<td>" + rs.getBigDecimal("size_weight") + "</td>");
                    out.write("<td>" + rs.getString("contents_description") + "</td>");
                    out.write("<td>" + rs.getString("delivery_speed") + "</td>");
                    out.write("<td>" + rs.getDate("preferred_date") + "</td>");
                    out.write("<td>" + rs.getTime("preferred_time") + "</td>");
                    out.write("<td>" + rs.getBigDecimal("total_cost") + "</td>");
                    out.write("<td>" + rs.getString("payment_method") + "</td>");
                    out.write("<td>" + rs.getTimestamp("booking_date") + "</td>");
                    out.write("</tr>");
                }
                out.write("</table>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error: " + e.getMessage());
        }
    }
}
