package checkpoint.andela.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

/**
 * Created by Semiu on 19/01/2016.
 */

public class FileParserTest {
  private BlockingQueue<Record> testRecord = new ArrayBlockingQueue<Record>(10);
  private String filePath;

  @Before
  public void setUp() throws Exception {
    filePath = "C:\\Users\\Semiu\\Documents\\myWorkspace\\Simple-Writer\\Files\\reactions.dat";
  }

  @Test
  public void testFileParser() throws Exception {
    Thread fileParserThread = new Thread(new FileParser(testRecord, filePath));
    fileParserThread.start();

    assertTrue(fileParserThread.isAlive());
  }


  @Test
  public void testIsComment(){
    FileParser fp = new FileParser();
    String string1 = "#    PATHOLOGIC-NAME-MATCHER-EVIDENCE";
    String string2 = "UNIQUE-ID - RXN-8748";
    String string3 = "//";
    assertTrue(fp.isComment(string1));
    assertFalse(fp.isComment(string2));
    assertFalse(fp.isComment(string3));
  }


  @Test
  public void testIsDelimiter(){
    FileParser fp = new FileParser();
    String string1 = "#    PATHOLOGIC-NAME-MATCHER-EVIDENCE";
    String string2 = "UNIQUE-ID - RXN-8748";
    String string3 = "//";
    assertFalse(fp.isDelimiter(string1));
    assertFalse(fp.isDelimiter(string2));
    assertTrue(fp.isDelimiter(string3));
  }


  @Test
  public void testProcessLine() {
    FileParser fp = new FileParser();
    Record record = new Record();
    String line1 = "ENZYMATIC-REACTION - ENZRXNMT2-1088";
    String line2 = "LEFT - CPD-8978";
    OrderedPair pair1 = new OrderedPair();
    OrderedPair pair2 = new OrderedPair();

    fp.processLine(record, line2, pair2);
    assertEquals(pair2.getAttribute(), "LEFT");
    assertEquals(pair2.getValue(), "CPD-8978");
    assertEquals(record.recordSize(), 1);

    fp.processLine(record, line1, pair1);
    assertEquals(pair1.getAttribute(), "ENZYMATIC-REACTION");
    assertEquals(pair1.getValue(), "ENZRXNMT2-1088");
    assertEquals(record.recordSize(), 2);

  }

}
