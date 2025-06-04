package com.jiralite.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BugUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int assignedTo = Integer.parseInt(req.getParameter("assigned_to"));
        String status = req.getParameter("status");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackerdb", "root", "root")) {
            String sql = "UPDATE bugs SET title=?, description=?, assigned_to=?, status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, assignedTo);
            ps.setString(4, status);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException(e);
        }

        res.sendRedirect("bug-list");
    }
}
