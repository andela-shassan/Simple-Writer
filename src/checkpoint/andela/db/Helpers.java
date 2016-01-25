package checkpoint.andela.db;

/**
 * Created by Semiu on 19/01/2016.
 */
public enum Helpers {
  DRIVER("com.mysql.jdbc.Driver"),
  DB_URL("jdbc:mysql://localhost:3306/"),
  USER("root"),
  PASS("Nobest@86"),
  DB_NAME("reactiondb"),
  TABLE_NAME("reactions");

  private String property;

  private Helpers(final String value) {
    this.property = value;
  }

  @Override
  public String toString(){
    return this.property;
  }
}
