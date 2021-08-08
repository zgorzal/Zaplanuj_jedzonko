package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setFirstName(request.getParameter("name"));
        admin.setLastName(request.getParameter("surname"));
        admin.setEmail(request.getParameter("email"));
        admin.setPassword(request.getParameter("password"));
        admin.setSuperAdmin(1);
        admin.setEnable(1);

        AdminDao adminDao = new AdminDao();
        adminDao.create(admin);

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
