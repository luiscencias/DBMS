/*
 * This file was created by Luis Davila of team 54 in Dr. Daugherity's 
 * CSCE 315 Class 
 * 
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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.ANTLRInputStream;


/**
 *
 * @author LuisDavila
 */
public class DBMSCommands {
    
    
    /**
     * 
     * This section is for variables the commands class needs to keep track of.
     * 
     *
     */
    public static Database view = new Database("view");
    public static DiskHandler diskHandler;

    
    
    public static void openCommand(String toOpen) {
        
        //Uses given database file name to attempt to open up and add the relation with the given name to the view from
        //Existing db files
		//Works Properly
		try {
			Relation toAdd = diskHandler.readDisk(toOpen);
			view.addRelation(toAdd);
		}
		catch(FileNotFoundException e){
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
    }
    
    public static void writeCommand(String toWrite) {
        //Writes the given relation to a file with a name equal to the relation name given
		//Works Properly
        
        //first check to see if relation exists in database
        //if it does, then print the relation
        if (view.getRelationIndex(toWrite) > -1){
            int indexToWrite = view.getRelationIndex(toWrite);
            try {
				diskHandler.writeDisk(view.relations.get(indexToWrite));
				}
			catch (FileNotFoundException e){
				System.err.println("FileNotFoundException: " + e.getMessage());
				}
        }
        else {
            System.out.println("Relation " + toWrite + " does not exist in database!");
        }
        //print appropriate message if relation does not exist
    }
    
    public static void closeCommand(String toDelete) {
        //Writes the specified relation to a file, then deletes it from the database
		//Works properly
        
        if (view.getRelationIndex(toDelete) > -1) {
            writeCommand(toDelete);
            view.delRelation(toDelete);
            
        }
        else {
            System.out.println("Relation " + toDelete + " does not exist in database!");
        }
        
    }
    
    public static void showCommand(String toShow) {
        //Prints specified relation onto terminal
        //Works properly
        //first check to see if relation exists in database
        //if it does, then print the relation
        if (view.getRelationIndex(toShow) > -1){
            int indexToPrint = view.getRelationIndex(toShow);
            view.relations.get(indexToPrint).testPrint();
        }
        else {
            System.out.println("Relation " + toShow + " does not exist in database!");
        }
        //if relation does not exist, print out message pointing out that relation does not exist    
        
        
    }
    
    public static void createCommand(String relationName, Attribute[] relationAtts, String[] primaryKeys) {
        //Creates a new relation with the name and parameters provided
        //Works properly
        //Check that parameters are acceptable
        if (relationAtts.length > 0 && primaryKeys.length > 0){
            Relation newRel = new Relation(relationName, relationAtts, primaryKeys);
            view.addRelation(newRel);
        }
        else {
            System.out.println("CreateCommandError: One or more input lists are empty");
        }
        //If parameters are incomplete, print a message accordingly
    }
    
    public static void updateSingleAttribute(String relationName, Attribute toUpdate, Literal toChange, String leftOperand, String operation, String RightOperation) {
        //Updates the given relation, with only 1 attribute to be set to a literal
       //Updates tuples that satisfy a condition given
	   
	   
	   
	   
	   /* Still needs to be implemented*/
	}
	
	
    
    public static void updateMultipleAttributes(String relationName, Attribute[] toUpdate, Literal[] toChange, String leftOperand, String operation, String rightOperand){
        //Updates the given relation, with a list of attributes to be set to their corresponding value in a list of literals
		
		/* Still needs to be implemented*/
    }
    public static void insertFromList(String relationName, Literal[] litList) {
        //Inserts into a given existing relation from a list of literals
        if (view.getRelationIndex(relationName)> -1){
            int relIndex = view.getRelationIndex(relationName);
            view.relations.get(relIndex).addRow(litList);
        }
        else {
            System.out.println("Relation " + relationName + " does not exist in database!");
        }
    }
    
    public static void insertFromRelation(String relationName, String otherRelation){
        //Inserts into a given existing relation from another existing relation    
        
        //first confirm that both relations exist within the database
        if (view.getRelationIndex(relationName)> -1 && view.getRelationIndex(otherRelation) > -1){
            
        //once confirmed 
            int relIndex = view.getRelationIndex(relationName);
            int otherRelIndex = view.getRelationIndex(otherRelation);
            int otherRelRows = view.relations.get(otherRelIndex).rows.size();
            for (int i = 0; i < otherRelRows; i++){
        //Iterate once for every row in relation whose values we are drawing from        
                view.relations.get(relIndex).addRow(view.relations.get(otherRelIndex).rows.get(i).toArray(new Literal[view.relations.get(otherRelIndex).rows.get(i).size()]));
        //This very long line of code simply adds a row to the relation we are inserting into
        //The row consists of a copy of the i'th row of the other relation (we need to convert from arraylist to array, hence the long line)
            }
               
        }
        else {
            System.out.println("one or more of the relations in this Insert command do not exist in database!");
        }
        //Print appropriate message if either of the relations does not exist
    }
    public static void deleteCommand(String relationName, String leftOp, String op, String RightOP) {
       //Deletes tuples from the relation given, taking out the tuples that satisfy the condition
       //given by the 3 strings
	   
	   /* Still needs to be implemented*/
    
    }
    
    public static void computeCondition(String relationName, String leftOp, String operator, String rightOp){
		//Will create a list of tuples 
		
		
		//Create temporary relation from relationName
		ArrayList<ArrayList<Literal>> validTuples;
		Relation tempRel = view.getRelation(relationName);
		int leftOpType = 0;
		int rightOpType = 0;
		//Check that both operands are valid, and assign them a type
		//Type 1 for Integer, Type 2 for String, Type 3 for attribute-name
		if(isInteger(leftOp) == true){
			leftOpType = 1;
		}
		else
			if (isLiteral(leftOp) == true){
				leftOpType = 2;
		}
		else {
			for (int i = 0; i < tempRel.orderedAttributes.size(); i++){
				if (leftOp.equals(tempRel.orderedAttributes.get(i).name)){
					leftOpType = 3;
					break;
				}
				if (i == (tempRel.orderedAttributes.size() - 1)){
					System.out.println("Invalid left operand! aborting comparison...");
					return;
				}
			}
			
		}	
		if(isInteger(rightOp) == true){
			rightOpType = 1;
		}
		else
			if (isLiteral(rightOp) == true){
				rightOpType = 2;
		}
		else {
			for (int i = 0; i < tempRel.orderedAttributes.size(); i++){
				if (rightOp.equals(tempRel.orderedAttributes.get(i).name)){
					rightOpType = 3;
					break;
				}
				if (i == (tempRel.orderedAttributes.size() - 1)){
					System.out.println("Invalid right operand! aborting comparison...");
					return;
				}
			}
			
		}	
			
		
		for (int j = 0; j < tempRel.rows.size(); j++){
			
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	public static boolean isInteger(String toCheck){
		try {
			int doesWork = Integer.parseInt(toCheck.trim());
			return true;
		}
		catch (NumberFormatException nfe){
			
			
		}
		return false;
	}
	
	public static boolean isLiteral(String toCheck){
		if (toCheck.charAt(0) == '"' && toCheck.charAt(toCheck.length()-1) == '"')
		{
			return true;
		}
		return false;
	}
	public static boolean singleComparison (String leftOp, String operator, String rightOp, int leftOpType, int rightOpType, ArrayList<Literal> tupleToCheck){
		//Perform a comparison on an individual tuple
		if(leftOpType != 3 && rightOpType !=3 && leftOpType != rightOpType){
			return false;
		}
		int operationType = 0;
		/* Integer to determine what operation type it is, which correspond to the following:
			1: Integer x Integer
			2: Integer x AttributeName
			3: AttributeName x Integer
			4: String x String
			5: String x AttributeName
			6: Attributename x String
			7: Attributename x Attributename
			If operationType > 3, the "<", ">", "<=", ">=" operators will return false
		*/
		switch (leftOpType){
			case 1:
			//if it is an integer
				if(rightOpType == 1) {operationType = 1;}
				if(rightOpType == 3) {operationType = 2;}
			break;
			case 2:
				if(rightOpType == 2) {operationType = 4;}
				if(rightOpType == 3) {operationType = 5;}
			//if it is a string literal
			break;
			case 3:
				if(rightOpType == 1) {operationType = 3;}
				if(rightOpType == 2) {operationType = 6;}
				if(rightOpType == 3) {operationType = 7;}
			//if it is an attribute name
			
		}
		
		
		switch (operator){
			case "==":
					
				break;
			case "!=":	
			
				break;
				
			case "<":
				
				break;
			
			case ">":
				break;
				
			case "<=":
				break;
				
			case ">=":
				break;
			
			
			
			
			
		}
		return false;
	}
    
	
    
        public static void main(String[] args){
        
        Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
        String[] samplePrimaries = {"big", "large"};
        Relation simpleRel = new Relation("One", samples, samplePrimaries );
		Relation insertTest = new Relation("gimme", samples, samplePrimaries);
		Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
        Literal[] sampleTwo = {new Literal(new Attribute("big",10),"tree"), new Literal(new Attribute("large",10),"plant")};
        Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
        Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
        Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")};
		simpleRel.addRow(sampleOne);
		simpleRel.addRow(sampleTwo);
        simpleRel.addRow(sampleThree);
        simpleRel.addRow(sampleFour);
        simpleRel.addRow(sampleFive);
        view.addRelation(simpleRel);
        view.addRelation(insertTest);
        showCommand("bad");
        showCommand("One");
       
        createCommand("two", samples, samplePrimaries);
        insertFromList("two", sampleOne);
        insertFromList("two", sampleTwo);
        insertFromList("two", sampleThree);
        insertFromList("two", sampleFour);
        insertFromList("two", sampleFive);
        
        showCommand("two");
        
        insertFromRelation("gimme","One");
        
        showCommand("gimme");
		
		writeCommand("gimme");
		
		closeCommand("two");
		
		
		
		openCommand("one");
		openCommand("two");
		
		showCommand("two");
		
		System.out.println("Testing isInt Function: " + isInteger("bob"));
		System.out.println("Testing isInt Function: " + isInteger("12"));
		System.out.println("Testing isInt Function: " + isLiteral("\"bob\""));
		System.out.println("Testing isInt Function: " + isLiteral("bob"));
		
		computeCondition("two", "\"bob\"", "==", "11");
		computeCondition("two", "\"bob\"", "==", "big");
    }
}
