package checkpoint.andela.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 19/01/2016.
 */

public class LogManager {
  private BlockingQueue<String> buffer = LogBuffer.getLogBuffer();


  public LogManager() {}


  public void writeLog(String logger, String uniqueValue) throws InterruptedException{
    String log = logger + " Thread(" + formatDate() + ") ----" + getAction(logger) +" UNIQUE-ID " + uniqueValue + " to  "+ destination(logger);
    buffer.put(log);
  }


  private String destination(String logger) {
    if(logger.equals("FileParser")){
      return "buffer.";
    }
    return "database.";
  }


  private  String formatDate(){
    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD  HH:mm:ss");
    Date  date = new Date();
    return formatter.format(date);
  }


  private String getAction(String string){
    if(string.equals("FileParser")){
      return " wrote ";
    }
    return " collected and saved ";
  }
}
