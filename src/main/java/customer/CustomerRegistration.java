package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import dbcon.ConDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CustomerRegistration")
public class CustomerRegistration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");

      
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String mob = req.getParameter("mobile");
        String add = req.getParameter("address");
        String userId = req.getParameter("userid");
        String pass = req.getParameter("password");
        String confirmPass = req.getParameter("confirm-password");
        String pref = req.getParameter("preferences");

       System.out.println(name);
       System.out.println(email);
       System.out.println(code);
       System.out.println(mob);
       System.out.println(add);
       System.out.println(userId);
       System.out.println(pass);
       System.out.println(pref);
//
        // Validate password and confirm password match
        if (!pass.equals(confirmPass)) {
            res.getWriter().write("Passwords do not match!");
            return;
        }

        // Password security checks
        if (pass.length() < 8) {
            res.getWriter().write("Password must be at least 8 characters long!");
            return;
        }

        // Generate random username
        String randomUsername = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        // Encrypt password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPass = encoder.encode(pass);

        try (Connection con = new ConDB().getCon()) { // Ensure connection is closed
            if (con == null) {
                res.getWriter().write("Database connection failed!");
                return;
            }

            // Insert into database
            String sql = "INSERT INTO Customer_Registration (Username, CustomerName, Email, Code, Mobno, Address, UserId, Password, Preferance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) { // Ensure PreparedStatement is closed
                ps.setString(1, randomUsername);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, code);
                ps.setString(5, mob);
                ps.setString(6, add);
                ps.setString(7, userId);
                ps.setString(8, hashedPass);
                ps.setString(9, pref);

                int r = ps.executeUpdate();
                if (r > 0) {
                    res.getWriter().write("Registration Successful! Your username: " + randomUsername);
                } else {
                    res.getWriter().write("Registration Failed! Please try again.");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error: " + e.getMessage());
        }
    }
}
