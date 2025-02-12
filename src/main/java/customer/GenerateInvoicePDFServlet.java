package customer;

import java.io.IOException;


@jakarta.servlet.annotation.WebServlet("/GenerateInvoiceServlet")
public class GenerateInvoicePDFServlet extends jakarta.servlet.http.HttpServlet {
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        // Set invoice details as request attributes
        request.setAttribute("senderName", request.getParameter("senderName"));
        request.setAttribute("receiverName", request.getParameter("receiverName"));
        request.setAttribute("receiverAddress", request.getParameter("receiverAddress"));
        request.setAttribute("sizeWeight", request.getParameter("sizeWeight"));
        request.setAttribute("contents", request.getParameter("contents"));
        request.setAttribute("deliverySpeed", request.getParameter("deliverySpeed"));
        request.setAttribute("date", request.getParameter("date"));
        request.setAttribute("time", request.getParameter("time"));
        request.setAttribute("cost", request.getParameter("cost"));
        request.setAttribute("paymentMethod", request.getParameter("paymentMethod"));

        // Forward request to invoice.jsp
        jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("invoice.jsp");
        dispatcher.forward(request, response);
    }
}
