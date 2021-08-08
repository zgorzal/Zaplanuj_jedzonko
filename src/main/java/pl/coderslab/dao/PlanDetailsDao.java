package pl.coderslab.dao;


import pl.coderslab.model.PlanDetails;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDetailsDao {
    private static final String LATEST_PLAN = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, plan.name as plan_name, recipe_plan.recipe_id as recipe_id\n" +
            "            FROM recipe_plan\n" +
            "            JOIN day_name on day_name.id=day_name_id\n" +
            "            JOIN plan on plan.id = plan_id\n" +
            "            JOIN recipe on recipe.id=recipe_id\n" +
            "            WHERE\n" +
            "            recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "            ORDER by day_name.display_order, recipe_plan.display_order;";


public PlanDetails readLatest(int userId){
    PlanDetails planDetails = new PlanDetails();
    try (Connection connection = DbUtil.getConnection();
         PreparedStatement statement = connection.prepareStatement(LATEST_PLAN)
    ) {
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            planDetails.setName(resultSet.getString("plan_name"));
            if (resultSet.getString("day_name").equals("poniedziałek")){
                planDetails.getMonday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("wtorek")){
                planDetails.getTuesday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("środa")){
                planDetails.getWednesday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("czwartek")){
                planDetails.getThursday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("piątek")){
                planDetails.getFriday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("sobota")){
                planDetails.getSaturday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }else if (resultSet.getString("day_name").equals("niedziela")){
                planDetails.getSaturday().add(resultSet.getString("meal_name")+";;;"+resultSet.getString("recipe_name")+";;;"+resultSet.getString("recipe_description")+";;;"+resultSet.getString("recipe_id"));
            }

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return planDetails;
}
}


