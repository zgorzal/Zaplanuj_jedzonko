package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailPlanServlet", value = "/app/plan/detail")
public class DetailPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plan plan = new PlanDao().read(Integer.parseInt(request.getParameter("id")));
        List<DayName> daysPlan = new DayNameDao().readPlanDays(Integer.parseInt(request.getParameter("id")));
        List<Plan> recipesPlan = new PlanDao().readPlanRecipe(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("plan", plan);
        request.setAttribute("daysPlan", daysPlan);
        request.setAttribute("recipesForPlan", recipesPlan);
        getServletContext().getRequestDispatcher("/WEB-INF/app/plan/detailPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
