# Simple-Writer

## About
Simple-Writer is a multithreaded application designed to parse a `reactant.dat` file. The file contains records of different reactants. Each reactant has different attribute-value pairs. The end result of this application is that it reads the document, parses it into buffer and saves it to the database while generating the logfile that records the activity of each thread at the same time.  

### Classes and features of the application.  

#### Application Class
This class serves as the entry point to the application. It implements all the other Runnable classes as a thread and controls, starts and stops the activity of the threads.  
It has a single method  

```java  
private void process(){  
......  
}  
```  
This is the method that starts the whole application and set it on production. It also manages the termination of the threads when all the threads are done with their tasks.  

### FileParser Class  
This class handles the parsing of the file to buffer, a temporary storage. It implements the Runnable interface. It has methods like ```java writeToBuffer() ``` which handles the reading of the text into buffer.  
Also, method ```java processLine() ``` takes the line read-in by the reader process it into OrderedPair and add it to the Record.  

### OrderedPair and Record classes  
These are the datatypes that were used in the application. The OrderedPair class separates the lines into attribute value pair while the  Record class separates the documents into reactants.  

### Singleton Class Complete  
This is a singleton class that serves just one purpose which is avoiding an infinite loops. It tells the `dbWriter()` when the `FileParser` is done reading the file.  

### DBManager and DBWriter classes  
These two classes handle the operations related to the database. Combined, they have methods to create a database, database table and also insert to insert into the database.  
Helper class serves as a data keeper for the database related process. It holds the credentials for connecting to the database.  

### LogManager and LogBuffer classes  
The LogManager class handles the creation and formatting of the log message into the appropriate style. It has methods such as `formatDate()` which formats the current date into specified string format. The `destination()` and `getAction()` methods that also helps to format the log text. The formatted text is saved into `LogBuffer` which serves as the temporary storage for the log message.  

### LogWriter  
This class also implements Runnable. It is saddled with the responsibility of writing the log message into a file.
It takes the text from the `LogBuffer` and write it into the specified file. If the file is not available in the specified location, it creates a new file with the given name.  


## Application Requirements and Running Instruction  
To run this application, `java sdk` must be installed on the system together with `MySql` database.  
Also, the credentials `(user & password)` located in the helper class must be changed accordingly in other for the app to access the database.  

**Application** is the entry point to this program. It takes two arguments: the reactant file path and the path for the log file. 
```java
public Application(String reactantFilePath, String logFilePath) throws Exception {
    setReactantFilePath(reactantFilePath);
    setLogFilePath(logFilePath);
  }
```  

User also has the options to instantiate the application with no argument but must then use `setter()` to set the path to the `reactant` and `log` files as illustrated below.  

```java
Application app = new Application();
    app.setLogFilePath("path to log file");
    app.setReactantFilePath("path to reactant file");
```  

By calling the process method `app.process();` on the app, it automatically saves the given data to the database and generates the log file at the same time.
