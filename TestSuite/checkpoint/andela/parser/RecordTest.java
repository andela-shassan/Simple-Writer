package checkpoint.andela.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Semiu on 19/01/2016.
 */

public class RecordTest {
  Record record = new Record();

  @Test
  public void testAddPair() {
    OrderedPair pair = new OrderedPair();
    pair.setAttribute("TYPES");
    pair.setValue("tRNA-Charging-Reactions");
    record.addPair(pair);
    assertEquals(record.recordSize(), 1);
  }

  @Test
  public void testRecordSize() {
    OrderedPair pair2 = new OrderedPair();
    pair2.setAttribute("TYPES-2");
    pair2.setValue("tRNA-Charging-Reactions-2");
    record.addPair(pair2);
    assertEquals(record.recordSize(), 1);

    OrderedPair pair3 = new OrderedPair();
    pair3.setAttribute("TYPES-3");
    pair3.setValue("tRNA-Charging-Reactions-3");
    record.addPair(pair3);
    assertEquals(record.recordSize(), 2);
  }


  @Test
  public void testGetUniqueID() {
    OrderedPair pair2 = new OrderedPair();
    pair2.setAttribute("TYPES-2");
    pair2.setValue("tRNA-Charging-Reactions-2");
    record.addPair(pair2);

    OrderedPair pair3 = new OrderedPair();
    pair3.setAttribute("UNIQUE-ID");
    pair3.setValue("PHENYLALANINE--TRNA-LIGASE-RXN");
    record.addPair(pair3);

    OrderedPair pair4 = new OrderedPair();
    pair4.setAttribute("TYPES-4");
    pair2.setValue("tRNA-Charging-Reactions-4");
    record.addPair(pair4);

    OrderedPair pair5 = new OrderedPair();
    pair5.setAttribute("TYPES-5");
    pair5.setValue("tRNA-Charging-Reactions-5");
    record.addPair(pair3);

    assertEquals(record.getUniqueID(), "PHENYLALANINE--TRNA-LIGASE-RXN");
  }

}
