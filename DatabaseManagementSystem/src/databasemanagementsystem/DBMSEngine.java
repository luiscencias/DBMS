/*
 * This file was created by Murtaza Hakimi of team 54 in Dr. Daugherity's 
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
 * @author Murtaza Hakimi
 */
 public class DBMSEngine {
   
   public static Database view = new Database("view");
   public static DiskHandler diskHandler;
   
   public static void selectionQuery(String relationName, String lOperand, String rOperand, String operator) {
     
   }
   
   // Return the number of pairs with equal 
   // values. 
   public static boolean areDuplicates(ArrayList<String> arr, int n) 
   { 
       int ans = 0; 
       
       // for each index i and j 
       for (int i = 0; i < n; i++) {
         for (int j = i+1; j < n; j++) {
           // finding the index with same 
           // value but different index. 
           if (arr.get(i) == arr.get(j)) 
               ans++; 
         }   
       }
       
       if(ans > 0) {
         return true;
       } else {
         return false;
       }    
   } 
   
   public static void projectionQuery(String relationName, ArrayList<String> attributeNames) {
     // project the attributes pass and their data to a new relation
     
     // check if relation exist in database
     if (view.getRelationIndex(relationName) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
       return;
     }
     
     Relation projFrom = view.getRelation(relationName);
     
     // check to make sure there are the same number of attributes being renamed
     if(attributeNames.size() > projFrom.orderedAttributes.size()) {
       System.out.println("InvalidDBException: Projecting more attributes then exist in relation");
       return;
     }
     
     // check to make sure there are no duplicates 
     if(areDuplicates(attributeNames, attributeNames.size())) {
       System.out.println("InvalidDBException: Attribute names cannot be duplicated in project statement");
       return;
     }
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     // add only the matching attributes to the list
     for(int i=0; i < attributeNames.size(); i++) {
       for(int j=0; j < projFrom.orderedAttributes.size(); j++) {
         if(attributeNames.get(i).equals(projFrom.orderedAttributes.get(j).name)) {
           attributes.add(projFrom.orderedAttributes.get(j));
         }
       }
     }
     
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     Relation projTo = new Relation("proj", attributeArray);
     
     // copy data over to the projected relation
     for(int i=0; i < projFrom.rows.size(); i++) {
       ArrayList<Literal> literalsTemp = new ArrayList();
       
       for(int j=0; j < attributes.size(); j++) {
         for(int k=0; k < projFrom.rows.get(j).size(); k++) {
           if(projFrom.rows.get(i).get(k).attribute.name.equals(attributes.get(j).name)) {
             Literal temp = new Literal(attributes.get(j), projFrom.rows.get(i).get(k).literal);
             literalsTemp.add(temp);
           }
         }
       }
       projTo.addRow(literalsTemp.toArray(new Literal[literalsTemp.size()]));
       literalsTemp = new ArrayList();
     }
     
     /* testing code: delete later */
     view.addRelation(projTo);
     System.out.println("Projection Successful! \n");
     
   }
   
   public static void renamingQuery(String relationBeingRenamed, String attributesFromRelation, ArrayList<String> attributeNames) {
     // rename attribute names from a relation
     
     // check if relation exist in database
     if (view.getRelationIndex(attributesFromRelation) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
       return;
     }
     
     // check if target exists but is different from source
     if (view.getRelationIndex(attributesFromRelation) != -1 && view.getRelationIndex(relationBeingRenamed) != -1 && !(relationBeingRenamed.equals(attributesFromRelation))) {
       System.out.println("InvalidDBException: Target relation can't be different from source if both exist");
       return;
     }
     
     Relation relationFrom = view.getRelation(attributesFromRelation);
     
     // check to make sure there are the same number of attributes being renamed
     if(attributeNames.size() > relationFrom.orderedAttributes.size()) {
       System.out.println("InvalidDBException: Renaming more attributes then exist in relation");
       return;
     }
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     /* 3. The attribute names always come from the right operand */
     for(int i=0; i < relationFrom.orderedAttributes.size(); i++) {
       attributes.add(relationFrom.orderedAttributes.get(i));
     }
     
     // check if renaming and creating new relation, or renaming original relationName
     if(view.getRelationIndex(relationBeingRenamed) == -1) {
       Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
       Relation relationTo = new Relation(relationBeingRenamed, attributeArray);
       
       // also need to copy the data into the new relation
       for(int i=0; i < relationFrom.rows.size(); i++) {
         relationTo.addRow(relationFrom.rows.get(i).toArray(new Literal[relationFrom.rows.get(i).size()]));
       }
       
       // rename the attributes
       for(int i=0; i < attributeNames.size(); i++) {
         Attribute temp = new Attribute(attributeNames.get(i), attributes.get(i).domain);
         attributes.set(i, temp);
       }
       relationTo.orderedAttributes = attributes;
       
       // rename teh attributes in each literal
       for(int i=0; i < relationTo.rows.size(); i++) {
         ArrayList<Literal> literalsTemp = new ArrayList();
         
         for(int j=0; j < relationTo.rows.get(i).size(); j++) {
           Literal temp = new Literal(attributes.get(j), relationFrom.rows.get(i).get(j).literal);
           literalsTemp.add(temp);
         }
         relationTo.rows.set(i, literalsTemp);
         literalsTemp = new ArrayList();
       }
       
       // add relation to database
       view.addRelation(relationTo);
       System.out.println("Renaming Successful! \n");
     } else {
       //rename attributes of existing relation
       for(int i=0; i < attributeNames.size(); i++) {
         Attribute temp = new Attribute(attributeNames.get(i), relationFrom.orderedAttributes.get(i).domain);
         relationFrom.orderedAttributes.set(i, temp);
       }
       
       // rename the attributes variable
       for(int i=0; i < attributeNames.size(); i++) {
         Attribute temp = new Attribute(attributeNames.get(i), attributes.get(i).domain);
         attributes.set(i, temp);
       }
       
       // rename the primary keys
       ArrayList<Attribute> newPrimaryKeys = attributes;
       relationFrom.primaryKey = newPrimaryKeys;
       
       // rename teh attributes in each literal
       for(int i=0; i < relationFrom.rows.size(); i++) {
         ArrayList<Literal> literalsTemp = new ArrayList();
         
         for(int j=0; j < relationFrom.rows.get(i).size(); j++) {
           Literal temp = new Literal(attributes.get(j), relationFrom.rows.get(i).get(j).literal);
           literalsTemp.add(temp);
         }
         relationFrom.rows.set(i, literalsTemp);
         literalsTemp = new ArrayList();
       }
       System.out.println("Renaming Successful! \n");
     }
   }
   
   public static boolean unionCompatible(Relation lRelation, Relation rRelation) {
     // check needed for union and difference
     
     /* 1. Union operations can only be performed if A and B have the 
     * same number of columns. */
     if(!(lRelation.orderedAttributes.size() == rRelation.orderedAttributes.size())) {
       return false;
     }
     
     /* 2. Union operations can only be performed if each column in A has the 
     * same type to its corresponding column in B. */
     for(int i = 0; i < lRelation.orderedAttributes.size(); i++) {
       if(!(lRelation.orderedAttributes.get(i).domain == rRelation.orderedAttributes.get(i).domain)) {
         return false;
       }
       
       if(!(lRelation.orderedAttributes.get(i).domain > 0 && rRelation.orderedAttributes.get(i).domain > 0)) {
         return false;
       }
     }
     
     return true;
   }
   
   public static void unionQuery(String lRelationName, String rRelationName) {
     // combines all the rows in two relations 
     
     // check if relations exist in database
     if (view.getRelationIndex(lRelationName) == -1 || view.getRelationIndex(rRelationName) == -1) {
       System.out.println("InvalidDBException: One or more Relations Does not Exist");
       return;
     }
     
     Relation lRelation = view.getRelation(lRelationName);
     Relation rRelation = view.getRelation(rRelationName);
     
     // check compatibility 
     boolean areCompatible = unionCompatible(lRelation, rRelation);
     
     if(!areCompatible) {
       System.out.println("InvalidDBException: Relations are not Compatible");
       return;
     }
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     /* 3. The attribute names always come from the left operand A. */
     for(int i=0; i < lRelation.orderedAttributes.size(); i++) {
       attributes.add(lRelation.orderedAttributes.get(i));
     }
     
     /* 4. The VARCHAR max-lengths would then come from the larger max-length 
     * from the corresponding columns. */
     for(int i=0; i < attributes.size(); i++) {
       if(lRelation.orderedAttributes.get(i).domain < rRelation.orderedAttributes.get(i).domain) {
         Attribute tempAttribute = new Attribute(lRelation.orderedAttributes.get(i).name, rRelation.orderedAttributes.get(i).domain);
         attributes.set(i, tempAttribute);
       }
     }
     
     /* create new relation (what to name it? what about primary keys?) */
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     Relation unionRelation = new Relation("union", attributeArray);
     
     int totalNumRows = lRelation.rows.size() + rRelation.rows.size();
     
     for(int i=0; i < lRelation.rows.size(); i++) {
       unionRelation.addRow(lRelation.rows.get(i).toArray(new Literal[lRelation.rows.get(i).size()]));
     }
     
     for(int i=0; i < rRelation.rows.size(); i++) {
       unionRelation.addRow(rRelation.rows.get(i).toArray(new Literal[rRelation.rows.get(i).size()]));
     }
     
     /* testing code: delete later */
     // TODO: check for existing union and delete it before adding new one
     view.addRelation(unionRelation);
     System.out.println("Union Successful! \n");
     
   }
   
   public static boolean areRowsEqual(ArrayList<Literal> rowOne, ArrayList<Literal> rowTwo) {
 		if(rowOne.size() != rowTwo.size()) {
 			return false;
 		}
 		
 		for(int k = 0; k < rowOne.size(); k++){
 			if(!(rowOne.get(k).literal.equals(rowTwo.get(k).literal))){
 				return false;
 			}
 		}
 		
 		return true;
   }
   
   public static void differenceQuery(String lRelationName, String rRelationName) {
     // combines the unique rows between the two relations
     
     // check if relations exist in database
     if (view.getRelationIndex(lRelationName) == -1 || view.getRelationIndex(rRelationName) == -1) {
       System.out.println("InvalidDBException: One or more Relations Does not Exist");
       return;
     }
     
     Relation lRelation = view.getRelation(lRelationName);
     Relation rRelation = view.getRelation(rRelationName);
     
     // check compatibility 
     boolean areCompatible = unionCompatible(lRelation, rRelation);
     
     if(!areCompatible) {
       System.out.println("InvalidDBException: Relations are not Compatible");
       return;
     }
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     /* 3. The attribute names always come from the left operand A. */
     for(int i=0; i < lRelation.orderedAttributes.size(); i++) {
       attributes.add(lRelation.orderedAttributes.get(i));
     }
     
     /* 4. The VARCHAR max-lengths would then come from the larger max-length 
     * from the corresponding columns. */
     for(int i=0; i < attributes.size(); i++) {
       if(lRelation.orderedAttributes.get(i).domain < rRelation.orderedAttributes.get(i).domain) {
         Attribute tempAttribute = new Attribute(lRelation.orderedAttributes.get(i).name, rRelation.orderedAttributes.get(i).domain);
         attributes.set(i, tempAttribute);
       }
     }
     
     /* create new relation (what to name it? what about primary keys?) */
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     Relation diffRelation = new Relation("diff", attributeArray);
     
     int count = 0;
     
     for(int i=0; i < lRelation.rows.size(); i++) {
       for(int j=0; j < rRelation.rows.size(); j++) {
         if(areRowsEqual(lRelation.rows.get(i), rRelation.rows.get(j))) {
           count++;
         }
       }
       if(count == 0) {
         diffRelation.addRow(lRelation.rows.get(i).toArray(new Literal[lRelation.rows.get(i).size()]));
       }
       count = 0;
     }
     
     for(int i=0; i < rRelation.rows.size(); i++) {
       for(int j=0; j < lRelation.rows.size(); j++) {
         if(areRowsEqual(rRelation.rows.get(i), lRelation.rows.get(j))) {
           count++;
         }
       }
       if(count == 0) {
         diffRelation.addRow(rRelation.rows.get(i).toArray(new Literal[rRelation.rows.get(i).size()]));
       }
       count = 0;
     }
     
     /* testing code: delete later */
     view.addRelation(diffRelation);
     System.out.println("Difference Successful! \n");
     
   }
   
   public static void productQuery(String lRelationName, String rRelationName) {
     // cartesion product of two Relations
     
     // check if relations exist in database
     if (view.getRelationIndex(lRelationName) == -1 || view.getRelationIndex(rRelationName) == -1) {
       System.out.println("InvalidDBException: One or more Relations Does not Exist");
       return;
     }
     
     Relation lRelation = view.getRelation(lRelationName);
     Relation rRelation = view.getRelation(rRelationName);
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     /* 3. The attribute names come from both the left and right operands */
     for(int i=0; i < lRelation.orderedAttributes.size(); i++) {
       attributes.add(lRelation.orderedAttributes.get(i));
     }
     
     for(int i=0; i < rRelation.orderedAttributes.size(); i++) {
       attributes.add(rRelation.orderedAttributes.get(i));
     }
     
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     Relation prodRelation = new Relation("prod", attributeArray);
     
     Literal[] rowToAdd = new Literal[attributeArray.length];
     for(int k=0; k < lRelation.rows.size(); k++) {
       
       for(int i=0; i < rRelation.rows.size(); i++) {
         
         int left = lRelation.orderedAttributes.size();
         
         for(int j=0; j < left; j++) {
           rowToAdd[j] = lRelation.rows.get(k).get(j);
         }
         
         int rIndex = 0;
         for(int l=left; l < rowToAdd.length; l++) {
           rowToAdd[l] = rRelation.rows.get(i).get(rIndex);
           rIndex++;
         }
         rIndex = 0;
         prodRelation.addRow(rowToAdd);
       }
     }
     
     /* testing code: delete later */
     view.addRelation(prodRelation);
     System.out.println("Product Successful! \n");
     
   }
   
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
   
   public static void main(String[] args) {
     
     /* Testing toggles */
     int testUnion = 0;
     int testDifference = 0;
     int testProduct = 0;
     int testSelection = 0;
     int testProjection = 0;
     int testRenaming = 0;
     
     int testCommands = 1;
     
     int testOpenCommand = 0;
     int testWriteCommand = 0;
     int testCloseCommand = 0;
     int testShowCommand = 0;
     int testCreateCommand = 0;
     int testUpdateSingle = 0;
     int testUpdateMultiple = 0;
     int testInsertFromList = 0;
     int testInsertFromRelation = 0;
     int testDeleteCommand = 0;
     
     /* Union Test */
     if(testUnion == 1) {
       System.out.println("Testing Union... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       Attribute[] samplesTwo = {new Attribute("big",0), new Attribute("large",10)};
       String[] samplePrimaries = {"big", "large"};
       
       Relation relOne = new Relation("One", samples, samplePrimaries);
       Relation relTwo = new Relation("Two", samples, samplePrimaries);
       
       Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleTwo = {new Literal(new Attribute("big",10),"tree"), new Literal(new Attribute("large",10),"plant")};
       Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
       Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
       
       Literal[] sampleThreeInt = {new Literal(new Attribute("big",0),"10"), new Literal(new Attribute("large",10),"sphere")};
       Literal[] sampleFourInt = {new Literal(new Attribute("big",0),"4"), new Literal(new Attribute("large",10),"canine")};
       
       relOne.addRow(sampleOne);
       
       relTwo.addRow(sampleThree);
       relTwo.addRow(sampleFour);
       relTwo.addRow(sampleFive);
       relTwo.addRow(sampleTwo);
       
       System.out.println("Test w/o adding to database... \n");
       unionQuery(relOne.name, relTwo.name);
       
       System.out.println("Test after adding to database... \n");
       view.addRelation(relOne);
       view.addRelation(relTwo);
       unionQuery(relOne.name, relTwo.name);
       view.testPrint();
       
       System.out.println("Testing non compatible relations... \n");
       Relation relThree = new Relation("Three", samplesTwo, samplePrimaries);
       
       relThree.addRow(sampleThreeInt);
       relThree.addRow(sampleFourInt);
       
       view.addRelation(relThree);
       view.testPrint();
       
       view.delRelation("union");
       unionQuery(relOne.name, relThree.name);
       view.testPrint();
       
       
       System.out.println("Testing w/ one relation being empty... \n");
       Relation relFour = new Relation("Four", samples, samplePrimaries);
       view.delRelation("union");
       view.addRelation(relFour);
       unionQuery(relTwo.name, relFour.name);
       view.testPrint();
     }
     
     if(testDifference == 1) {
       System.out.println("Testing Difference... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       String[] samplePrimaries = {"big", "large"};
       
       Relation relOne = new Relation("One", samples, samplePrimaries);
       Relation relTwo = new Relation("Two", samples, samplePrimaries);
       
       Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
       Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
       Literal[] sampleSix = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleSeven = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
     
       relTwo.addRow(sampleThree);
       relTwo.addRow(sampleFive);
       relTwo.addRow(sampleTwo);
       
       relOne.addRow(sampleOne);
       relOne.addRow(sampleFour);
       relOne.addRow(sampleSix);
       relOne.addRow(sampleSeven);
       
       view.addRelation(relOne);
       view.addRelation(relTwo);
       differenceQuery(relOne.name, relTwo.name);
       view.testPrint();
     }
     
     if(testProduct == 1) {
       System.out.println("Testing Product... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       String[] samplePrimaries = {"big", "large"};
       
       Relation relOne = new Relation("One", samples, samplePrimaries);
       Relation relTwo = new Relation("Two", samples, samplePrimaries);
       
       Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
       Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
       Literal[] sampleSix = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleSeven = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
     
       relOne.addRow(sampleThree);
       relOne.addRow(sampleSix);
       
       relTwo.addRow(sampleOne);
       relTwo.addRow(sampleFour);
       
       view.addRelation(relOne);
       view.addRelation(relTwo);
       productQuery(relOne.name, relTwo.name);
       view.testPrint();
     }
     
     if(testSelection == 1) {
       
     }
     
     if(testProjection == 1) {
       System.out.println("Testing Projection... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10), new Attribute("glob", 0)};
       String[] samplePrimaries = {"big", "large"};
       
       Relation relTwo = new Relation("Two", samples, samplePrimaries);
       
       Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleTwo = {new Literal(new Attribute("big",10),"tree"), new Literal(new Attribute("large",10),"plant")};
       Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere"), new Literal(new Attribute("glob",0), "")};
       Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine"), new Literal(new Attribute("glob",0), "5")};
       Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer"), new Literal(new Attribute("glob",0), "1")}; 
       
       relTwo.addRow(sampleThree);
       relTwo.addRow(sampleFour);
       relTwo.addRow(sampleFive);
       
       view.addRelation(relTwo);

       ArrayList<String> attributeNames = new ArrayList();
       attributeNames.add("big");
       attributeNames.add("glob");
       
       System.out.println("Testing Case 1 Projection... \n");
       projectionQuery(relTwo.name, attributeNames);
       view.testPrint();
       
       System.out.println("Testing Case 2 Projection... \n");
       attributeNames.set(1, "large");
       view.delRelation("proj");
       projectionQuery(relTwo.name, attributeNames);
       view.testPrint();
       
       System.out.println("Testing bad attribute names passed... \n");
       attributeNames.set(0, "hello");
       view.delRelation("proj");
       projectionQuery(relTwo.name, attributeNames);
       view.testPrint();
     }
     
     if(testRenaming == 1) {
       System.out.println("Testing Renaming... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       Attribute[] samplesTwo = {new Attribute("big",0), new Attribute("large",10)};
       String[] samplePrimaries = {"big", "large"};
       
       Relation relOne = new Relation("One", samples, samplePrimaries);
       Relation relTwo = new Relation("Two", samples, samplePrimaries);
       
       Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       Literal[] sampleTwo = {new Literal(new Attribute("big",10),"tree"), new Literal(new Attribute("large",10),"plant")};
       Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
       Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
     
       relOne.addRow(sampleOne);
       relOne.addRow(sampleTwo);
       
       relTwo.addRow(sampleThree);
       relTwo.addRow(sampleFour);
       relTwo.addRow(sampleFive);
       
       view.addRelation(relOne);
       view.addRelation(relTwo);
       
       System.out.println("Rename existing relation... \n");
       ArrayList<String> attributeNames = new ArrayList();
       attributeNames.add("bob");
       attributeNames.add("gunge");
       
       renamingQuery(relOne.name, relTwo.name, attributeNames);
       renamingQuery(relOne.name, relOne.name, attributeNames);
       view.testPrint();
       
       attributeNames.add("hello");
       renamingQuery(relOne.name, relOne.name, attributeNames);
       
       
       System.out.println("Rename to new relation... \n");
       attributeNames.remove(0);
       renamingQuery("newRel", relOne.name, attributeNames);
       view.testPrint();
     }
     
     if(testCommands == 1) {
       System.out.println("TESTING ALL COMMANDS... \n");
       
       Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       String[] samplePrimaries = {"big", "large"};
       Relation simpleRel = new Relation("One", samples, samplePrimaries );
		   Relation insertTest = new Relation("outputFile", samples, samplePrimaries);
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
       
       System.out.println("Testing show command... \n");
       showCommand("bad");
       showCommand("One");
       
       System.out.println("Testing create command... \n");
       createCommand("two", samples, samplePrimaries);
       
       System.out.println("Testing insert from list command... \n");
       insertFromList("two", sampleOne);
       insertFromList("two", sampleTwo);
       insertFromList("two", sampleThree);
       insertFromList("two", sampleFour);
       insertFromList("two", sampleFive);
       
       System.out.println("Testing show command... \n");
       showCommand("two");
        
       System.out.println("Testing insert from relation command... \n");
       insertFromRelation("outputFile","doesNotExist");
       
       System.out.println("Testing show command... \n");
       showCommand("outputFile");
		   
       System.out.println("Testing write command... \n");
		   writeCommand("outputFile");
		   
       System.out.println("Testing close command... \n");
		   closeCommand("two");
		
		
		   System.out.println("Testing open command... \n");
		   openCommand("one");
		   openCommand("two");
		     
       System.out.println("Testing show command... \n");
		   showCommand("two");
       
       if(testOpenCommand == 1) {
         
       }
       
       if(testWriteCommand == 1) {
         
       }
       
       if(testCloseCommand == 1) {
         
       }
       
       if(testShowCommand == 1) {

       }
       
       if(testCreateCommand == 1) {
         
       }
       
       if(testUpdateSingle == 1) {
         
       }
       
       if(testUpdateMultiple == 1) {
         
       }
       
       if(testInsertFromList == 1) {
         
       }
       
       if(testInsertFromRelation == 1) {
         
       }
       
       if(testDeleteCommand == 1) {
         
       }
       
     }
     
   }
   
 }