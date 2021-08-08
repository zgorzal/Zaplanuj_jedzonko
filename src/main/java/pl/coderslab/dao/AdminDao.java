package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin, enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";
    private static final String CHECK_USER = "SELECT COUNT(*) FROM admins WHERE email = ? AND password = ?;";
    private static final String CHECK_USER_GET_ID_PASS = "SELECT MAX(id) id, MAX(password) password FROM admins WHERE email = ? GROUP BY email;";
    private static final String CHECK_PASSWORD_QUERY = "SELECT password FROM admins WHERE email = ?";
    private static final String GET_ID_QUERY = "SELECT id FROM admins WHERE email = ?";

    public Admin read(int id) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("superadmin"),
                        resultSet.getInt("enable"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("superadmin"),
                        resultSet.getInt("enable"));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setAdmin(admin, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            setAdmin(admin, statement);
            statement.setInt(7, admin.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAdmin(Admin admin, PreparedStatement statement) throws SQLException {
        statement.setString(1, admin.getFirstName());
        statement.setString(2, admin.getLastName());
        statement.setString(3, admin.getEmail());
        statement.setString(4, admin.getPassword());
        statement.setInt(5, admin.getSuperAdmin());
        statement.setInt(6, admin.getEnable());
    }

    public int checkUser(HttpServletRequest request) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(CHECK_USER);) {
            // w dwóch ponizszych liniach uzupełniamy selecta o zmienne sesyjne logowania i sprawdzenie czy taki użytkownik istnieje w bazie
            preStmt.setString(1, String.valueOf(request.getSession().getAttribute("userEmail")));
            preStmt.setString(2, String.valueOf(request.getSession().getAttribute("userPass")));
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Admin checkUserIdPass(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preStmt = connection.prepareStatement(CHECK_USER_GET_ID_PASS);) {
            preStmt.setString(1, email);
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setEmail(resultSet.getString("password"));
                ;
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    private boolean checkPassword(String email, String password) {
        boolean passwordCorrect = false;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_PASSWORD_QUERY)
        ) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString("password"))) {
                    passwordCorrect = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordCorrect;
    }

    public Admin loginAdmin(String email, String password) {
        Admin admin = null;
        if (checkPassword(email, password)) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(GET_ID_QUERY)
            ) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    admin = read(resultSet.getInt("id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admin;
    }
}