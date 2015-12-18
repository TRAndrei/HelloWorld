# HelloWorld

These instructions require Maven.

How to compile:
    - run `mvn clean install` in the main directory
    - you should end up with a /target subfolder which will contain a jar file named xsizero-jar-with-dependencies

How to run with Maven:
    - run `mvn exec:java` in the main directory

How to run standalone
    - navigate to the /target subfolder and run `java -jar xsizero-jar-with-dependencies.jar`

After the application successfully starts you should see "Coder Dojo sample app" in the console.

How to debug:
    - run `mvnDebug exec:java`
    - create a debug environment and connect to port 8000
    - the application should start as soon as you connect with the debugger
