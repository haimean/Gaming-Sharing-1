/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author s
 */
public class DBContext {

    private final String serverName = "localhost";
    private final String dbName = "GamingSharing";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "12345678";
    public Connection connection;

  public Connection getConnection(){
    Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      String url =
        "jdbc:sqlserver://" +
        serverName +
        ":" +
        portNumber +
        ";databaseName=" +
        dbName;
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void closeConnection(
            Connection connection,
            PreparedStatement preparedStatement,
            ResultSet resultSet
    ) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void closeConnection(
            Connection connection,
            PreparedStatement preparedStatement
    ) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public static String createSlug(String title) {
        String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        slug = pattern.matcher(slug).replaceAll("");
        slug = slug.toLowerCase();
        slug = slug.replaceAll("đ", "d");
        slug = slug.replaceAll("([^0-9a-z-\\s])", "");
        slug = slug.replaceAll("[\\s]", "-");
        slug = slug.replaceAll("(-+)", "-");
        slug = slug.replaceAll("^-+", "");
        slug = slug.replaceAll("-+$", "");
        return slug;
    }
}
