/******************************************************
 * A multiline Javadoclike comment about my grammar *
 ******************************************************/
grammar DBMSGrammar;

/* Compiling grammar:
$ antlr4 DBMSGrammar.g4
$ javac DBMSGrammar*.java -cp /usr/local/lib/antlr-4.7.1-complete.jar
$ echo "query" | grun DBMSGrammar program -tree
*/

// Monoline comment about a parser rule

/* 
 A multiline Javalike comment
 */
query : relationname '<-' expr ';' ;

relationname : identifier ;

identifier : alpha (alpha | digit)* ;

alpha : ALPHA ;

digit : DIGIT ;

expr:
    ( atomicexpr
    | selection
    | projection
    | renaming
    | union
    | difference
    | product
    ) ;

atomicexpr : relationname | '('expr')' ;

selection : 'select' '(' condition')' atomicexpr ;

condition : conjunction ('||' conjunction )* ;

conjunction : comparison ( '&&' comparison )* ;

comparison : operand op operand | '('conjunction ('||' conjunction )*')' ;

op : '==' | '!=' | '<' | '>' | '<=' | '>=' ;

operand : attributename | literal ;

attributename : identifier;

literal : '"' (alpha | digit)* '"' | (digit)* ;

projection : 'project' '(' attributelist')' atomicexpr ;

attributelist : attributename (',' attributename )* ;

renaming : 'rename' '(' attributelist')' atomicexpr ;

union : atomicexpr '+' atomicexpr ;

difference : atomicexpr '-' atomicexpr ;

product : atomicexpr '*' atomicexpr ;

/* Don't Need (3 Person Team) */
// naturaljoin : atomicexpr & atomicexpr

command:
    (opencmd
    | closecmd
    | writecmd
    | exitcmd
    | showcmd
    | createcmd
    | updatecmd
    | insertcmd
    | deletecmd
    ) ';' ;

opencmd : 'OPEN' relationname ;

closecmd : 'CLOSE' relationname ;

writecmd : 'WRITE' relationname ;

exitcmd : 'EXIT' ;

showcmd : 'SHOW' atomicexpr ;

createcmd : 'CREATE TABLE' relationname '('typedattributelist') PRIMARY KEY' '('attributelist')' ;

updatecmd : 'UPDATE' relationname 'SET' attributename '=' literal (',' attributename '=' literal)* 'WHERE' condition ;

insertcmd : 'INSERT INTO' relationname 'VALUES FROM' '('literal (',' literal)*')' | 'INSERT INTO' relationname 'VALUES FROM RELATION' expr ;

deletecmd : 'DELETE FROM' relationname 'WHERE' condition ;

typedattributelist : attributename type (',' attributename type )* ;

type : ('VARCHAR' '('integer')') | ('INTEGER') ;

integer : digit (digit)* ;

program : (query | command)* ;

// some lexer rules
ALPHA : [a-zA-Z_]+ ; // match alphabets
DIGIT : [0-9]+ ; // match digits
WS : [ \t\r\n]+ -> skip ; // ignore whitespace

