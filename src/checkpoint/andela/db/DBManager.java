package checkpoint.andela.db;

import java.sql.*;

/**
 * Created by Semiu on 19/01/2016.
 */
public class DBManager {
  private static Connection connection;
  private Statement statement;


  public DBManager() {}


  public Connection getConnection() {
    try{
      Class.forName(Helpers.DRIVER);
      connection = DriverManager.getConnection(Helpers.DB_URL, Helpers.USER, Helpers.PASS);

    }catch(SQLException se){
      se.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }
    return connection;
  }



  public void createDatabase(String DBName ){
    if(databaseExists(DBName)) {
      return;
    }
    try{
      statement = connection.createStatement();
      String database = "CREATE DATABASE " + DBName;
      statement.executeUpdate(database);
    }
    catch(SQLException sqle){
      sqle.printStackTrace();
    }
    catch(Exception e){
      e.printStackTrace();
    }

  }


  public void createTable(String DBName, String tableName, String... tableColumns) throws SQLException {

    //Delete table if the tableName already exist.
    deleteTable(DBName, tableName);
    String table = "CREATE TABLE " + DBName + "." + tableName + " (";
    for(String string : tableColumns){
      table += "`" + string +"`" + " TEXT,";
    }
    table = table.substring(0, table.length() - 1) + ")";
    execute(table);
  }


  public  void execute(String table) {
    try {
      statement = connection.createStatement();
      statement.executeUpdate(table);
    }
    catch (SQLException sqle){
      sqle.printStackTrace();
    }
  }


  public void deleteTable(String DBName, String tableName){
    String deleteTable = "DROP TABLE IF EXISTS "+ DBName + "." + tableName;
    execute(deleteTable);
  }


  public boolean databaseExists(String DBName){
    try{
      ResultSet resultSet = getConnection().getMetaData().getCatalogs();
      while (resultSet.next()) {
        if(resultSet.getString(1).equals(DBName)){
          return true;
        }
      }
      resultSet.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }


  public void deleteDatabase(String DBName){
    if(!databaseExists(DBName)){
      return;
    }
    String deleteDB = "DROP DATABASE "+ DBName;
    execute(deleteDB);
    System.out.println("Database deleted successfully! ");
  }


  public void closeConnection() {
    try{
      if(connection!=null) {
        connection.close();
      }
      if(statement != null){
        statement.close();
      }
    }catch(SQLException sqle){
      sqle.printStackTrace();
    }
  }
}
