package checkpoint.andela.parser;

/**
 * Created by Semiu on 19/01/2016.
 */
public enum Completed {
  INSTANCE;
  private boolean completed = false;

  public void setCompleted(boolean complete){
    this.completed = complete;
  }

  public boolean getComplete(){
    return completed;
  }
}