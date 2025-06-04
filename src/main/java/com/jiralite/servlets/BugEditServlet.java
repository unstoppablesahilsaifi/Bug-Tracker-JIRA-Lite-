package com.jiralite.servlets;

import com.jiralite.model.Bug;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BugEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int bugId = Integer.parseInt(req.getParameter("id"));
        Bug bug = null;
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackerdb", "root", "root")) {
            String sql = "SELECT * FROM bugs WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bugId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bug = new Bug();
                bug.setId(rs.getInt("id"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setReportedBy(rs.getInt("reported_by"));
                bug.setAssignedTo(rs.getInt("assigned_to"));
                bug.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.setAttribute("bug", bug);
        req.getRequestDispatcher("edit-bug.jsp").forward(req, res);
    }
}
