package com.jiralite.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BugSubmissionServlet extends HttpServlet {
    private final String DB_URL = "jdbc:mysql://localhost:3306/bugtrackerdb";
    private final String DB_USER = "root";
    private final String DB_PASS = "root";

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String username = (String) session.getAttribute("username");  // username string
    String title = request.getParameter("title");
    String description = request.getParameter("description");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            // 1. Get user ID from username
            String getUserIdSql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql);
            getUserIdStmt.setString(1, username);
            ResultSet rs = getUserIdStmt.executeQuery();

            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                // User not found — handle error appropriately
                request.setAttribute("error", "User not found.");
                RequestDispatcher rd = request.getRequestDispatcher("submit-bug.jsp");
                rd.forward(request, response);
                return;
            }
            rs.close();
            getUserIdStmt.close();

            // 2. Insert bug using user ID
            String insertSql = "INSERT INTO bugs (title, description, reported_by, assigned_to, status) VALUES (?, ?, ?, NULL, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, title);
            insertStmt.setString(2, description);
            insertStmt.setInt(3, userId);  // Insert user ID here
            insertStmt.setString(4, "Open");

            insertStmt.executeUpdate();
            insertStmt.close();

            request.setAttribute("message", "Bug submitted successfully!");
            RequestDispatcher rd = request.getRequestDispatcher("submit-bug.jsp");
            rd.forward(request, response);
        }
    } catch (Exception e) {
        throw new ServletException("Error submitting bug", e);
    }
}

}

