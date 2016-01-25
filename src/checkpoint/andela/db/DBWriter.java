package checkpoint.andela.db;

import checkpoint.andela.buffer.Buffers;
import checkpoint.andela.log.LogManager;
import checkpoint.andela.parser.FileParserObserver;
import checkpoint.andela.parser.Reactant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 19/01/2016.
 */

public class DBWriter extends DBManager implements Runnable {
  private BlockingQueue<Reactant> reactants = Buffers.getFileBuffer();
  LogManager logManager = new LogManager();

  public DBWriter() {}

  @Override
  public void run() {
    try {
      createDatabase(Helpers.DB_NAME.toString());
      createTable(Helpers.DB_NAME.toString(), Helpers.TABLE_NAME.toString(), attribute);
      writeToDB();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Reactant getRecord() throws InterruptedException {
      return reactants.take();
  }

  public void writeToDB() throws InterruptedException, SQLException {
    while(!reactants.isEmpty() || FileParserObserver.INSTANCE.getFileParserStatus()){
      Reactant reactant = getRecord();
      logManager.writeLog("DBWriter", reactant.getUniqueID());
      executePreparedStatement(reactant);
    }
  }

  public void executePreparedStatement(Reactant reactant) throws SQLException {
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String insertRecord = createQueryString(reactant);
    try {
      conn = getConnection();
      preparedStatement = conn.prepareStatement(insertRecord);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String createQueryString(Reactant reactant) {
    String field ="", value ="";
    Hashtable<String, String> rec = reactant.getARecord();
    for (String key : rec.keySet()) {
      field += "`" + key +"`, ";
      value += "'" + rec.get(key) +"', ";
    }
    field = field.substring(0, field.length()-2);
    value = value.substring(0, value.length()-2);
    String insertRecord = "INSERT INTO "+ Helpers.DB_NAME.toString() + "." + Helpers.TABLE_NAME.toString() + "(" + field + " )" + " VALUES (" + value + " )";
    return insertRecord;
  }

  String[] attribute = {
      "UNIQUE-ID",
      "TYPES",
      "COMMON-NAME",
      "ATOM-MAPPINGS",
      "CANNOT-BALANCE?",
      "CITATIONS",
      "COMMENT",
      "COMMENT-INTERNAL",
      "CREDITS",
      "DATA-SOURCE",
      "DBLINKS",
      "DELTAG0",
      "DOCUMENTATION",
      "EC-NUMBER",
      "ENZYMATIC-REACTION",
      "ENZYMES-NOT-USED",
      "EQUILIBRIUM-CONSTANT",
      "HIDE-SLOT?",
      "IN-PATHWAY",
      "INSTANCE-NAME-TEMPLATE",
      "LEFT",
      "MEMBER-SORT-FN",
      "ORPHAN?",
      "PATHOLOGIC-NAME-MATCHER-EVIDENCE",
      "PATHOLOGIC-PWY-EVIDENCE",
      "PHYSIOLOGICALLY-RELEVANT?",
      "PREDECESSORS",
      "PRIMARIES",
      "REACTION-DIRECTION",
      "REACTION-LIST",
      "REGULATED-BY",
      "REQUIREMENTS",
      "RIGHT",
      "RXN-LOCATIONS",
      "SIGNAL",
      "SPECIES",
      "SPONTANEOUS?",
      "STD-REDUCTION-POTENTIAL",
      "SYNONYMS",
      "SYSTEMATIC-NAME",
      "TEMPLATE-FILE",
      "^COEFFICIENT",
      "^COMPARTMENT"
  };
}
