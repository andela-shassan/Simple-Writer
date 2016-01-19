package checkpoint.andela.main;

import org.junit.Test;

public class ApplicationTest {


  /**
   * This test pass if it create a log file with the name 'testLog.txt' and the file contains exactly 20 lines.
   * @throws Exception
   */
  @Test
  public void testApplication() throws Exception {
    Application app = new Application();
    app.setLogFilePath("C:\\Users\\Semiu\\Desktop\\Files\\intelliJ.txt");
    app.setReactantFilePath("C:\\Users\\Semiu\\Desktop\\Files\\reactions.dat");
    app.process();
  }
}
