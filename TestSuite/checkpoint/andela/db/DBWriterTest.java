package checkpoint.andela.db;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Semiu on 19/01/2016.
 */
public class DBWriterTest {

  @Test
  public void testRun() {
    Thread dbWriter = new Thread(new DBWriter());
    dbWriter.start();
    assertTrue(dbWriter.isAlive());
  }
}