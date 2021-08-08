package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddRecipeToPlanServlet", value = "/app/recipe/plan/add")
public class AddRecipeToPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
//        reading plan names from DB to add to dropdown list
        PlanDao planDao = new PlanDao();
        request.setAttribute("plans", planDao.readAll(admin.getId()));

//        reading recipes from DB
        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipes", recipeDao.findAllAdmin(admin.getId()));

        getServletContext().getRequestDispatcher("/WEB-INF/AddRecipeToPlan.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        retrieving data from form and adding new recipe to plan
        int planID = Integer.parseInt(request.getParameter("planName"));
        String mealName = request.getParameter("mealName");
        int mealNumber = Integer.parseInt(request.getParameter("mealNumber"));
        int recipeID = Integer.parseInt(request.getParameter("recipeName"));
        int dayID = Integer.parseInt(request.getParameter("dayName"));


        RecipePlan recipePlan = new RecipePlan(recipeID, mealName, mealNumber, dayID, planID);
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.create(recipePlan);

//        redirecting to servlet after creating recipe_plan object
        response.sendRedirect(request.getContextPath() + "/app/recipe/plan/add");

    }

}


