package checkpoint.andela.parser;

/**
 * Created by Semiu on 19/01/2016.
 */
public enum FileParserObserver {
  INSTANCE;
  private boolean fileParserStatus = true;

  public void setFileParserStatus(boolean status){
    fileParserStatus = status;
  }

  public boolean getFileParserStatus(){
    return fileParserStatus;
  }
}