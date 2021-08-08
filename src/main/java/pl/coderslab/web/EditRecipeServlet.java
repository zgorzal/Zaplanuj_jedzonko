package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditRecipeServlet", value = "/app/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        getting id from parameter and reading object
        String recipeID = request.getParameter("id");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(Integer.parseInt(recipeID));
        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/WEB-INF/app/recipe/edit.jsp").forward(request, response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        handling form
        int recipeID = Integer.parseInt(request.getParameter("recipeID"));
        Recipe recipe = new Recipe();
        RecipeDao recipeDao = new RecipeDao();
        recipe.setId(recipeID);
        recipe.setName(request.getParameter("name"));
        recipe.setIngredients(request.getParameter("ingredients"));
        recipe.setDescription(request.getParameter("description"));
        recipe.setPreparationTime(Integer.parseInt(request.getParameter("preparation_time")));
        recipe.setPreparation(request.getParameter("preparation"));

        recipeDao.update(recipe);

        response.sendRedirect(request.getContextPath() + "/app/recipe/list");

    }
}
