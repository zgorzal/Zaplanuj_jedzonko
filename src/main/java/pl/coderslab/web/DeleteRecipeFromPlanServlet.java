package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeFromPlanServlet", value = "/app/plan/recipe/delete")
public class DeleteRecipeFromPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        sending parameters do confirmation view

        request.setAttribute("planID", request.getParameter("planID"));
        request.setAttribute("recipeID", request.getParameter("recipeID"));

        getServletContext().getRequestDispatcher("/WEB-INF/deleteRecipeFromPlan.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        handling deleting confirmation

        int planID = Integer.parseInt(request.getParameter("planID"));
        int recipeID = Integer.parseInt(request.getParameter("recipeID"));
        String deleteConfirmation = request.getParameter("delete");

        if (deleteConfirmation.equals("confirm")) {
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            recipePlanDao.removeRecipeFromPlan(planID, recipeID);
        }
            response.sendRedirect("/app/plan/detail?id=" + planID);







    }
}
