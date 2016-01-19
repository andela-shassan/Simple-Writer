package checkpoint.andela.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.FileParser;
import checkpoint.andela.parser.Record;

public class Application {
  static BlockingQueue<Record> record;
  private static Future nextTask = null;
 

  
  public Application() throws Exception {
    record = new ArrayBlockingQueue<Record>(1);
  }

  
  private void process() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    Runnable fileParserThread = new FileParser(record,"C:\\Users\\Semiu\\Documents\\myWorkspace\\Simple-Writer\\Files\\reactions.dat");
    Runnable dbWriter = new DBWriter(record);
    Runnable logWriter = new LogWriter("C:\\Users\\Semiu\\Documents\\myWorkspace\\Simple-Writer\\Files\\logFile.txt");
    
    executor.submit(fileParserThread);
    executor.submit(dbWriter);
    executor.submit(logWriter);
    
    if(nextTask == null){
      executor.awaitTermination(2, TimeUnit.SECONDS);
      executor.shutdown();
    }    
    
  }

  
  public static void main(String[] args) throws Exception {          
    Application app = new Application(); 
    app.process();  
  }
}



