package checkpoint.andela.parser;

import java.util.Hashtable;

/**
 * Created by Semiu on 19/01/2016.
 */
public class Reactant {
  private Hashtable<String, String> reactant;


  public Reactant() {
    reactant = new Hashtable<String, String>();
  }

  public void addPair(OrderedPair pair) {
    reactant.put(pair.getAttribute(), pair.getValue());
  }

  public int reactantSize(){
    return reactant.size();
  }

  public Hashtable<String, String> getARecord(){
    return reactant;
  }

  public String getUniqueID(){
    return reactant.get("UNIQUE-ID");
  }
}
