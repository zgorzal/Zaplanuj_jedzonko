package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ListRecipesServlet", value="/app/recipe/list")
public class ListRecipesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");


        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipeList", recipeDao.findAllAdmin(admin.getId()));


        getServletContext().getRequestDispatcher("/WEB-INF/app/recipe/list.jsp")
                .forward(request, response);
    }
}
