package dao;

import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static dao.UserDao instance;
    private Connection dbConnection = DbConnector.getDbConnection();
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private UserDao() {
    }

    public static synchronized dao.UserDao getInstance() {
        if (instance == null) {
            instance = new dao.UserDao();
        }
        return instance;
    }

    public boolean addUser(User user) {

        String addUserIntoTableSql = "INSERT INTO mdblogger.users " +
                "(firstName, lastName, login, pass, adress, email, roleId) " +
                "VALUES (?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(addUserIntoTableSql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAdress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getRoleId());
            preparedStatement.executeUpdate();
            LOGGER.debug(addUserIntoTableSql);
        } catch (SQLException e) {
            LOGGER.error("Can`t insert user into database", e);
            e.printStackTrace();
        }
        return true;
    }

    public Optional<User> getUserById(long id) {
        String selectFromTableSql = "select * from mdblogger.users " +
                "where idUsers = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTableSql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long usersId = resultSet.getLong("idUser");
                String userFirstName = resultSet.getString("firstName");
                String userLastName = resultSet.getString("lastName");
                String userLogin = resultSet.getString("login");
                String userPass = resultSet.getString("pass");
                String userAdress = resultSet.getString("adress");
                String userEmail = resultSet.getString("email");
                String userRole = resultSet.getString("roleId");
                User newUser = new User(usersId, userFirstName, userLastName, userLogin, userPass, userAdress, userEmail, userRole);
                return Optional.of(newUser);
            }
            LOGGER.info("get user info is successfull");
        } catch (SQLException e) {
            LOGGER.error("Can`t get user by id", e);
        }
        return Optional.empty();
    }

    public long getIdByName(String firstName, String lastName) {
        long idUser = 1;
        String selectUserByNameSql = "select * from mdblogger.users where idUsers = ? AND idUsers = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectUserByNameSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idUser = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t get user by user`s name", e);
        }
        return idUser;
    }

    public Optional<User> getUserByLoginAndPass(String log, String pass) {
        User user = null;
        String selectFromTable = "select * from mdblogger.users where login=? AND pass=?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTable)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, log);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int idUser = resultSet.getInt("idUsers");
                    String userFirstName = resultSet.getString("firstName");
                    String userLastName = resultSet.getString("lastName");
                    String userLogin = resultSet.getString("login");
                    String userPass = resultSet.getString("pass");
                    String userAdress = resultSet.getString("adress");
                    String userEmail = resultSet.getString("email");
                    String userRole = resultSet.getString("roleId");
                    user = new User(idUser, userFirstName, userLastName, userLogin, userPass, userAdress, userEmail, userRole);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t get user by login and password", e);
        }
        return Optional.empty();
    }

    public long getIdByEmail(String email) {
        long idLocal = 1;
        String selectFromTable = "select * from mdblogger.users " +
                "where idUsers = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTable)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idLocal = resultSet.getLong("idUsers");
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t get ID by email", e);
            e.printStackTrace();
        }
        return idLocal;
    }

    public long getIdBylogin(String login) {
        long idUser = 1;
        String selectFromTable = "select * from mdblogger.users " +
                "where idUsers = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTable)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idUser = resultSet.getLong("idUsers");
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t get ID by login", e);
            e.printStackTrace();
        }
        return idUser;
    }

    public void deleteUserById(long id) {
        String deleteFromTableSql = "delete * from mdblogger.users " +
                "where idUsers = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(deleteFromTableSql)) {
            if (preparedStatement != null) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t delete user by ID", e);
            e.printStackTrace();
        }
    }

    public boolean CheckUserExist(String firstName, String lastName) {
        boolean isDuplicated = false;
        String isUserIntoTableSql = "select * from mdblogger.users " +
                "where firstName=? AND lastName=?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(isUserIntoTableSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isDuplicated = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Such User don`t exist", e);
            e.printStackTrace();
        }
        return isDuplicated;
    }

    public boolean CheckUserExist(String email) {
        boolean isDuplicated = false;
        String isUserIntoTableSql = "SELECT * FROM mdblogger.users WHERE email = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(isUserIntoTableSql)) {
            preparedStatement.setString(1, email);
            LOGGER.info("preparedStatement");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isDuplicated = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Such User don`t exist", e);
            e.printStackTrace();
        }
        return isDuplicated;
    }

    public void updateUser(String login, String password) {

        String updateUserIntoTableSql = "UPDATE mdblogger.users SET " +
                "firstName = ?, lastName= ?, login= ?, pass= ?, adress= ?, email= ? " +
                "WHERE (login = ?)";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(updateUserIntoTableSql)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, getUserByLoginAndPass(login, password).get().getFirstName());
                preparedStatement.setString(2, getUserByLoginAndPass(login, password).get().getLastName());
                preparedStatement.setString(3, getUserByLoginAndPass(login, password).get().getLogin());
                preparedStatement.setString(4, getUserByLoginAndPass(login, password).get().getAdress());
                preparedStatement.setString(5, getUserByLoginAndPass(login, password).get().getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t update user", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT * FROM mdblogger.users";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong("idUsers");
                String firstname = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String adress = resultSet.getString("adress");
                String email = resultSet.getString("email");
                String userRole = resultSet.getString("roleId");
                User user = new User(userId, firstname, lastName, login, pass, adress, email, userRole);
                usersList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("database is not exist", e);
            e.printStackTrace();
        }
        return usersList;
    }
}
