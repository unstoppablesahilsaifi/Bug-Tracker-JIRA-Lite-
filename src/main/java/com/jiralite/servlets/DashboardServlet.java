package com.jiralite.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");
        request.setAttribute("username", session.getAttribute("username"));

        switch (role) {
            case "Tester":
                RequestDispatcher testerDispatcher = request.getRequestDispatcher("tester-dashboard.jsp");
                testerDispatcher.forward(request, response);
                break;
            case "Developer":
                RequestDispatcher devDispatcher = request.getRequestDispatcher("developer-dashboard.jsp");
                devDispatcher.forward(request, response);
                break;
            case "Admin":
                RequestDispatcher adminDispatcher = request.getRequestDispatcher("admin-dashboard.jsp");
                adminDispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }
}
