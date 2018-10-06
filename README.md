# Project-2
Project 2 for CSCE 315

Part 1: Design Documentation
- see wiki log

Part 2: Parser 
The java.parser class takes the input from the input.txt file and prints the results to output.txt

Part 3: DBMS Core

To compile use:
javac -cp "antlr-4.7.1-complete.jar" *.java

To run:
java -cp ".:antlr-4.7.1-complete.jar" DBMSEngine

To test the queries there is a toggle feature in the main of DBMSEngine.java. Simply change the test variables to 1 to test each query. To test the commands simply change the
testCommands variable to 1 to test all commands at once. 
