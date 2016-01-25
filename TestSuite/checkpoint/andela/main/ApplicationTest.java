package checkpoint.andela.main;

import org.junit.Test;

public class ApplicationTest {


  /**
   * This test pass if it create a log file with the name 'logFile.txt' and the file contains the activities of the threads.
   * @throws Exception
   */
  @Test
  public void testApplication() throws Exception {
    Application app = new Application();
    app.setLogFilePath("C:\\Users\\Semiu\\Desktop\\Files\\logFile3.txt");
    app.setReactantFilePath("C:\\Users\\Semiu\\Desktop\\Files\\reactions.dat");
    app.process();
  }
}
