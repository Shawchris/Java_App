My Java Application
A simple Java application with a GUI interface. This project serves as a learning exercise for Java development, version control with GitHub, and application packaging.
Features

Basic GUI interface using Java Swing
Input text processing
Simple output display
Packaged as a standalone application

Prerequisites

Java Development Kit (JDK) 11 or higher
Gradle build tool
VS Code with Java extensions

Building the Application
To build the application, run:
./gradlew build
This will create a JAR file in the build/libs directory and a distribution ZIP in the build/distributions directory.
Running the Application
To run the application directly:
./gradlew run
To run the packaged JAR:
java -jar build/libs/MyJavaApp-1.0-SNAPSHOT.jar
Project Structure

src/main/java - Java source files
src/test/java - Test source files
build.gradle - Gradle build configuration
.gitignore - Git ignore file

Development Workflow

Make changes to the code
Commit changes to Git
Push to GitHub
Build and test the application

Future Enhancements

Add more functionality
Improve the UI design
Add configuration options
Package as a native application

License
This project is licensed under the MIT License - see the LICENSE file for details.