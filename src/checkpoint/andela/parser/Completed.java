package checkpoint.andela.parser;

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
