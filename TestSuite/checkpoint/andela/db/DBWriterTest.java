package checkpoint.andela.db;

import checkpoint.andela.parser.Record;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

/**
 * Created by Semiu on 19/01/2016.
 */
public class DBWriterTest {

  BlockingQueue<Record> records = new ArrayBlockingQueue<Record>(1000);

  @Test
  public void testRun() {

    Thread dbWriter = new Thread(new DBWriter(records));
    dbWriter.start();

    assertTrue(dbWriter.isAlive());
  }
}