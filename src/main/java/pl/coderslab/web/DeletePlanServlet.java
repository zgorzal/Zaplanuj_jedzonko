package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePlanServlet", value = "/app/plan/delete")
public class DeletePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("plan", new PlanDao().read(planId));
        getServletContext().getRequestDispatcher("/WEB-INF/app/plan/delPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Integer.parseInt(request.getParameter("cancel")) > 0){
            new PlanDao().deletePlan(Integer.parseInt(request.getParameter("cancel")));
        }
        response.sendRedirect("list");
    }
}
