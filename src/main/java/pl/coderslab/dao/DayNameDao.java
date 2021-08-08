package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

//SQL queries

    private static final String FIND_ONE_DAY = "SELECT * from day_name where id = ?";
    private static final String FIND_ALL_DAYS = "SELECT * from day_name";
    private static final String FIND_DAY_ID = "SELECT ID from day_name where name = ?";
    private static final String DAYS_IN_PLAN = "SELECT DISTINCT \n" +
                                                "    rp.day_name_id dayId, \n" +
                                                "    dn.name dayName\n" +
                                                "FROM \n" +
                                                "    recipe_plan rp \n" +
                                                "        INNER JOIN day_name dn on rp.day_name_id = dn.id\n" +
                                                "WHERE rp.plan_id = ?\n" +
                                                "ORDER BY 1;";

    public DayName findOneDay(int id) {
        DayName dayName = new DayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE_DAY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dayName.setId(resultSet.getInt("id"));
                dayName.setName(resultSet.getString("name"));
                dayName.setDisplayOrder(resultSet.getInt("displayOrder"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayName;
    }

    public List<DayName> findAll() {
        List<DayName> dayNames = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DAYS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DayName dayNameToAdd = new DayName();
                dayNameToAdd.setId(resultSet.getInt("id"));
                dayNameToAdd.setName(resultSet.getString("name"));
                dayNameToAdd.setDisplayOrder(resultSet.getInt("displayOrder"));
                dayNames.add(dayNameToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNames;
    }


    public int getNameID(String name) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_DAY_ID)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int nameID = resultSet.getInt("id");
                return nameID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
    }

    public List<DayName> readPlanDays(int planId){
        List<DayName> listDays = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection();
            PreparedStatement preStmt = connection.prepareStatement(DAYS_IN_PLAN);) {
            preStmt.setInt(1, planId);
            ResultSet resSet = preStmt.executeQuery();
            while(resSet.next()){
                DayName dayName = new DayName();
                dayName.setId(resSet.getInt("dayId"));
                dayName.setName(resSet.getString("dayName"));
                listDays.add(dayName);
            }
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listDays;
    }
}



