package checkpoint.andela.parser;

/**
 * Created by Semiu on 19/01/2016.
 */
public class OrderedPair {
  private String attribute = "";
  private String value = "";



  public OrderedPair() {}

  public OrderedPair(String attribute, String value) {
    setAttribute(attribute);
    setValue(value);
  }

  public String getAttribute() {
    return attribute.trim();
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public String getValue() {
    return value.trim();
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getPair(){
    return attribute + " " + value;
  }
}
