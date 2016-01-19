package checkpoint.andela.db;

/**
 * Created by Semiu on 19/01/2016.
 */
public class Helpers {
  static final String DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/";

  static final String USER = "root";
  static final String PASS = "Nobest@86";


  public static String[] attribute = {
      "UNIQUE-ID",
      "TYPES",
      "COMMON-NAME",
      "ATOM-MAPPINGS",
      "CANNOT-BALANCE?",
      "CITATIONS",
      "COMMENT",
      "COMMENT-INTERNAL",
      "CREDITS",
      "DATA-SOURCE",
      "DBLINKS",
      "DELTAG0",
      "DOCUMENTATION",
      "EC-NUMBER",
      "ENZYMATIC-REACTION",
      "ENZYMES-NOT-USED",
      "EQUILIBRIUM-CONSTANT",
      "HIDE-SLOT?",
      "IN-PATHWAY",
      "INSTANCE-NAME-TEMPLATE",
      "LEFT",
      "MEMBER-SORT-FN",
      "ORPHAN?",
      "PATHOLOGIC-NAME-MATCHER-EVIDENCE",
      "PATHOLOGIC-PWY-EVIDENCE",
      "PHYSIOLOGICALLY-RELEVANT?",
      "PREDECESSORS",
      "PRIMARIES",
      "REACTION-DIRECTION",
      "REACTION-LIST",
      "REGULATED-BY",
      "REQUIREMENTS",
      "RIGHT",
      "RXN-LOCATIONS",
      "SIGNAL",
      "SPECIES",
      "SPONTANEOUS?",
      "STD-REDUCTION-POTENTIAL",
      "SYNONYMS",
      "SYSTEMATIC-NAME",
      "TEMPLATE-FILE",
      "^COEFFICIENT",
      "^COMPARTMENT"
  };
}
