package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.PlanDetailsDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        PlanDao planDao = new PlanDao();
        request.setAttribute("plan_count",planDao.getCount(admin.getId()));
        try {
            request.setAttribute("plan_name",planDao.latestPlan(admin.getId()).getName());
        } catch (NullPointerException e){
            request.setAttribute("plan_name","Brak dodanych planów");
        }

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipe_count",recipeDao.getCount(admin.getId()));
        PlanDetailsDao planDetailsDao = new PlanDetailsDao();
        request.setAttribute("latest_plan",planDetailsDao.readLatest(admin.getId()));
        getServletContext().getRequestDispatcher("/WEB-INF/app/dashboard/dashboard.jsp").forward(request, response);
        System.out.println(planDetailsDao.readLatest(1));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
