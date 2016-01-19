package checkpoint.andela.db;

import static org.junit.Assert.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.junit.Test;

import checkpoint.andela.parser.Record;

public class DBWriterTest {

  BlockingQueue<Record> records = new ArrayBlockingQueue<Record>(1000);

  @Test
  public void testRun() {
    
    Thread dbWriter = new Thread(new DBWriter(records));
    dbWriter.start();

    assertTrue(dbWriter.isAlive());
  }
}
