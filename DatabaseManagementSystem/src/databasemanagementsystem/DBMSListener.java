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
    public String relationName;
    
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
      
      Relation relOne = new Relation("One", samples, samplePrimaries);
      Relation relTwo = new Relation("Two", samples, samplePrimaries);
      Relation relThree = new Relation("Three", samples, samplePrimaries);
      
      Literal[] sampleOne = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
      Literal[] sampleTwo = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
      Literal[] sampleThree = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
      Literal[] sampleFour = {new Literal(new Attribute("big",10),"dog"), new Literal(new Attribute("large",10),"canine")};
      Literal[] sampleFive = {new Literal(new Attribute("big",10),"bee"), new Literal(new Attribute("large",10),"buzzer")}; 
      Literal[] sampleSix = {new Literal(new Attribute("big",10),"boss"), new Literal(new Attribute("large",10),"boy")};
      Literal[] sampleSeven = {new Literal(new Attribute("big",10),"ball"), new Literal(new Attribute("large",10),"sphere")};
      
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
        // skip child nodes with no children (e.g., CAPITALIZE, WHERE)
        if (child.getChildCount() == 0) { continue; }

        // get relation name's parse tree
        if (counter == 0) { relationTree = child; }

        // get condition's parse tree
        else if (counter == 1) { conditionTree = child; }

        ++counter;
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
      }
      
      String rString;
      
      // right operand
      if(isInView(children.get(2).getText()) == 1) { 
        rString = children.get(2).getText();
      } else {
        rString = engine.queue.relations.get(engine.queue.relations.size()-1).name;
        engine.view.addRelation(engine.queue.getRelation(rString));
        engine.queue.delRelation(rString);
      }
      
      engine.assignmentCommand(children.get(0).getText(), rString);
      
      System.out.println("Exception cuaght?: " + canConvertInt(rString) + "\n");
      if(canConvertInt(rString) == 1) {
        engine.view.delRelation(rString);
      }
    }
    
    @Override public void exitDeletecmd(DBMSGrammarParser.DeletecmdContext ctx) { }
    
    @Override public void exitInsertcmd(DBMSGrammarParser.InsertcmdContext ctx) { }
    
    @Override public void exitUpdatecmd(DBMSGrammarParser.UpdatecmdContext ctx) { }
    
    @Override public void enterCreatecmd(DBMSGrammarParser.CreatecmdContext ctx) { }
    
    @Override public void exitShowcmd(DBMSGrammarParser.ShowcmdContext ctx) { 
      ParseTree conditionTree = ctx;
      
      List<ParseTree> children = ctx.children;
      
      List<String> leafText = getLeafNodeList(children);
      
      if(!(leafText.get(1).equals("all"))) {
        engine.showCommand(leafText.get(1));
      } else {
        engine.view.testPrint();
        engine.queue.testPrint();
      }
      
    }
    
    @Override public void enterExitcmd(DBMSGrammarParser.ExitcmdContext ctx) { System.exit(0);}
    
    @Override public void enterWritecmd(DBMSGrammarParser.WritecmdContext ctx) { }
    
    @Override public void exitClosecmd(DBMSGrammarParser.ClosecmdContext ctx) { }
    
    @Override public void enterClosecmd(DBMSGrammarParser.ClosecmdContext ctx) { }
    
    @Override public void enterOpencmd(DBMSGrammarParser.OpencmdContext ctx) { }
    
    @Override public void exitProduct(DBMSGrammarParser.ProductContext ctx) { 
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
      
      engine.productQuery(lString, rString);
      engine.view.delRelation(lString);
      engine.view.delRelation(rString);
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
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
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
      
      engine.differenceQuery(lString, rString);
      engine.view.delRelation(lString);
      engine.view.delRelation(rString);
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
        System.out.println("Child[" + counter + "]: " + child.getText() + "\n");
        
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
      
      engine.unionQuery(lString, rString);
      engine.view.delRelation(lString);
      engine.view.delRelation(rString);
    }
    
    @Override public void exitRenaming(DBMSGrammarParser.RenamingContext ctx) { }
    
    @Override public void exitProjection(DBMSGrammarParser.ProjectionContext ctx) { }
    
    @Override public void exitAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void enterAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void exitSelection(DBMSGrammarParser.SelectionContext ctx) { 

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
