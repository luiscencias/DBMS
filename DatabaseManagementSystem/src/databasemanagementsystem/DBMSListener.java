/*
    DBMSListener.java
    This file was created by Luis Davila of Team 54
    in Dr. Daugherity's CSCE 315 Section 506
    for Project 2.

    This file contains the DBMSListener class which extends the base listener created by ANTLR
    
 */

/**
 *
 * @author LuisDavila
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

public class DBMSListener extends DBMSGrammarBaseListener {
    
    public DBMSEngine engine;
    
    // check if relation exist in database 1 = exists
    public int isInView(String relationName) {
      if (engine.view.getRelationIndex(relationName) == -1) {
        return 0;
      } else {
        return 1;
      }
    }
    
    // check if string can be converted to int, 1 is yes
    public int canConvertInt(String s) {
      try { 
        int result = Integer.parseInt(s);
        return 1;
      } catch (NumberFormatException e) {
        return 0;
      }
    }
    
    public void test() {
      /* Testing Purposes: ***************************************************/
      Attribute[] samples = {new Attribute("big",10), new Attribute("large",10)};
      String[] samplePrimaries = {"big", "large"};
      
      Attribute[] samples2 = {new Attribute("gloop",10), new Attribute("fropp",10)};
      String[] samplePrimaries2 = {"gloop", "fropp"};
      
      Relation relOne = new Relation("One", samples, samplePrimaries);
      Relation relTwo = new Relation("Two", samples, samplePrimaries);
      Relation relThree = new Relation("Three", samples2, samplePrimaries2);
      
      Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
      Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
      Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
      Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
      Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
      Literal[] sampleSix = {new Literal(new Attribute("gloop",10),"boss"), new Literal(new Attribute("fropp",10),"boy")};
      Literal[] sampleSeven = {new Literal(new Attribute("gloop",10),"ball"), new Literal(new Attribute("fropp",10),"sphere")};
      
      System.out.println("Adding to view...");
      relOne.addRow(sampleOne);
      relOne.addRow(sampleTwo);
      relOne.addRow(sampleThree);
      
      relTwo.addRow(sampleFour);
      relTwo.addRow(sampleFive);
      
      relThree.addRow(sampleSix);
      relThree.addRow(sampleSeven);
    
      
      engine.view.addRelation(relOne);
      engine.view.addRelation(relTwo);
      engine.view.addRelation(relThree);
      /***********************************************************************/
    }
    
    @Override public void exitQuery(DBMSGrammarParser.QueryContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        // System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }

        ++counter;
      }
      
      String rString;
      
      try { 
        // right operand
        if(isInView(children.get(2).getText()) == 1) { 
          rString = children.get(2).getText();
        } else {
          rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
          engine.view.addRelation(engine.queue.getRelation(rString));
          engine.queue.delRelation(rString);
        }
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      
      try {
        engine.assignmentCommand(children.get(0).getText(), rString);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      
      //System.out.println("Exception cuaght?: " + canConvertInt(rString) + "\n");
      if(canConvertInt(rString) == 1) {
        engine.view.delRelation(rString);
      }
    }
    
    @Override public void exitDeletecmd(DBMSGrammarParser.DeletecmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      try {
        engine.deleteCommand(children.get(1).getText(), children.get(3));
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
    }
    
    @Override public void exitInsertcmd(DBMSGrammarParser.InsertcmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      String relName = children.get(1).getText();
      String rString;
      ArrayList<Literal> lits = new ArrayList();
      
      // insert from relation
      if(children.get(2).getText().contains("RELATION")) {
        
        // right operand
        if(isInView(children.get(3).getText()) == 1) { 
          rString = children.get(3).getText();
        } else {
          rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
          engine.view.addRelation(engine.queue.getRelation(rString));
          engine.queue.delRelation(rString);
        }
        
        try {
          engine.insertFromRelation(relName, rString);
          if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
        } catch (Exception e) {
          System.out.println("Error in Command...\n");
          return;
        }
        
      } else { // insert from list
        
        // relation does not exist
        if(isInView(relName) == 0) {
          System.out.println("Relation " + relName + " does not exist");
          return;
        }
        
        ArrayList<Attribute> atts = engine.view.getRelation(relName).orderedAttributes;
        
        int c = 0;
        for(int i = 4; i < children.size(); i+=2) {
          
          // its a varchar
          if(children.get(i).getText().contains("\"")) {
            String lit = children.get(i).getText().replace("\"", "");
            Literal temp = new Literal(atts.get(c), lit);
            lits.add(temp);
            c++;
            
          } else { // its an integer
            String lit = children.get(i).getText();
            Literal temp = new Literal(atts.get(c), lit);
            lits.add(temp);
            c++;
          }
        }
      }
      
      Literal[] litArray = lits.toArray(new Literal[lits.size()]);
      
      try {
        engine.insertFromList(relName, litArray);
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
      
    }
    
    @Override public void exitUpdatecmd(DBMSGrammarParser.UpdatecmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      String relName = children.get(1).getText();
      
      // check if relation exists
      if(isInView(relName) != 1) {
        System.out.println("Error in Query...");
        return;
      }
      
      Relation getInfo = engine.view.getRelation(relName);
      
      ArrayList<Attribute> attributeList = new ArrayList();
      ArrayList<Literal> literalList = new ArrayList();
      
      String attribute = "";
      String literal = "";
      
      int c = 0;
      boolean isLiteral = false;
      boolean passedWhere = false;
      for (ParseTree child : children) {
        if(c >= 3 && (c % 2) != 0 && passedWhere == false) {
          if(child.getText().equals("WHERE")) { passedWhere = true; }
          
          if(isLiteral == false) {
            attribute = child.getText();
            isLiteral = true;
          } else { 
            if(child.getText().contains("\"")) {
              literal = child.getText().replace("\"", "");
            } else {
              literal = child.getText();
            }
            
            // build and add attribute
            for(int i = 0; i < getInfo.orderedAttributes.size(); i++) {
              if(getInfo.orderedAttributes.get(i).name.equals(attribute)) {
                 attributeList.add(getInfo.orderedAttributes.get(i));
                 System.out.println("adding: " + attribute + " " + getInfo.orderedAttributes.get(i).name + " + ");
              }
            }
            
            // build and add literal
            literalList.add(new Literal(attributeList.get(attributeList.size()-1), literal));
            isLiteral = false;
          }

        }
        c++;
      }
      
      Attribute[] attributeArray = attributeList.toArray(new Attribute[attributeList.size()]);
      Literal[] literalArray = literalList.toArray(new Literal[literalList.size()]);
      
      /* Test: Output attributeArray DELETE IN CLEANUP */
      for(int i = 0; i < attributeArray.length; i++) {
        System.out.println("att: " + attributeArray[i].name + " + ");
      }
      
      /* Test: Output literalArray DELETE IN CLEANUP */
      for(int i = 0; i < literalArray.length; i++) {
        System.out.println("lit: " + literalArray[i].literal + " + ");
      }
      
      try {
        engine.updateCommand(relName, attributeArray, literalArray, children.get(children.size()-1));
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
      
    }
    
    @Override public void enterCreatecmd(DBMSGrammarParser.CreatecmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      // get the relation name and the primary key
      String relName = children.get(1).getText();
      String[] primaryKeys = children.get(6).getText().split(",");
      
      // set up getting info for the attributes
      String[] attributeInfo = children.get(3).getText().split(",");
      ArrayList<String> attributeNames = new ArrayList();
      ArrayList<Integer> types = new ArrayList();
      ArrayList<Attribute> attributes = new ArrayList();
      
      // seperate the attribute names from the type info
      for(int i = 0; i < attributeInfo.length; i++) {
        if(attributeInfo[i].contains("VARCHAR(")) {
          int nameIndex = attributeInfo[i].indexOf("VARCHAR(");
          int lastType = attributeInfo[i].indexOf(")");
          
          attributeNames.add(attributeInfo[i].substring(0, nameIndex));
          
          types.add(Integer.parseInt(attributeInfo[i].substring(nameIndex+8, lastType)));
        }
        else if(attributeInfo[i].contains("INTEGER")) {
          int nameIndex = attributeInfo[i].indexOf("INTEGER");
          
          attributeNames.add(attributeInfo[i].substring(0, nameIndex));
          types.add(0);
        } else { // grammar should check for this but just in case
          System.out.println("Error could not create table for some reason");
        }
      }
      
      //build the attributes and add them to the lists
      for(int i = 0; i < attributeNames.size(); i++) {
        Attribute temp = new Attribute(attributeNames.get(i),types.get(i));
        attributes.add(temp);
      }
      
      Attribute[] attributeArray = attributes.toArray(new Attribute[attributes.size()]);
      
      try {
        engine.createCommand(relName, attributeArray, primaryKeys);
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
      
    }
    
    @Override public void exitShowcmd(DBMSGrammarParser.ShowcmdContext ctx) { 
      ParseTree conditionTree = ctx;
      
      List<ParseTree> children = ctx.children;
      
      List<String> leafText = getLeafNodeList(children);
      
      if(!(leafText.get(1).equals("all"))) {
        try {
          engine.showCommand(leafText.get(1));
        } catch (Exception e) {
          System.out.println("Error in Command...\n");
          return;
        }
      } else {
        engine.view.testPrint();
        engine.queue.testPrint();
      }
      
    }
    
    @Override public void enterExitcmd(DBMSGrammarParser.ExitcmdContext ctx) { 
      System.exit(0);
    }
    
    @Override public void enterWritecmd(DBMSGrammarParser.WritecmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	
  
      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }
  
        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }
  
        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
  
        ++counter;
      }
      
      try {
        engine.writeCommand(children.get(1).getText());
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
        
    }
    
    @Override public void enterClosecmd(DBMSGrammarParser.ClosecmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	
  
      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }
  
        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }
  
        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
  
        ++counter;
      }
      
      try {
        engine.closeCommand(children.get(1).getText());
      } catch (Exception e) {
        System.err.println("Error in Command...:\n");
        return;
      }
    }
    
    @Override public void enterOpencmd(DBMSGrammarParser.OpencmdContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	
  
      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }
  
        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }
  
        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
  
        ++counter;
      }
      
      try {
        engine.openCommand(children.get(1).getText());
      } catch (Exception e) {
        System.err.println("Error in Command...:" + e.getMessage() + "\n");
        return;
      }
    }
    
    @Override public void exitProduct(DBMSGrammarParser.ProductContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	
  
      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }
  
        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }
  
        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
  
        ++counter;
      }
      
      String lString;
      String rString;
      
      // check left operand
      if(isInView(children.get(0).getText()) == 1) { 
        lString = children.get(0).getText();
      } else {
        lString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(lString));
        engine.queue.delRelation(lString);
      }
      
      // check right operand
      if(isInView(children.get(2).getText()) == 1) { 
        rString = children.get(2).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      try {
        engine.productQuery(lString, rString);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      if(canConvertInt(lString) == 1) { engine.view.delRelation(lString); }
      if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
    }
    
    @Override public void exitDifference(DBMSGrammarParser.DifferenceContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	
  
      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }
  
        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }
  
        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
  
        ++counter;
      }
      
      String lString;
      String rString;
      
      // check left operand
      if(isInView(children.get(0).getText()) == 1) { 
        lString = children.get(0).getText();
      } else {
        lString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(lString));
        engine.queue.delRelation(lString);
      }
      
      // check right operand
      if(isInView(children.get(2).getText()) == 1) { 
        rString = children.get(2).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      try {
        engine.differenceQuery(lString, rString);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      if(canConvertInt(lString) == 1) { engine.view.delRelation(lString); }
      if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
    }
    
    @Override public void exitUnion(DBMSGrammarParser.UnionContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }

        ++counter;
      }
      
      String lString;
      String rString;
      
      // check left operand
      if(isInView(children.get(0).getText()) == 1) { 
        lString = children.get(0).getText();
      } else {
        lString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(lString));
        engine.queue.delRelation(lString);
      }
      
      // check right operand
      if(isInView(children.get(2).getText()) == 1) { 
        rString = children.get(2).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      try {
        engine.unionQuery(lString, rString);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      if(canConvertInt(lString) == 1) { engine.view.delRelation(lString); }
      if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
    }
    
    @Override public void exitRenaming(DBMSGrammarParser.RenamingContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }

        ++counter;
      }
      
      String rString;
      String[] attributes = children.get(2).getText().split(",");
      ArrayList<String> attributeNames = new ArrayList();
      for(int i = 0; i < attributes.length; i++) {
        attributeNames.add(attributes[i]);
      }
      
      // check right operand
      if(isInView(children.get(4).getText()) == 1) { 
        rString = children.get(4).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      try {
        engine.renamingQuery(rString, attributeNames);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
    }
    
    @Override public void exitProjection(DBMSGrammarParser.ProjectionContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      String relName;
      String attributes = children.get(2).getText();
      String[] attributesArray = attributes.split(",");
      ArrayList<String> attributeNames = new ArrayList();
      
      for(int i =0; i < attributesArray.length; i++) {
        attributeNames.add(attributesArray[i]);
        System.out.println("attribute: " + attributesArray[i]);
      }
      
      // check right operand
      if(isInView(children.get(4).getText()) == 1) { 
        relName = children.get(4).getText();
      } else {
        relName = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(relName));
        engine.queue.delRelation(relName);
      }
      
      try {
        engine.projectionQuery(relName, attributeNames);
      } catch (Exception e) {
        System.out.println("Error in Query...\n");
        return;
      }
      if(canConvertInt(relName) == 1) { engine.view.delRelation(relName); }
    }
    
    @Override public void exitAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void enterAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void exitSelection(DBMSGrammarParser.SelectionContext ctx) { 
      // get the root node's direct children
      List<ParseTree> children = ctx.children;
      // instantiate parse tree values
      ParseTree relationTree = null;
      ParseTree conditionTree = null;	

      // iterate through children
      int counter = 0;
      for (ParseTree child : children) {
        //System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }
        
        ++counter;
      }
      
      String rString;
      // check right operand
      if(isInView(children.get(4).getText()) == 1) { 
        rString = children.get(4).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      try {
        engine.selectionQuery(rString, children.get(2));
        if(canConvertInt(rString) == 1) { engine.view.delRelation(rString); }
      } catch (Exception e) {
        System.out.println("Error in Command...\n");
        return;
      }
    }
    
    @Override public void exitCondition(DBMSGrammarParser.ConditionContext ctx) { 
        ParseTree conditionTree = ctx;
        
        System.out.println(conditionTree.getText());
        
        List<ParseTree> children = ctx.children;
        
        printLeafNodes(children);
        
        List<String> leafText = getLeafNodeList(children);
        
        for (int i = 0; i < leafText.size(); i++)
        {
            System.out.println(leafText.get(i));
        }
        
    }
    
    public static void printLeafNodes (List<ParseTree> toIterate) {
        List<ParseTree> recursChild = null;
       
        for (ParseTree child:toIterate){
             if (child.getChildCount() == 0) {System.out.println(child.getText()); }
            
            if (child.getChildCount() > 0) {
                recursChild = getChildList(child);
                printLeafNodes(recursChild);
            }
             
        }
    }
    public static List<String> getLeafNodeList (List<ParseTree> toIterate) {
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
}
