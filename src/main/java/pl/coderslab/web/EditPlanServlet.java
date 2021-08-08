package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditPlanServlet", value="/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plan plan = new Plan();
        plan.setId(Integer.parseInt(request.getParameter("id")));
        plan.setDescription(request.getParameter("planDescription"));
        plan.setName(request.getParameter("planName"));
        PlanDao planDao = new PlanDao();
        planDao.updatePlan(plan);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        PlanDao planDao = new PlanDao();
        Plan readPlan = planDao.read(Integer.parseInt(id));
        request.setAttribute("plan", readPlan);
        getServletContext().getRequestDispatcher("/WEB-INF/app/plan/editPlan.jsp")
                .forward(request, response);
    }
}
