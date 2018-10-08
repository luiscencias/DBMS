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
    
    @Override public void exitQuery(DBMSGrammarParser.QueryContext ctx) { }
    
    @Override public void exitDeletecmd(DBMSGrammarParser.DeletecmdContext ctx) { }
    
    @Override public void exitInsertcmd(DBMSGrammarParser.InsertcmdContext ctx) { }
    
    @Override public void exitUpdatecmd(DBMSGrammarParser.UpdatecmdContext ctx) { }
    
    @Override public void enterCreatecmd(DBMSGrammarParser.CreatecmdContext ctx) { }
    
    @Override public void exitShowcmd(DBMSGrammarParser.ShowcmdContext ctx) { }
    
    @Override public void enterExitcmd(DBMSGrammarParser.ExitcmdContext ctx) { System.exit(0);}
    
    @Override public void enterWritecmd(DBMSGrammarParser.WritecmdContext ctx) { }
    
    @Override public void exitClosecmd(DBMSGrammarParser.ClosecmdContext ctx) { }
    
    @Override public void enterClosecmd(DBMSGrammarParser.ClosecmdContext ctx) { }
    
    @Override public void enterOpencmd(DBMSGrammarParser.OpencmdContext ctx) { }
    
    @Override public void exitProduct(DBMSGrammarParser.ProductContext ctx) { }
    
    @Override public void exitDifference(DBMSGrammarParser.DifferenceContext ctx) { }
    
    @Override public void exitUnion(DBMSGrammarParser.UnionContext ctx) { }
    
    @Override public void exitRenaming(DBMSGrammarParser.RenamingContext ctx) { }
    
    @Override public void exitProjection(DBMSGrammarParser.ProjectionContext ctx) { }
    
    @Override public void exitAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void enterAtomicexpr(DBMSGrammarParser.AtomicexprContext ctx) { }
    
    @Override public void exitSelection(DBMSGrammarParser.SelectionContext ctx) { }
    
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
