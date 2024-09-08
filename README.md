# Disaster Relief System
Before compiling/running the application, ensure that you have the following:
1. Java Development Kit (JDK) installed on your system.
2. The required JAR files in the `lib` folder within the project directory.
(hamcrest, junit, and postgresql)
Compiling the Application
To compile the source code, follow these steps:
1. Open a terminal or command prompt.
2. Navigate to the project directory. (do not enter the edu folder yet)
3. Compile the source files using the following command:
javac -cp lib/\* edu/ucalgary/oop/\*.java
This command compiles all Java files (`*.java`) located in the `edu/ucalgary/oop` directory, with
the classpath (`-cp`) set to include all JAR files (`*`) within the `lib` directory.
Running the Application
1. After compiling the source code, execute the application
2. Use the following command:
java -cp "lib\postgresql-42.7.3.jar;." edu.ucalgary.oop.DisasterVictimInterface or java -cp
"lib\postgresql-42.7.3.jar;." edu.ucalgary.oop.InquirerInterface
