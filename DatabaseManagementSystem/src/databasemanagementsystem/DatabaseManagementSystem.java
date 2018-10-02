/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;

import java.lang.String;
import java.lang.Character;
import static java.lang.System.out;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author murtazahakimi
 */
public class DatabaseManagementSystem {

    /**
     * @param args the command line arguments
     */
    
    /* To compile and run: 
        javac -cp "antlr-4.7.1-complete.jar" *.java
        java -cp ".:antlr-4.7.1-complete.jar"
    */
    public static void main(String args[]) throws IOException {
               String ANSI_RESET = "\u001B[0m";
               String ANSI_GREEN = "\u001B[32m";
               String ANSI_RED = "\u001B[31m";
               
               Scanner scanner = new Scanner(System.in);
               System.out.println("Parsing...");
               String fLine;
               
               PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
               
               int count = 0;
               while(scanner.hasNext()){
                       fLine = scanner.nextLine();
                       if(fLine.trim().length() > 0){
                               System.out.print(count++);
                               String validity = "";
                               boolean parse = isValidLine(fLine); /// Process input here
                               if(parse){
                                   validity = "VALID";
                               }
                               else{
                                   validity = "INVALID";
                               }
                                                                 
                               writer.println("Line " + count + " is " + validity);
                       }
               }
               writer.close();

       }
        
        public static boolean isValidLine(String line) {
            CharStream charStream = CharStreams.fromString(line);
            DBMSGrammarLexer lexer = new DBMSGrammarLexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
            DBMSGrammarParser parser = new DBMSGrammarParser(commonTokenStream);
            
            ParseTree parseTree = parser.program();
            int numErrors = parser.getNumberOfSyntaxErrors();
            
            System.out.println(parseTree.getText());
            System.out.println("\n" + parseTree.toStringTree(parser) + "\n");
            
           
            return numErrors == 0;
        }
    
}
