package checkpoint.andela.db;

import checkpoint.andela.log.LogManager;
import checkpoint.andela.parser.Completed;
import checkpoint.andela.parser.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 19/01/2016.
 */

public class DBWriter extends DBManager implements Runnable {
  private BlockingQueue<Record> records;
  LogManager logManager = new LogManager();


  public DBWriter(BlockingQueue<Record> records ) {
    this.records = records;
  }


  @Override
  public void run() {
    try {
      createDatabase("reactiondb");
      createTable("reactiondb", "reactions", Helpers.attribute);
      writeToDB();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }


  public Record getRecord() throws InterruptedException {
    return records.take();
  }


  public void writeToDB() throws InterruptedException, SQLException {
    while(!records.isEmpty() || !Completed.INSTANCE.getComplete()){
      Record record = getRecord();
      logManager.writeLog("DBWriter", record.getUniqueID());
      executePreparedStatement(record);
    }
  }


  public void executePreparedStatement(Record record) throws SQLException {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String insertRecord = createQueryString(record);
    try {
      conn = getConnection();
      preparedStatement = conn.prepareStatement(insertRecord);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  private String createQueryString(Record record) {
    String field ="", value ="";

    Hashtable<String, String> rec = record.getARecord();
    for (String key : rec.keySet()) {
      field += "`" + key +"`, ";
      value += "'" + rec.get(key) +"', ";
    }
    field = field.substring(0, field.length()-2);
    value = value.substring(0, value.length()-2);
    String insertRecord = "INSERT INTO reactiondb.reactions (" + field + " )" + " VALUES (" + value + " )";
    return insertRecord;
  }
}
