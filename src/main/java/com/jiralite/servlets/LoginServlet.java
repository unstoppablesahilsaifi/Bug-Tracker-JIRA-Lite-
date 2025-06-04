package com.jiralite.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    private final String DB_URL = "jdbc:mysql://localhost:3306/bugtrackerdb";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                // Session Tracking
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Cookie (optional)
                if ("on".equals(remember)) {
                    Cookie c = new Cookie("username", username);
                    c.setMaxAge(7 * 24 * 60 * 60); // 7 days
                    response.addCookie(c);
                } else {
                    Cookie c = new Cookie("username", "");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }

                // Redirect based on role
                switch (role) {
                    case "Tester": response.sendRedirect("tester-dashboard.jsp"); break;
                    case "Developer": response.sendRedirect("developer-dashboard.jsp"); break;
                    case "Admin": response.sendRedirect("admin-dashboard.jsp"); break;
                    default: response.sendRedirect("login.jsp"); break;
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

            conn.close();
        } catch (Exception e) {
            throw new ServletException("Login failed", e);
        }
    }
}
