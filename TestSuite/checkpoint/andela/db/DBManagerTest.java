package checkpoint.andela.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DBManagerTest {

  DBManager dbm = new DBManager();

  @Test
  public void testGetConnection() throws SQLException {
    Connection conn = dbm.getConnection();
    assertTrue(conn.isValid(1000));
    conn.close();
    assertFalse(conn.isValid(1000));
  }

  @Test
  public void testCreateDatabase() {
    dbm.deleteDatabase("andela");
    assertFalse(dbm.databaseExists("andela"));
    dbm.createDatabase("andela");
    assertTrue(dbm.databaseExists("andela"));
    dbm.deleteDatabase("andela");
    assertFalse(dbm.databaseExists("andela"));

  }


  @Test
  public void testCreateTable() throws SQLException {
    String[] columns = {
        "MONDAY",
        "TUESDAY",
        "WEDNESSDAY",
        "THURSDAY",
        "FRIDAY"
    };

    dbm.createDatabase("dbmanagertest");
    dbm.createTable("dbmanagertest", "dbtabletest", columns );
    assertTrue(dbm.databaseExists("dbmanagertest"));
    dbm.deleteTable("dbmanagertest", "dbtabletest");

  }

}
