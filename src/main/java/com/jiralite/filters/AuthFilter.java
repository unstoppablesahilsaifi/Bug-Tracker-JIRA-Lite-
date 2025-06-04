package com.jiralite.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {}

    // AuthFilter.java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession(false);
    String loggedInUserRole = (session != null) ? (String) session.getAttribute("role") : null;
    String uri = req.getRequestURI();

    // Admin-only pages
    boolean isAdminURI = uri.endsWith("/user-list") || uri.endsWith("/add-user") || uri.endsWith("/edit-user") || uri.endsWith("/delete-user");
    if (isAdminURI && (loggedInUserRole == null || !loggedInUserRole.equalsIgnoreCase("Admin"))) {
        res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        return;
    }

    // General login check
    boolean loggedIn = (session != null && session.getAttribute("username") != null);
    boolean loginRequest = uri.endsWith("login") || uri.endsWith("login.jsp");

    if (loggedIn || loginRequest) {
        chain.doFilter(request, response);
    } else {
        res.sendRedirect("login.jsp");
    }
}

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

    