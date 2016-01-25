package checkpoint.andela.parser;

import checkpoint.andela.buffer.Buffers;
import checkpoint.andela.log.LogManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 19/01/2016.
 */
public class FileParser implements Runnable {
  private BufferedReader bufferedReader;
  private BlockingQueue<Reactant> reactants = Buffers.getFileBuffer();
  LogManager logManager = new LogManager();

  public FileParser(){}

  public FileParser(String filePath) throws Exception {
    bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
  }

  @Override
  public void run() {
    writeToBuffer();
  }

  private void writeToBuffer() {
    try{
      Reactant aReactant = new Reactant();
      String line;
      while ((line = bufferedReader.readLine()) != null){
        if(isComment(line)){
          continue;
        }
        if(isDelimiter(line)){
          reactants.put(aReactant);
          logManager.writeLog("FileParser", aReactant.getUniqueID());
          aReactant = new Reactant();
        }
        if(line.length() > 2){
          OrderedPair pair = new OrderedPair();
          processLine(aReactant, line, pair);
        }
      }
      FileParserObserver.INSTANCE.setFileParserStatus(false);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  protected void processLine(Reactant aReactant, String line, OrderedPair pair) {
    pair.setAttribute(line.substring(0, line.indexOf(" ")));
    pair.setValue(line.substring((line.indexOf(" ") + 3)));
    aReactant.addPair(pair);
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
