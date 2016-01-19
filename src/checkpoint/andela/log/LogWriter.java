package checkpoint.andela.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import checkpoint.andela.parser.Completed;

public class LogWriter extends LogManager implements Runnable{
  private static BlockingQueue<String>  buffer = LogBuffer.getLogBuffer();
  private BufferedWriter bufferedWriter;
  private String logFilePath;

  
  public LogWriter(String logFilePath) {
    this.logFilePath = logFilePath;
  }

  
  @Override
  public void run() {
    try {
      writeToFile();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public void writeToFile() throws InterruptedException, IOException{
    File file = new File(logFilePath);
    if(!file.exists()){
      file.createNewFile();
    }
    bufferedWriter = new BufferedWriter(new FileWriter(file));
    while(!buffer.isEmpty() || !Completed.INSTANCE.getComplete()){
    bufferedWriter.write(getLog());
    bufferedWriter.newLine();
    bufferedWriter.flush();}
  }
  
  
  private String getLog() throws InterruptedException{
    return  buffer.take();
  }
}
