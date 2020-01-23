/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class SQLListener extends DBMSGrammarBaseListener {
    
    @Override 
    public void enterQuery(DBMSGrammarParser.QueryContext ctx) {
        System.out.print(ctx.getText());
    }
    
private void printQuery(String DBMSGrammarSentence) {
    // Get our lexer
    DBMSGrammarLexer lexer = new DBMSGrammarLexer(new ANTLRInputStream(DBMSGrammarSentence));
 
    // Get a list of matched tokens
    CommonTokenStream tokens = new CommonTokenStream(lexer);
 
    // Pass the tokens to the parser
    DBMSGrammarParser parser = new DBMSGrammarParser(tokens);
 
    // Specify our entry point
    DBMSGrammarParser.QueryContext DBMSGrammarSentenceContext = parser.query();
 
    // Walk it and attach our listener
    ParseTreeWalker walker = new ParseTreeWalker();
    SQLListener listener = new SQLListener();
    walker.walk(listener, DBMSGrammarSentenceContext);
    }
}
