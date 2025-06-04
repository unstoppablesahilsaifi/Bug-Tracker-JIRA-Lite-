package com.jiralite.servlets;

import com.jiralite.model.Bug;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class BugListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Bug> bugs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtrackerdb", "root", "root")) {
            String sql = "SELECT * FROM bugs";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("DEBUG: Fetching bugs from DB...");

            while (rs.next()) {
                Bug bug = new Bug();
                bug.setId(rs.getInt("id"));
                bug.setTitle(rs.getString("title"));
                bug.setDescription(rs.getString("description"));
                bug.setReportedBy(rs.getInt("reported_by"));
                bug.setAssignedTo(rs.getInt("assigned_to"));
                bug.setStatus(rs.getString("status"));

                System.out.println("DEBUG: Found bug - ID=" + bug.getId() + ", Title=" + bug.getTitle());
                bugs.add(bug);
            }

            System.out.println("DEBUG: Total bugs fetched: " + bugs.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        req.setAttribute("bugs", bugs);
        req.getRequestDispatcher("bug-list.jsp").forward(req, res);
    }
}
