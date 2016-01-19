package checkpoint.andela.main;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.*;

import java.util.concurrent.*;

/**
 * Created by Semiu on 19/01/2016.
 */

public class Application {
  private String logFilePath;
  private String reactantFilePath;
  public static BlockingQueue<Record> record = new ArrayBlockingQueue<Record>(1);
  private static Future nextTask = null;

  Runnable fileParserThread;
  Runnable dbWriterThread;
  Runnable logWriterThread;




  public Application(String reactantFilePath, String logFilePath) throws Exception {
    setReactantFilePath(reactantFilePath);
    setLogFilePath(logFilePath);
  }


  public Application() {}


  protected void process() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    fileParserThread = new FileParser(record, reactantFilePath);
    dbWriterThread = new DBWriter(record);
    logWriterThread = new LogWriter(logFilePath);

    executor.submit(fileParserThread);
    executor.submit(dbWriterThread);
    executor.submit(logWriterThread);

    if(nextTask == null){
      executor.awaitTermination(60, TimeUnit.SECONDS);
      executor.shutdown();
    }
  }


  public String getLogFilePath() {
    return logFilePath;
  }


  public void setLogFilePath(String logFilePath) {
    this.logFilePath = logFilePath;
  }


  public String getReactantFilePath() {
    return reactantFilePath;
  }


  public void setReactantFilePath(String reactantFilePath) {
    this.reactantFilePath = reactantFilePath;
  }
}
