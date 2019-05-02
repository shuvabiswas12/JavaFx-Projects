package sample.database;

import sample.assests.AlertBox;
import sample.model.BookInformation;

import javax.swing.*;
import java.sql.*;

public class DatabaseHandler {



    private static DatabaseHandler databaseHandler = null;



    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection connection = null;
    private static Statement statement = null;





    private DatabaseHandler() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        getDbConnection();
        setUpTables();
    }








    public static DatabaseHandler getDbInstance() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        if (databaseHandler == null) {
            databaseHandler = new DatabaseHandler();
        }
        return databaseHandler;
    }





    private Connection getDbConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        connection = DriverManager.getConnection(DB_URL);

        return connection;
    }






    public void setUpTables() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

            try {
                statement = connection.createStatement();
                DatabaseMetaData databaseMetaData = connection.getMetaData();

                ResultSet bookTable = databaseMetaData.getTables(null, null, Const.BOOK_TABLE.toUpperCase(), null);

                if (bookTable.next()) {
                    System.out.println("Table "+Const.BOOK_TABLE+" already exists, ready for go!");
                } else {
                    statement.execute(Const.CREATE_BOOK_TABLE);
                }

                ResultSet membarTable = databaseMetaData.getTables(null, null, Const.MEMBAR_TABLE.toUpperCase(), null);

                if (membarTable.next()) {
                    System.out.println("Table "+Const.MEMBAR_TABLE+" already exists, ready for go!");
                } else {
                    statement.execute(Const.CREATE_MEMBAR_TABLE);
                }

                ResultSet issueTable = databaseMetaData.getTables(null, null, Const.ISSUE_TABLE.toUpperCase(), null);

                if (issueTable.next()) {
                    System.out.println("Table "+Const.ISSUE_TABLE+" already exists, ready for go!");
                } else {
                    statement.execute(Const.CREATE_ISSUE_TABLE);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }




        public ResultSet exeQuery(String query) {
            ResultSet resultSet = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        }






        public boolean exeAction(String query) {
            try {
                statement = connection.createStatement();
                statement.execute(query);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Exception at executeQuery:DatabaseHandler "+e.getLocalizedMessage());
                return false;
            }
        }



    public boolean deleteBook(BookInformation book) {

        String query = "DELETE FROM "+Const.BOOK_TABLE+" WHERE "+Const.ID+" = ?";

        try {

            Boolean val = isBookAlreadyIssued(book);
            if (!val) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, book.getBookId());
                int res = preparedStatement.executeUpdate();

                return true;
            } else {
                AlertBox.errorAlert("Can't delete cause of issued the book !");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


        return false;
    }


    public boolean isBookAlreadyIssued(BookInformation book) {


        String countQuery = "SELECT COUNT(*) FROM "+Const.ISSUE_TABLE+" WHERE "+Const.BOOK_ID+" = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(countQuery);

            preparedStatement.setString(1, book.getBookId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                int count = resultSet.getInt(1);
                System.out.println(count);

                if (count > 0) {
                    return  true;
                } else  {
                    return  false;
                }

            }

        } catch(Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean updateBook(BookInformation bookInformation) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        String query = "UPDATE "+Const.BOOK_TABLE+" SET "+Const.TITLE+" = ?, "+Const.AUTHOR+" = ?, "+
                Const.PUBLISHER+" = ? WHERE "+Const.ID+" = ?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, bookInformation.getBookTitle());
        preparedStatement.setString(2, bookInformation.getBookAuthor());
        preparedStatement.setString(3, bookInformation.getBookPublisher());
        preparedStatement.setString(4, bookInformation.getBookId());

        int count = preparedStatement.executeUpdate();

        return (count>0);
    }





}
