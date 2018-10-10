/*
 * This file was created by Murtaza Hakimi of team 54 in Dr. Daugherity's 
 * CSCE 315 Class 
 * 
 *Utilized some code from https://stackoverflow.com/questions/26699089/infix-to-postfix-using-stacks-java
 *For conversion of condition statements from infix to postfix notation - Luis
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

        /*
        TO DO: FOR CONDITIONS:
            IMPLEMENT INFIX TO POSTFIX CONVERSION
            IMPLEMENT POSTFIX COMPUTATION INTO END RESULT
            CHANGE ARGUMENTS OF SELECTION, DELETION, AND UPDATE TO REFLECT NEW PARSETREE ARGUMENT
               FOR QUERIES:
            IMPLEMENT A WAY OF DETERMINING WHEN THE RESULT OF A QUERY WILL BE ASSIGNED TO A UNIQUE RELATION NAME (The name to the left of <-)
            I'M THINKING A METHOD CALLED QUERY OR FINALQUERY THAT TAKES THE WANTED NEW RELATION NAME, AS WELL AS A STRING INDICATING WHAT TYPE OF QUERY TO PERFORM
            WITH A SWITH CASE STRUCTURE THAT WILL PERFORM THE APPROPRIATE QUERY AND THEN CHANGE THE NAME OF THE QUERY SPECIFIC RELATION TO THE ONE WANTED
               FOR LISTENER:
            USE FILE DBMSLISTENER.JAVA
            NEED TO IMPLEMENT ALL THE OVERRIDE METHODS TO PASS APPROPRIATE INPUT TO CORRESPONDING COMMANDS/QUERIES
               
        */



