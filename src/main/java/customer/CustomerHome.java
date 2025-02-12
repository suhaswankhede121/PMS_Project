package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dbcon.ConDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CustomerHome")
public class CustomerHome extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html"); // Ensure proper response type
        PrintWriter out = res.getWriter();
        
        String username = req.getParameter("username");
        String password = req.getParameter("password"); // From HTML
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        try {
            Connection con = new ConDB().getCon();
            if (con != null) {
                String sql = "SELECT Username, Password FROM Customer_Registration WHERE Username=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                
                if (rs.next()) {
                    String hashpass = rs.getString("Password"); // From DB
            
                   // String customerName=rs.getString("CustomerName");
                   
                
                    if (encoder.matches(password, hashpass)) {
                   
                        HttpSession session = req.getSession();
                        session.setAttribute("username", username);
                        session.setMaxInactiveInterval(5 * 60); 
                        res.sendRedirect("HomePage.jsp");
                    } else {
                       
                        out.println("<script>alert('Incorrect Password!'); window.location.href='CustomerLogin.html';</script>");
                    }
                } else {
               
                    out.println("<script>alert('Username Not Found!'); window.location.href='CustomerLogin.html';</script>");
                }
            } else {
                out.println("<script>alert('Database Connection Error!'); window.location.href='CustomerLogin.html';</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Something Went Wrong!'); window.location.href='CustomerLogin.html';</script>");
        }
    }
}
