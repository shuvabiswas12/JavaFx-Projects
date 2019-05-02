package sample.database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Config {

    private Connection connection;

    private Connection getConnection() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://" + dbHOst + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, dbUser, dbPassword);

        return connection;
    }

    // insert task
    public void insertTask(Task task) {
        String query = "INSERT INTO " + Const.TASK_TABLE + "("+Const.USERS_ID +","+ Const.TASK +","+ Const.DESCRIPTION + ","+ Const.TASK_DATE_CREATED +") " +
                " VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setString(2, task.getTask());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setTimestamp(4, task.getDateCreated());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // delete task
    public void deleteTask(int userId, int taskId){
        String query = "DELETE FROM "+Const.TASK_TABLE+" WHERE "+Const.USERS_ID+" =?"+" AND "+Const.TASK_ID+" =? ";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,taskId);

            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update task
    public void updateTask(Timestamp date, String description, String task, int taskid) throws SQLException, ClassNotFoundException {
       // String query = "UPDATE"+Const.TASK_TABLE+" SET " +Const.TASK_DATE_CREATED+" = ? "+Const.DESCRIPTION+" =? "+Const.TASK+" =? "+
               // " WHERE "+Const.TASK_ID + " =? ";

        String query = "UPDATE task SET date_time=?, description=?, tasks=? WHERE task_id=?";

        PreparedStatement preparedStatement = getConnection().prepareStatement(query);

        preparedStatement.setTimestamp(1, date);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, task);
        preparedStatement.setInt(4, taskid);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    // write
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRST_NAME + "," + Const.USERS_LAST_NAME + "," +
                Const.USERS_NAME + "," + Const.USERS_LOCATION + "," + Const.USERS_GENDER + "," + Const.USERS_PASSWORD + ")" +
                "VALUES(?,?,?,?,?,?)";

        //PreparedStatement preparedStatement = connection.prepareStatement(insert);
        PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getLocation());
        preparedStatement.setString(5, user.getGender());
        preparedStatement.setString(6, user.getPassword());

        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();

    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        if (!user.getUserName().equals("") && !user.getPassword().equals("")) {
            String searchQuery = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_NAME + " = ? " + " AND " + Const.USERS_PASSWORD + " = ?";

            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(searchQuery);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return resultSet;
    }

    public ResultSet getTaskByUser(int userId) {
        ResultSet resultTask = null;

        String query = "SELECT * FROM " + Const.TASK_TABLE + " WHERE " + Const.USERS_ID + " = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultTask = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultTask;
    }

    public int getAllTask(int userId) throws SQLException, ClassNotFoundException {

        String query = "SELECT COUNT(*) FROM " + Const.TASK_TABLE + " WHERE " + Const.USERS_ID + "=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);

        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }

        //return resultSet.getInt(1);
        return 0;
    }



}