/**
 *
 * @author Murtaza Hakimi
 */
 public class DBMSEngine {
   
   public static Database view = new Database("view");
   public static Database queue = new Database("queue");
   public static DiskHandler diskHandler;
   
   public static void assignmentCommand(String relationNameTo, String relationNameFrom) {
     
     Relation relTo = view.getRelation(relationNameFrom);
     relTo.name = relationNameTo;
     if (!(view.getRelationIndex(relationNameTo) == -1)) { view.delRelation(relationNameTo); }
     view.addRelation(relTo);
     
     // // check if relation exist in database
     // if (view.getRelationIndex(relationNameTo) == -1) {
     //   Relation relTo = view.getRelation(relationNameFrom);
     //   relTo.name = relationNameTo;
     //   view.addRelation(relTo);
     //   return;
     // } else {
     // 
     // 
     // 
     //   String tempName = relationNameTo;
     //   //view.delRelation(relationNameTo);
     //   Relation relTo = new Relation("hello", new Attribute[0]);
     //   relTo = view.getRelation(relationNameFrom);
     //   relTo.name = tempName;
     //   view.addRelation(relTo);
     // }
    
   }
   
   public static void selectionQuery(String relationName, ParseTree conditionTree) {
     // check if relation exist in database
     if (view.getRelationIndex(relationName) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
       return;
     }
     
     ArrayList<ArrayList<Literal>> matchedRows = conditTopEnd(relationName, conditionTree);
     
     ArrayList<Attribute> attributes = new ArrayList();
     
     // add the attributes to the list 
     for(int i =0; i < matchedRows.get(0).size(); i++) {
       attributes.add(matchedRows.get(0).get(i).attribute);
     }
     
     // if the first attribute is !!! then the attributes didnt match the relation
     if(attributes.get(0).name.equals("!!!")) {
       System.out.println("InvalidDBException: attributes did not match any in the relation \n");
       return;
     }
     
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     String name = Integer.toString(queue.relations.size());
     Relation selectRelation = new Relation(name, attributeArray);
     
     selectRelation.rows = matchedRows;
     
     /* testing code: delete later */
     queue.addRelation(selectRelation);
     System.out.println("Selection Successful! \n");
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
     String name = Integer.toString(queue.relations.size());
     Relation projTo = new Relation(name, attributeArray);
     
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
     queue.addRelation(projTo);
     System.out.println("Projection Successful! \n");
     
   }
   
   public static void renamingQuery(String attributesFromRelation, ArrayList<String> attributeNames) {
     // check if relation exist in database
     if (view.getRelationIndex(attributesFromRelation) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
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
     
     Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
     String name = Integer.toString(queue.relations.size());
     Relation relationTo = new Relation(name, attributeArray);
     
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
     queue.addRelation(relationTo);
     System.out.println("Renaming Successful! \n");
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
       int firstTrue = 1;
       
       if(!(lRelation.orderedAttributes.get(i).domain > 0 && rRelation.orderedAttributes.get(i).domain > 0)) {
         firstTrue = 0;
       }
       
       if(!(lRelation.orderedAttributes.get(i).domain == rRelation.orderedAttributes.get(i).domain)) {
         if(firstTrue !=1) { return false; }
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
     String name = Integer.toString(queue.relations.size());
     Relation unionRelation = new Relation(name, attributeArray);
     
     int totalNumRows = lRelation.rows.size() + rRelation.rows.size();
     
     for(int i=0; i < lRelation.rows.size(); i++) {
       unionRelation.addRow(lRelation.rows.get(i).toArray(new Literal[lRelation.rows.get(i).size()]));
     }
     
     for(int i=0; i < rRelation.rows.size(); i++) {
       unionRelation.addRow(rRelation.rows.get(i).toArray(new Literal[rRelation.rows.get(i).size()]));
     }
     
     /* testing code: delete later */
     // TODO: check for existing union and delete it before adding new one
     queue.addRelation(unionRelation);
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
     String name = Integer.toString(queue.relations.size());
     Relation diffRelation = new Relation(name, attributeArray);
     
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
     queue.addRelation(diffRelation);
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
     String name = Integer.toString(queue.relations.size());
     Relation prodRelation = new Relation(name, attributeArray);
     
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
     queue.addRelation(prodRelation);
     System.out.println("Product Successful! \n");
     
   }
   
   public static void openCommand(String toOpen) {
        
        //Uses given database file name to attempt to open up and add the relation with the given name to the view from
        //Existing db files
		//Works Properly
		try {
			Relation toAdd = diskHandler.readDisk(toOpen);
			view.addRelation(toAdd);
      
      view.delRelation("!!!");
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
    
    public static void updateCommand(String relationName, Attribute[] toUpdate, Literal[] toChange, ParseTree conditionTree) {
        //Updates the given relation, with only 1 attribute to be set to a literal
       //Updates tuples that satisfy a condition given
	    if (view.getRelationIndex(relationName) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
       return;
        }

        for (int j = 0; j < toUpdate.length; j++){
            for (int i = 0; i < view.getRelation(relationName).orderedAttributes.size(); i ++)
            {
                if (toUpdate[j].name.equals(view.getRelation(relationName).orderedAttributes.get(i).name)){
                    System.out.println(toUpdate[j].name);
                    System.out.println(view.getRelation(relationName).orderedAttributes.get(i).name);
                    break;
                }   
                if (i == (view.getRelation(relationName).orderedAttributes.size() - 1)){
                System.out.println("One or more attributes given to update do not exist in this relation!");
                return;
            }
            }
            
        }
        
        ArrayList<ArrayList<Literal>> conditionMetRows = conditTopEnd(relationName, conditionTree);
        
        
        /*
           This absolute monstrosity of 4 nested for loops is a lot less scary than it looks, as the innermost 2 loops will only happen occasionally
           In addition, this setup allows for a single updateCommand method that will work with either a single attribute to change, or more
           Outermost loop iterates through the arraylist of rows that satisfied the condition
           Second loop iterates through the given relation to find the row that corresponds to the current row in the arraylist 
           Third loop iterates through the array of attributes to change (usually 1, can be more)
           Fourth loop iterates through the columns of the row, until it finds the one that corresponds to the attribute being changed
           Finally, it changes the value of the literal at that point
        
        */
        
        for (int i = 0; i < conditionMetRows.size(); i++){
            for (int l = 0; l < view.getRelation(relationName).rows.size(); l++){
                if(areRowsEqual(conditionMetRows.get(i),view.getRelation(relationName).rows.get(l)) == true){
                    for (int j = 0; j < toUpdate.length; j++){
                            for (int k = 0; k < view.getRelation(relationName).orderedAttributes.size(); k++)
                            {
                                if(toUpdate[j].name.equals(view.getRelation(relationName).orderedAttributes.get(k).name)){
                                    view.getRelation(relationName).rows.get(l).get(k).literal = toChange[j].literal;
                                }
                            }
                    }
                }
            }
        }
        
        
	}
	
	
    public static void insertFromList(String relationName, Literal[] litList) {
        //Inserts into a given existing relation from a list of literals
        String literalName = "";
        
        if (view.getRelationIndex(relationName)> -1){
             int relIndex = view.getRelationIndex(relationName);
             if (litList.length >= view.relations.get(relIndex).orderedAttributes.size()){
                for (int i = 0; i < view.relations.get(relIndex).orderedAttributes.size(); i++){
                    literalName = litList[i].literal;    
                    if (view.relations.get(relIndex).orderedAttributes.get(i).domain > 1)
                    {
                        if (view.relations.get(relIndex).orderedAttributes.get(i).domain < literalName.length()) {   
                            litList[i].literal = literalName.substring(0,view.relations.get(relIndex).orderedAttributes.get(i).domain - 1);
                        }
                    }

                    litList[i].attribute = view.relations.get(relIndex).orderedAttributes.get(i);

                }

                view.relations.get(relIndex).addRow(litList);
             }
             else {
                 System.out.println("Not enough literals in list to add to table!");
             }
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
                if (unionCompatible(view.relations.get(relIndex),view.relations.get(otherRelIndex)) == true){
                    for (int i = 0; i < otherRelRows; i++){
                //Iterate once for every row in relation whose values we are drawing from        
                        view.relations.get(relIndex).addRow(view.relations.get(otherRelIndex).rows.get(i).toArray(new Literal[view.relations.get(otherRelIndex).rows.get(i).size()]));
                //This very long line of code simply adds a row to the relation we are inserting into
                //The row consists of a copy of the i'th row of the other relation (we need to convert from arraylist to array, hence the long line)
                    }
                } else {
                    System.out.println("Both relations exist in this database, but they are not union compatible!");
                }
        }
        else {
            System.out.println("one or more of the relations in this Insert command do not exist in database!");
        }
        //Print appropriate message if either of the relations does not exist
    }
    public static void deleteCommand(String relationName, ParseTree conditionTree) {
       //Deletes tuples from the relation given, taking out the tuples that satisfy the condition
       //given by the 3 strings
     // check if relation exist in database
     if (view.getRelationIndex(relationName) == -1) {
       System.out.println("InvalidDBException: Relation Does not Exist");
       return;
     }
     
     selectionQuery(relationName, conditionTree);
     
     String name = queue.relations.get(queue.relations.size()-1).name;
     Relation select = queue.getRelation(name);
     view.addRelation(select);
     queue.delRelation(name);
     
     differenceQuery(relationName, name);
     String nameDIFF = queue.relations.get(queue.relations.size()-1).name;
     
     view.delRelation(name);
     view.delRelation(relationName);
     Relation newRel = queue.getRelation(nameDIFF);
     queue.delRelation(nameDIFF);
     view.addRelation(newRel);
     newRel.name = relationName;
     
     
    }
    
    public static ArrayList<ArrayList<Literal>> conditTopEnd (String relationName, ParseTree conditionTree){
       
       /* 
       The purpose of this method is to take a relation name and a condition parse tree
       And traverse the parse tree to obtain an infix list of condition operands and operators,
       Then utilize a stack structure to convert the list into postfix
       And once the list is in postfix notation, evaluate it using the computeCondition method
       ending up with a single ArrayList of rows that contains every row in the relation that
       satisfies all conditions given
       */
       ArrayList<ArrayList<Literal>> validTuples = new ArrayList();
       
       if (view.getRelationIndex(relationName) == -1) {
      System.out.println("InvalidDBException: Relation Does not Exist");
      return validTuples;
         }
       String connectLiteral = "";
       List<ParseTree> children = getChildList(conditionTree);
       List<String> conditionStrings = getLeafNodeList (children);
       //Get a list of strings that is made up of all the leaf nodes
      
       //Create 4 Lists of strings:
       //1 for the initially processed parsetree text
       //1 for the infixed list consisting of operands (relation names of the temp relations holding the tuples that satisfy that condition)
       //1 to keep track of the number of and names of the temporary condition tuple holding relations this method uses, so they can be deleted at the end
       //1 to store the postfix list gotten from infixtopostfix method
       List<String> properStrings = new LinkedList<String>();
       List<String> infixedList = new LinkedList<String>();
       List<String> tempRelNames = new LinkedList<String>();
       List<String> postfixedList = new LinkedList<String>();
       //End of List Initializations
       
       //Create integer tempRelCounter to keep track of how many temporary relations there are
       int tempRelCounter = 0;
       
       
       //Create an array of attributes to pass to every temporary relation made
       ArrayList<Attribute> attributes = new ArrayList();
       // add the attributes to the list 
       for(int i =0; i < view.getRelation(relationName).orderedAttributes.size(); i++) {
                attributes.add(view.getRelation(relationName).orderedAttributes.get(i));
       }
      Attribute[] attArray = attributes.toArray(new Attribute[attributes.size()]);
      //End of attribute array generation
      
       //Begin properString creation loop
       for (int i = 0; i < conditionStrings.size(); i++){
          if (conditionStrings.get(i).equals("\"")){
              connectLiteral = conditionStrings.get(i) + conditionStrings.get(i+1) + conditionStrings.get(i+2);
              properStrings.add(connectLiteral);
              i++;
              i++;
          }else{
           properStrings.add(conditionStrings.get(i));
                   }
       }
       //Above loop creates a new list with the string literals all together
       
       
       //Below loop goes through properString list, creates a temporary relation for each comparison operator it finds, using the operands to the left and right of it
       //properString consists entirely of comparison operators, comparison operands, and logical operators
       for (int i =0; i < properStrings.size(); i++){
           if (isLogicOperator(properStrings.get(i)) == true){
               infixedList.add(properStrings.get(i));
               //If current string is a logical operator, add it to infixList
           } else
               if (isCompOperator(properStrings.get(i)) == true)
                 {
                   //No need to worry about outOfBounds exception as thanks to our parser a comparison operator will always be surrounded by 2 operands
                   ArrayList<ArrayList<Literal>> tempRows = computeCondition(relationName, properStrings.get(i-1),properStrings.get(i),properStrings.get(i+1));
                   //Create rows for temporary relation by calling our computeCondition method, which will return an array of rows that contains every row satisfying that condition
                   
                   //Check for the error output of  the computeCondition method, in case one of the operands is invalid, and end this method if that's the case
                  if (tempRows.get(0).get(0).attribute.name.equals("!!!")) {
                         System.out.println("InvalidDBException: attributes did not match any in the relation \n");
                         return tempRows;
                      }
                   //If it passes the error check, create new temporary relation in queue database with name C(temprelcounter)
                   tempRelCounter++;
                   String tempRelName = ("c" + tempRelCounter);
                   Relation tempRel = new Relation(tempRelName,attArray);
                   tempRel.rows = tempRows;
                   queue.addRelation(tempRel);
                   //Relation will contain the rows from computed condition
                   tempRelNames.add(tempRelName);
                   infixedList.add(tempRelName);
                   //Pass the name of this new relation to the infixList to be used as an operand
               }
           
       }
       
       /*
       EVERYTHING UP TO HERE WORKS
       */
       
       postfixedList = infixToPostfix(infixedList);
       /*
        CONVERT INFIX LIST TO POSTFIX
       */
       
       /*
         CODE TO PROCESS POSTFIX LIST AND GET FINAL RESULT IN POSTFIXCONDIT METHOD
       */
       
       //If there was only one condition, simply return those tuples which satisfied it
       if (tempRelCounter == 1){
           validTuples = queue.getRelation(tempRelNames.get(0)).rows;
           queue.delRelation(tempRelNames.get(0));
       }else{
       //If there were multiple conditions, move on to postfix method;
           validTuples = postFixCondit(postfixedList);
           for (int t = 0; t < tempRelCounter; t++){
               queue.delRelation(tempRelNames.get(t));
           }
           
       }
       /*
         ABOVE CODE TO DELETE ALL TEMP RELATIONS RIGHT BEFORE RETURNING 
       */
       return validTuples;
       
   }
    
   public static ArrayList<ArrayList<Literal>> postFixCondit (List<String> toEval){
       //Method will iterate through postfix list
       //And evaluate every operator with its corresponding operands
       //Finally returning the result of every operation
       ArrayList<ArrayList<Literal>> validTuples = new ArrayList();
       Stack<String> evalStack = new Stack<String>();
       String curr = "";
       String leftOp = "";
       String rightOp = "";
       for (int i =0; i < toEval.size(); i++){
           curr = toEval.get(i);
           if (isLogicOperator(curr)== false){
               evalStack.push(curr);
           }
           if (isLogicOperator(curr) == true){
               rightOp = evalStack.pop();
               leftOp =  evalStack.pop();
               
               if (curr.equals("||")){
                  //Perform a union query of left and right relations,
                  //Assign the union query rows as the new rows for the relation with name = leftop
                  //Push leftop back to the stack
                  getFromBoth(leftOp,rightOp);
                  //Once rows have been reassigned, delete the union query relation
                  evalStack.push(leftOp);
               }else if(curr.equals("&&")){
                  //Compute the intersection of row values of left and right relations
                  //Assign the intersection rows as the new rows for the relation with name = leftop
                  //Push leftop back to the stack
                   getIntersection(leftOp,rightOp);
                   
                   evalStack.push(leftOp);
                   
                   
                   
                   
               }    
           }     
       }
       curr = evalStack.pop();
       validTuples = queue.getRelation(curr).rows;
       return validTuples;
   }
   public static void getIntersection(String leftRel, String rightRel){
       //From 2 temporary relations in the queue
       //Returns the rows which are in both relations  
    ArrayList<Literal> currRow = new ArrayList();   
    ArrayList<ArrayList<Literal>> tuplesInCommon = new ArrayList();
    //Check if either relation has no rows, because it would automatically mean the intersection is empty
    if (queue.getRelation(leftRel).rows.size() == 0 || queue.getRelation(leftRel).rows.size() == 0){
        queue.getRelation(leftRel).rows = tuplesInCommon;
    }else {
        for (int i = 0; i < queue.getRelation(rightRel).rows.size(); i++)
            {
               currRow = queue.getRelation(rightRel).rows.get(i);
        
           for(int j = 0; j < queue.getRelation(leftRel).rows.size(); j++){
               if (areRowsEqual(currRow,queue.getRelation(leftRel).rows.get(j))== true){
                   tuplesInCommon.add(currRow);
               }
           }   
         }
        
        //Method doesn't return anything, it simply sets the values of the left operand relation to the new computed ones
        queue.getRelation(leftRel).rows = tuplesInCommon;
        queue.getRelation(leftRel).testPrint();
    }
    
    
   }
   public static void getFromBoth(String leftRel, String rightRel){
    ArrayList<ArrayList<Literal>> tuplesInBoth = new ArrayList();
    if(queue.getRelation(leftRel).rows.isEmpty()){
        tuplesInBoth = queue.getRelation(rightRel).rows;
       } else 
        if(queue.getRelation(rightRel).rows.isEmpty()){
            tuplesInBoth = queue.getRelation(leftRel).rows;
           }else{
           tuplesInBoth = queue.getRelation(leftRel).rows;
           int initialLength = queue.getRelation(leftRel).rows.size();
           ArrayList<Literal> currRow = new ArrayList();
           for (int i = 0; i < queue.getRelation(rightRel).rows.size(); i++)
           {
               currRow = queue.getRelation(rightRel).rows.get(i);

               for(int j = 0; j < initialLength; j++){
                   if (areRowsEqual(tuplesInBoth.get(j),currRow) == true){
                       break;
                   }else if (j == initialLength -1){
                       tuplesInBoth.add(currRow);
                   } 
               }   
           }
        }
    queue.getRelation(leftRel).rows = tuplesInBoth;
    queue.getRelation(leftRel).testPrint();
   }
   public static List<String> infixToPostfix(List<String> toConvert){
       /*
       THIS METHOD CONVERTS AN INFIX LIST OF OPERATORS AND OPERANDS TO POSTFIX AS PER THE ALGORITH FOUND HERE:
       https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
       */
       List<String> postfixList = new LinkedList<String>();
       String curr = "";
       Stack<String> postStack = new Stack<String>();
       String ridof = "";
       for (int i = 0; i < toConvert.size(); i++){
       curr = toConvert.get(i);
           if (isLogicOperator(curr)==false){
               postfixList.add(curr);
           }else
               if (curr.equals("(")){
                   postStack.push(curr);
               }else if( curr.equals(")")){
                   while (!postStack.isEmpty() && !(postStack.peek().equals("("))){
                       postfixList.add(postStack.pop());
                   }
                   if (!postStack.isEmpty() && !(postStack.peek().equals("("))){
                       System.out.println("Invalid expression in infixtopostfixmethod");
                   }else{
                       postStack.pop();
                   }  
               }
            else {
                   while (!postStack.isEmpty() && hasHigherPrecedence(postStack.peek(),curr)){
                       postfixList.add(postStack.pop());
                   }
                   postStack.push(curr);
                 }
       }
       while (!postStack.isEmpty()){
           postfixList.add(postStack.pop());
       }
       
       return postfixList;
   } 
   public static boolean hasHigherPrecedence(String s1, String s2){
       // Method made to work with the above infixtopostfix algorithm
       switch (s1){
               
           case "||":
               return false;
           case "&&":
               return (s2.equals("||"));
           
           default:
               return false;
           
           
       }
   }
   public static boolean isCompOperator(String s){
       //This method takes a string and determines if it is a comparison operator
       switch (s){
           case "==":
           case "<=":
           case ">=":
           case ">":
           case "<":
           case "!=":
               return true;
           default:
               break;
       }
       return false;
   }
   public static boolean isLogicOperator(String s){
       //This method takes a string and determines if it is an && or || logic operator
       switch (s){
           case "&&":
           case "||":
           case "(":
           case ")":
               return true;
           default:
               break;
       }
       return false;
   }
   public static List<String> getLeafNodeList (List<ParseTree> toIterate) {
       //This method generates a list of strings consisting of the inorder leaf nodes 
       //of the parsetree input
       List<String> leafNodes = new LinkedList<String>();
       List<ParseTree> recursChild = null;
       boolean add = false;
       for (ParseTree child:toIterate){
            if (child.getChildCount() == 0) {
                leafNodes.add(child.getText());
            }
           
           if (child.getChildCount() > 0) {
               recursChild = getChildList(child);
               add = leafNodes.addAll(getLeafNodeList(recursChild));
           }
            
       }
      return leafNodes;
   }
   public static List<ParseTree> getChildList (ParseTree toList){
       List<ParseTree> childList = new LinkedList<ParseTree>();
       for (int i = 0; i < toList.getChildCount(); i ++){
           childList.add(toList.getChild(i));
       }
       
       return childList;
   }
   public static ArrayList<ArrayList<Literal>> computeCondition(String relationName, String leftOp, String operator, String rightOp){
   //Will create a list of tuples 
   
   
   //Create temporary relation from relationName
   ArrayList<ArrayList<Literal>> validTuples = new ArrayList();
   Relation tempRel = view.getRelation(relationName);
   int leftOpType = 0;
   int rightOpType = 0;
   boolean addThisTuple = false;
   //Check that both operands are valid, and assign them a type
   //Type 1 for Integer, Type 2 for String, Type 3 for attribute-name
   if(isInteger(leftOp) == true){
     leftOpType = 1;
   }
   else
     if (isLiteral(leftOp) == true){
       leftOpType = 2;
       leftOp = leftOp.substring(1,leftOp.length()-1);
   }
   else {
     for (int i = 0; i < tempRel.orderedAttributes.size(); i++){
       if (leftOp.equals(tempRel.orderedAttributes.get(i).name)){
         leftOpType = 3;
         break;
       }
       if (i == (tempRel.orderedAttributes.size() - 1)){
         ArrayList<Literal> errTuple = new ArrayList();
         errTuple.add(new Literal(new Attribute("!!!",10),"!!!"));
         validTuples.add(errTuple);
         return validTuples;
       }
     }
     
   }	
   if(isInteger(rightOp) == true){
     rightOpType = 1;
   }
   else
     if (isLiteral(rightOp) == true){
       rightOpType = 2;
       rightOp = rightOp.substring(1,rightOp.length()-1);
       
   }
   else {
     for (int i = 0; i < tempRel.orderedAttributes.size(); i++){
       if (rightOp.equals(tempRel.orderedAttributes.get(i).name)){
         rightOpType = 3;
         break;
       }
       if (i == (tempRel.orderedAttributes.size() - 1)){
         System.out.println("Invalid right operand! aborting comparison...");
         ArrayList<Literal> errTuple = new ArrayList();
         errTuple.add(new Literal(new Attribute("!!!",10),"!!!"));
         validTuples.add(errTuple);
         return validTuples;
       }
     }
     
   }	
     
   
   for (int j = 0; j < tempRel.rows.size(); j++){	
     addThisTuple = singleComparison(leftOp, operator, rightOp, leftOpType, rightOpType, tempRel.rows.get(j));
     if (addThisTuple == true){
       validTuples.add(tempRel.rows.get(j));
     }
       
   }
   return validTuples;
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
   int leftNum = 0;
   int rightNum = 0;
   if(leftOpType != 3 && rightOpType !=3 && leftOpType != rightOpType){
     
     return false;
   }
   //Return false if the operand types are incompatible
   
               
               
               //Find what kind of literal this attribute corresponds to
               //If this function was called at all, it's because this attribute exists in the table 
   if (leftOpType == 3) {
     for (int i = 0; i < tupleToCheck.size(); i++){
       if (leftOp.equals(tupleToCheck.get(i).attribute.name))
       {
         if (tupleToCheck.get(i).attribute.domain > 0){
           leftOpType = 2;
         }
         else {
           leftOpType = 1;
         }
         leftOp = tupleToCheck.get(i).literal;
         
   //Assign the operand a type depending on the attribute that was found			
       }
     }
   }
   //Find what kind of literal this attribute corresponds to
               //If this function was called at all, it's because this attribute exists in the table 		
     if (rightOpType == 3) {
     for (int i = 0; i < tupleToCheck.size(); i++){
       if (rightOp.equals(tupleToCheck.get(i).attribute.name))
       {
         if (tupleToCheck.get(i).attribute.domain > 0){
           rightOpType = 2;
         }
         else {
           rightOpType = 1;
         }
         rightOp = tupleToCheck.get(i).literal;
         
   //Assign the operand a type depending on the attribute that was found						
       }
     }
     
   }
   
   //Now check that the deciphered operand types are still compatible
               //return false if they are not
   if (rightOpType != leftOpType) {
     return false;
   }
   
   
   //Now that both operands have been translated to literals, and it is
               //confirmed that they are compatible, switch case structure performs
               //the actual comparison based on the utilized operator
   
               
               //Each case after == and != will check if either operator is a string, and return false if it is
               
   switch (operator){
     case "==":
         if (leftOp.equals(rightOp)){ return true;}
       break;
     case "!=":	
         if (!(leftOp.equals(rightOp))){return true;}
       break;
       
     case "<":
       if(leftOpType == 2){return false;}
       
       try {
         leftNum = Integer.parseInt(leftOp.trim());
         rightNum = Integer.parseInt(rightOp.trim());
         }
       catch (NumberFormatException nfe){}
       if(leftNum < rightNum){return true;}
       
       break;
     
     case ">":
       if(leftOpType == 2){return false;}
       try {
         leftNum = Integer.parseInt(leftOp.trim());
         rightNum = Integer.parseInt(rightOp.trim());
         }
       catch (NumberFormatException nfe){}
       if(leftNum > rightNum){return true;}
       break;
       
     case "<=":
       if(leftOpType == 2){return false;}
       try {
         leftNum = Integer.parseInt(leftOp.trim());
         rightNum = Integer.parseInt(rightOp.trim());
         }
       catch (NumberFormatException nfe){}
       if(leftNum <= rightNum){return true;}
       break;
     case ">=":
       if(leftOpType == 2){return false;}
       try {
         leftNum = Integer.parseInt(leftOp.trim());
         rightNum = Integer.parseInt(rightOp.trim());
         }
       catch (NumberFormatException nfe){}
       if(leftNum < rightNum){return true;}
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
     int testRenaming = 1;
     
     int testCommands = 0;
     int testOpenCommand = 0;
     int testWriteCommand = 0;
     int testCloseCommand = 0;
     int testShowCommand = 0;
     int testCreateCommand = 0;
     int testUpdateCommand = 0;
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
       
       view.delRelation("!!!union");
       unionQuery(relOne.name, relThree.name);
       view.testPrint();
       
       
       System.out.println("Testing w/ one relation being empty... \n");
       Relation relFour = new Relation("Four", samples, samplePrimaries);
       view.delRelation("!!!union");
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
       // System.out.println("Testing Selection... \n");
       // 
       // Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
       // String[] samplePrimaries = {"big", "large"};
       // 
       // Relation relOne = new Relation("One", samples, samplePrimaries);
       // Relation relTwo = new Relation("Two", samples, samplePrimaries);
       // 
       // Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       // Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       // Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       // Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
       // Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
       // Literal[] sampleSix = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
       // Literal[] sampleSeven = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
       // 
       // relOne.addRow(sampleOne);
       // relOne.addRow(sampleTwo);
       // relOne.addRow(sampleThree);
       // relOne.addRow(sampleFour);
       // relOne.addRow(sampleFive);
       // relOne.addRow(sampleSix);
       // relOne.addRow(sampleSeven);
       // 
       // view.addRelation(relOne);
       // 
       // System.out.println("Testing select big == \"boss\" \n");
       // selectionQuery(relOne.name, "big", "\"boss\"", "==");
       // view.testPrint();
       // 
       // System.out.println("Testing select big != \"bee\" \n");
       // view.delRelation("!!!select");
       // selectionQuery(relOne.name, "big", "\"bee\"", "!=");
       // view.testPrint();
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
       view.delRelation("!!!proj");
       projectionQuery(relTwo.name, attributeNames);
       view.testPrint();
       
       System.out.println("Testing bad attribute names passed... \n");
       attributeNames.set(0, "hello");
       view.delRelation("!!!proj");
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
       
       renamingQuery(relTwo.name, attributeNames);
       renamingQuery(relOne.name, attributeNames);
       view.testPrint();
       
       attributeNames.add("hello");
       renamingQuery(relOne.name, attributeNames);
       
       
       System.out.println("Rename to new relation... \n");
       attributeNames.remove(0);
       renamingQuery(relOne.name, attributeNames);
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
       Literal[] sampleSix = {new Literal(new Attribute("big",8),"hippopotamus"), new Literal(new Attribute("large",10),"buzzer")};
       insertFromList("two", sampleSix);
       
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
     }
       
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
       
       if(testUpdateCommand == 1) {
       //     Attribute[] updateSamples = {new Attribute("big",10), new Attribute("weight",0)};
       // String[] updatePrimaries = {"big", "weight"};
       // Relation updateRel = new Relation("updateMe", updateSamples, updatePrimaries );
       // Literal[] upOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("weight",0),"2")};
       // Literal[] upTwo = {new Literal(new Attribute("big",10),"tree"), new Literal(new Attribute("weight",0),"6")};
       // Literal[] upThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("weight",0),"3")};
       // Literal[] upFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("weight",0),"7")};
       // Literal[] upFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("weight",0),"2")};
       // 
       // Literal[] updatedValues = {new Literal(new Attribute("big",10),"UPDATED"), new Literal(new Attribute("weight",0),"20")};
       // 
       // updateRel.addRow(upOne);
       // updateRel.addRow(upTwo);
       // updateRel.addRow(upThree);
       // updateRel.addRow(upFour);
       // updateRel.addRow(upFive);
       // 
       // view.addRelation(updateRel);
       // view.testPrint();
       // 
       // updateCommand("updateMe",updateSamples, updatedValues, "big", "!=", "\"boss\"");
       // 
       // view.testPrint();
       
       }
       
         
       
       
       if(testInsertFromList == 1) {
           
           
             
       }
       
       if(testInsertFromRelation == 1) {
         
       }
       
     
     
     // if(testDeleteCommand == 1) {
     //   System.out.println("Testing Delete Command... \n");
     // 
     //   Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
     //   String[] samplePrimaries = {"big", "large"};
     // 
     //   Relation relOne = new Relation("One", samples, samplePrimaries);
     //   Relation relTwo = new Relation("Two", samples, samplePrimaries);
     // 
     //   Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
     //   Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
     //   Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
     //   Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
     //   Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
     //   Literal[] sampleSix = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
     //   Literal[] sampleSeven = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
     // 
     //   relOne.addRow(sampleOne);
     //   relOne.addRow(sampleTwo);
     //   relOne.addRow(sampleThree);
     //   relOne.addRow(sampleFour);
     //   relOne.addRow(sampleFive);
     //   relOne.addRow(sampleSix);
     //   relOne.addRow(sampleSeven);
     // 
     //   view.addRelation(relOne);
     // 
     //   System.out.println("Testing select big == \"boss\" \n");
     //   deleteCommand(relOne.name, "big", "==", "\"boss\"");
     //   view.testPrint();
     // 
     //   System.out.println("Testing select big != \"bee\" \n");
     //   view.delRelation("!!!select");
     //   deleteCommand(relOne.name, "big", "!=", "\"bee\"");
     //   view.testPrint();
     // }
     
   }
   
 }