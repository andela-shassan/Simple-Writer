package checkpoint.andela.parser;

import java.util.Hashtable;

/**
 * Created by Semiu on 19/01/2016.
 */
public class Record {
  private Hashtable<String, String> record;


  public Record() {
    record = new Hashtable<String, String>();
  }


  public void addPair(OrderedPair pair) {
    record.put(pair.getAttribute(), pair.getValue());
  }


  public int recordSize(){
    return record.size();
  }


  public Hashtable<String, String> getARecord(){
    return record;
  }


  public String getUniqueID(){
    return record.get("UNIQUE-ID");
  }
}
