package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;



public class RecipeDao {
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?,?,?, now(), now(),?,?, ?)";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ?, ingredients = ?, description = ?, updated = now(), preparation_time=?, preparation = ? WHERE id = ?";
    private static final String READ_RECIPE_QUERY = "SELECT * FROM recipe WHERE id = ?";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe WHERE id = ?";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe";
    private static final String RECIPE_COUNT_QUERY = "SELECT COUNT(*) FROM recipe WHERE admin_id = ?;";
    private static final String FIND_ALL_RECIPES_BY_ADMIN = "SELECT * FROM recipe WHERE admin_id = ? order by id desc ";


    public int getCount(int userId) {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(RECIPE_COUNT_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int totalCount = resultSet.getInt("count(*)");
                return totalCount;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }



    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getDate("created"));
                    recipe.setUpdated(resultSet.getDate("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }


    public static Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setInt(4, recipe.getPreparationTime());
            insertStm.setString(5, recipe.getPreparation());
            insertStm.setInt(6, recipe.getAdminId()); // wzięte z sesji? :)
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Recipe updateRecipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY))
        {
            statement.setInt(6, updateRecipe.getId());
            statement.setString(1, updateRecipe.getName());
            statement.setString(2, updateRecipe.getIngredients());
            statement.setString(3, updateRecipe.getDescription());
            statement.setInt(4, updateRecipe.getPreparationTime());
            statement.setString(5, updateRecipe.getPreparation());

            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Recipe> findAll() {

        try (Connection connection = DbUtil.getConnection()){
            List<Recipe> recipeList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();

                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getDate("created"));
                recipeToAdd.setUpdated(resultSet.getDate("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }
            return recipeList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Recipe> findAllAdmin(int adminID) {

        try (Connection connection = DbUtil.getConnection()){
            List<Recipe> recipeList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_BY_ADMIN);
            statement.setInt(1, adminID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();

                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getDate("created"));
                recipeToAdd.setUpdated(resultSet.getDate("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }
            return recipeList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




}


