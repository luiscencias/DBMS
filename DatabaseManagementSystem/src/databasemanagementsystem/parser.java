/*
CSCE 315 Project 2 Parser
Created by Nic Kristiansson of Group 54
Takes in input line by line, and determines if its valid.
*/


import java.io.*;
import java.util.*;

import java.lang.String;
import java.lang.Character;
import static java.lang.System.out;

public class parser{

        public static void main(String args[]) throws IOException {
                String ANSI_RESET = "\u001B[0m";
                String ANSI_GREEN = "\u001B[32m";
                String ANSI_RED = "\u001B[31m";
                Scanner scanner = new Scanner(System.in);
                System.out.println("Parsing...");
                String fLine;
                int count = 0;
                while(scanner.hasNext()){
                        fLine = scanner.nextLine();
                        if(fLine.trim().length() > 0){
                                System.out.print("Line ");
                                System.out.print(count++);
                                System.out.print(" is ");
                                boolean parse = true; /// Process input here
                                if(parse){
                                        System.out.print(ANSI_GREEN);
                                        System.out.print("VALID");
                                }
                                else{
                                        System.out.print(ANSI_RED);
                                        System.out.print("INVALID");
                                }
                                System.out.println(ANSI_RESET);
                        }
                }

        }

}