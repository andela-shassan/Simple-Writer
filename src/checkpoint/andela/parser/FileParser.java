package checkpoint.andela.parser;

import checkpoint.andela.log.LogManager;

import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 19/01/2016.
 */
public class FileParser implements Runnable {
  private BufferedReader bufferedReader;
  private BlockingQueue<Record> records;
  LogManager logManager = new LogManager();

  public FileParser(){}


  public FileParser(BlockingQueue<Record> records, String filePath) throws Exception {
    bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
    this.records = records;
  }


  @Override
  public void run() {
    writeToBuffer();
  }


  private void writeToBuffer() {
    try{
      Record aRecord = new Record();
      String line;
      while ((line = bufferedReader.readLine()) != null){
        Completed.INSTANCE.setCompleted(false);
        if(isComment(line)){
          continue;
        }
        if(isDelimiter(line)){
          records.put(aRecord);
          logManager.writeLog("FileParser", aRecord.getUniqueID());
          aRecord = new Record();
        }
        if(line.length() > 2){
          OrderedPair pair = new OrderedPair();
          processLine(aRecord, line, pair);
        }
      }
      Completed.INSTANCE.setCompleted(true);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


  protected void processLine(Record aRecord, String line, OrderedPair pair) {
    pair.setAttribute(line.substring(0, line.indexOf(" ")));
    pair.setValue(line.substring((line.indexOf(" ") + 3)));
    aRecord.addPair(pair);
  }


  boolean isComment(String line){
    if(line.startsWith("#")){
      return true;
    }
    return false;
  }


  boolean isDelimiter(String line){
    if(line.startsWith("//")){
      return true;
    }
    return false;
  }
}
