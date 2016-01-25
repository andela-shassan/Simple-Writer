package checkpoint.andela.buffer;

import checkpoint.andela.parser.Reactant;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Semiu on 25/01/2016.
 */
public class Buffers {
  private static BlockingQueue<String> logBuffer = new ArrayBlockingQueue<String>(1);
  private static BlockingQueue<Reactant> fileBuffer = new ArrayBlockingQueue<Reactant>(1);

  public static BlockingQueue<String> getLogBuffer(){
    return logBuffer;
  }

  public static BlockingQueue<Reactant> getFileBuffer(){
    return fileBuffer;
  }
}
