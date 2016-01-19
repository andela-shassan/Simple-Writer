package checkpoint.andela.parser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Semiu on 19/01/2016.
 */

public class OrderedPairTest {

  String first = "UNIQUE-ID - PHENYLALANINE--TRNA-LIGASE-RXN";
  String second = "TYPES - tRNA-Charging-Reactions";
  String third = "ATOM-MAPPINGS - :UNBALANCED";
  String fourth = "CREDITS - SRI";
  String fifth = "LEFT - PROTON";



  @Test
  public void testSetAttribute() {
    OrderedPair pair = new OrderedPair();
    assertEquals(pair.getAttribute(), "");
    pair.setAttribute("TYPES");
    assertEquals(pair.getAttribute(), "TYPES");
  }


  @Test
  public void testGetValue() {
    OrderedPair pair = new OrderedPair();
    assertEquals(pair.getValue(), "");
    pair.setValue("tRNA-Charging-Reactions");
    assertEquals(pair.getValue(), "tRNA-Charging-Reactions");
  }


  @Test
  public void testGetPair() {
    OrderedPair pair = new OrderedPair();
    pair.setAttribute("TYPES");
    pair.setValue("tRNA-Charging-Reactions");
    assertEquals(pair.getPair(), "TYPES tRNA-Charging-Reactions");
  }

}
