package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value="/app/recipe/del")
public class DeleteRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Integer.parseInt(request.getParameter("cancel")) > 0){
            new RecipeDao().delete(Integer.parseInt(request.getParameter("cancel")));
        }
        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("recipe", new RecipeDao().read(recipeId));
        getServletContext().getRequestDispatcher("/WEB-INF/app/recipe/deleterecipe.jsp").forward(request, response);
    }
}
