package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    // pytania SQL
    private static final String READ_ALL_PLANS = "SELECT id, name, description, created, admin_id FROM plan WHERE admin_id = ? ORDER BY created DESC;";
    private static final String READ_PLAN = "SELECT id, name, description, created, admin_id FROM plan WHERE id = ?;";
    private static final String CREATE_PLAN = "INSERT INTO plan (name, description, created, admin_id) values (?, ?, now(), ?);";
    private static final String DELETE_PLAN = "DELETE FROM plan WHERE id = ?;";
    private static final String UPDATE_PLAN = "UPDATE plan SET name = ?, description = ? WHERE id = ?;";
    private static final String PLAN_COUNT = "SELECT COUNT(*) FROM plan WHERE admin_id = ?;";
    private static final String RECIPE_BY_DAY_PLAN = "SELECT\n" +
                                                     "    rp.day_name_id dayId, rp.meal_name mealName, r.description recipeDescription, rp.recipe_id recipeId\n" +
                                                     "FROM\n" +
                                                     "    recipe_plan rp\n" +
                                                     "        INNER JOIN recipe r ON rp.recipe_id = r.id\n" +
                                                     "            INNER JOIN day_name dn ON rp.day_name_id = dn.id\n" +
                                                     "WHERE plan_id = ?\n" +
                                                     "ORDER BY rp.display_order;";
    private static final String PLAN_LATEST = "SELECT id FROM plan where id=(SELECT MAX(id) from plan WHERE admin_id = ?);";

    public Plan read(int planId){
        Plan plan = new Plan();
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(READ_PLAN)){
            preStmt.setInt(1, planId);
            ResultSet resultSet = preStmt.executeQuery();
            while(resultSet.next()){
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                plan.setAdminId(resultSet.getInt("admin_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return plan;
    }

    public List<Plan> readAll(int adminId){
        List<Plan> plans = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(READ_ALL_PLANS);) {
            preStmt.setInt(1, adminId);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()){
                Plan addPlan = new Plan();
                addPlan.setId(resultSet.getInt("id"));
                addPlan.setName(resultSet.getString("name"));
                addPlan.setDescription(resultSet.getString("description"));
                addPlan.setCreated(resultSet.getString("created"));
                addPlan.setAdminId(resultSet.getInt("admin_id"));
                plans.add(addPlan);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plans;
    }

    public Plan createPlan(Plan plan) {
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(CREATE_PLAN, PreparedStatement.RETURN_GENERATED_KEYS)){
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setInt(3, plan.getAdminId());
            int result = preStmt.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            ResultSet resultSetGenKey = preStmt.getGeneratedKeys();
            if(resultSetGenKey.first()){
                plan.setId(resultSetGenKey.getInt(1));
                return plan;
            } else {
                throw new RuntimeException("Generated key was not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePlan(int planId){
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(DELETE_PLAN)) {
            preStmt.setInt(1, planId);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlan(Plan plan){
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(UPDATE_PLAN)) {
            preStmt.setInt(3, plan.getId());
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCount(int userId) {
        try (Connection conn = DbUtil.getConnection()){

            PreparedStatement statement = conn.prepareStatement(PLAN_COUNT);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");

            }
        }catch (SQLException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public List<Plan> readPlanRecipe(int planId){
        List<Plan> recipePlan = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(RECIPE_BY_DAY_PLAN)) {
            preStmt.setInt(1, planId);
            ResultSet resSet = preStmt.executeQuery();
            while(resSet.next()){
                recipePlan.add(new Plan(resSet.getInt("dayId"),
                                        resSet.getString("mealName"),
                                        resSet.getString("recipeDescription"),
                                        resSet.getInt("recipeId")));
            }
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipePlan;
    }
    public Plan latestPlan(int userId) {
        try (Connection conn = DbUtil.getConnection()){

            PreparedStatement statement = conn.prepareStatement(PLAN_LATEST);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return read(resultSet.getInt("id"));

            }
        }catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
