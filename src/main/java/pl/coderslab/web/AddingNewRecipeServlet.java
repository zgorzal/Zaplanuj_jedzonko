package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddingNewRecipeServlet", value="/app/recipe/add")
public class AddingNewRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparation_time"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        int tmpAdminId = admin.getId();

        Recipe recipe = new Recipe(name, ingredients, description,preparationTime, preparation, tmpAdminId);

        if (name.isEmpty() || description.isEmpty() || preparation.isEmpty() || ingredients.isEmpty()){
            response.getWriter().append("Wypełnij cały formularz");
        } else {
            RecipeDao.create(recipe);
            response.sendRedirect(request.getContextPath()+"/app/recipe/list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/app/recipe/add.jsp")
                .forward(request, response);
    }
}
