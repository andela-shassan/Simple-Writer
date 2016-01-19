package checkpoint.andela.log;

/**
 * Created by Semiu on 19/01/2016.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LogBuffer {
  private static BlockingQueue<String> buffer = new ArrayBlockingQueue<String>(1);


  public static BlockingQueue<String> getLogBuffer(){
    return buffer;
  }
}