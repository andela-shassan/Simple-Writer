package checkpoint.andela.parser;

import java.util.Hashtable;


public class Record {  
  private Hashtable<String, String> record;
  
  
  public Record() {
    record = new Hashtable<>();
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
