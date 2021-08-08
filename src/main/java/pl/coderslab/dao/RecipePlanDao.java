package pl.coderslab.dao;

import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;

//class contains methods needed to handle recipe_plan table


public class RecipePlanDao {

    private static final String CREATE_RECIPE_PLAN = "INSERT INTO recipe_plan" +
            "(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?)";
    private static final String DELETE_RECIPE_BY_ID_QUERY= "DELETE FROM recipe_plan WHERE plan_id = ? AND recipe_id = ?";

    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RECIPE_PLAN, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, recipePlan.getRecipeId());
            preparedStatement.setString(2, recipePlan.getMealName());
            preparedStatement.setInt(3, recipePlan.getDisplayOrder());
            preparedStatement.setInt(4, recipePlan.getDayNameId());
            preparedStatement.setInt(5, recipePlan.getPlanId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                recipePlan.setId(resultSet.getInt(1));
            }
            return recipePlan;
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeRecipeFromPlan(int planId, int recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_BY_ID_QUERY)) {
            preparedStatement.setInt(1,planId);
            preparedStatement.setInt(2, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
