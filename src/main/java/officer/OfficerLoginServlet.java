package officer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.ConDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/OfficerLoginServlet")
public class OfficerLoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
    , IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            
            Connection con =new ConDB().getCon();
//            
//            // Check if user exists
//            String query = "SELECT * FROM officers WHERE username = ? AND password = ?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();

            if (username.equals("admin") && password.equals("Admin@123")) {
                // Set session attribute
                HttpSession session = request.getSession();
                session.setAttribute("officer", username);

                // Redirect to officer home page
                response.sendRedirect("HomePageOfficer.html");
            } else {
                // Redirect back to login with error
                response.sendRedirect("OfficerLogin.jsp?error=1");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("OfficerLogin.jsp?error=1");
        }
    }
}

