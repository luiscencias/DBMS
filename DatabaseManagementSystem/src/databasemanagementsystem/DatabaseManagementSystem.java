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

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.ANTLRInputStream;

/**
 *
 * @author murtazahakimi
 */
public class DatabaseManagementSystem {
    
    /* To compile and run: 
        javac -cp "antlr-4.7.1-complete.jar" *.java
        java -cp ".:antlr-4.7.1-complete.jar"
    */
    public static void main(String args[]) throws IOException {
               
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type in your command/query");
            String fLine;
             
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
             
            int count = 0;
            while(scanner.hasNext()){
                    fLine = scanner.nextLine();
                    if(fLine.trim().length() > 0){
                             
                            /* Testing purposes: 
                            System.out.print(count++); */
                             
                            String validity = "";
                            boolean parse = isValidLine(fLine); /// Process input here
                            if(parse){
                                /* walker code */ 
                                validity = "VALID";
                                String DBMSGrammarProgram = fLine;
                                 
                                // Get our lexer
                                DBMSGrammarLexer lexer = new DBMSGrammarLexer(new ANTLRInputStream(DBMSGrammarProgram));
                              
                                // Get a list of matched tokens
                                CommonTokenStream tokens = new CommonTokenStream(lexer);
                              
                                // Pass the tokens to the parser
                                DBMSGrammarParser parser = new DBMSGrammarParser(tokens);
                              
                                // Specify our entry point
                                DBMSGrammarParser.ProgramContext DBMSGrammarProgramContext = parser.program();
                              
                                // Walk it and attach our listener
                                ParseTreeWalker walker = new ParseTreeWalker();
                                DBMSListener listener = new DBMSListener();
                                walker.walk(listener, DBMSGrammarProgramContext);
                            }
                            else{
                                validity = "INVALID";
                                System.out.println("Line is INVALID");
                            }
                                            
                            // writer.println("Line " + count + " is " + validity);
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
            
            /* get rid of after testing */
           // System.out.println(parseTree.getText());
           // System.out.println("\n" + parseTree.toStringTree(parser) + "\n");
            
           
            return numErrors == 0;
        }
    
}
