grammar Mymx;

//skip
Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

complication_code: (function_def_unit | class_def_unit | var_def_unit| ';')*;
function_def_unit: (VOID | type) IDENTIFIER '(' parameter_list?')' suite;
class_def_unit: CLASS IDENTIFIER '{' (var_def_unit | function_def_unit | constructor_def_unit)* '}' ';';
constructor_def_unit: IDENTIFIER '(' parameter_list?')' suite;
var_def_unit: variable_list ';';

expression_list: expression (',' expression)*;
parameter_list: type IDENTIFIER (',' type IDENTIFIER)*;
variable_list: type variable_decl (',' variable_decl)*;
variable_decl: IDENTIFIER ('=' expression)? ;
suite: '{' statement* '}';

BOOL_LITERAL: TRUE | FALSE;
INTEGER_LITERAL: '0' | [1-9][0-9]*;
STRING_LITERAL: '"' (~["\\\r\n]| '\\' ["n\\])* '"';
NULL_LITERAL: NULL;

//keyword
INT:  'int';
BOOL: 'bool';
STRING: 'string';
NULL: 'null';
VOID: 'void';
TRUE: 'true';
FALSE: 'false';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
RETURN: 'return';
NEW: 'new';
CLASS: 'class';
THIS: 'this';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9_]*;

statement: suite                                                                                                #blockStat
         | var_def_unit                                                                                         #vardefStat
         | IF '(' expression ')' ifstatement = statement (ELSE elsestatement = statement)?                                                    #ifStat
         | WHILE '(' expression ')' statement                                                                   #whileStat
         | FOR '(' (initexpr = expression | initvar = variable_list)? ';' condition = expression? ';' incr = expression? ')' statement     #forStat
         | CONTINUE ';'                                                                                         #continueStat
         | BREAK ';'                                                                                            #breakStat
         | RETURN expression? ';'                                                                               #returnStat
         | expression ';'                                                                                       #exprStat
         | ';'                                                                                                  #emptyStat
         ;

expression: THIS                                                                           #atomExpr
          | (BOOL_LITERAL | INTEGER_LITERAL | STRING_LITERAL | NULL_LITERAL)               #atomExpr
          | IDENTIFIER                                                                     #atomExpr
          | expression op = ('++' | '--')                                                  #suffixExpr
          | expression '.' IDENTIFIER                                                      #memberExpr
          | expression '(' expression_list? ')'                                            #funcExpr
          | NEW newtype                                                                    #newExpr
          | identifier = expression '[' index = expression ']'                             #subarrayExpr
          | <assoc=right> op = ('++' | '--') expression                                    #prefixExpr
          | <assoc=right> op = ('+' | '-') expression                                      #prefixExpr
          | <assoc=right> op = ('!' | '~') expression                                      #prefixExpr
          | lhs = expression op = ('+' | '-' ) rhs = expression                            #binaryExpr
          | lhs = expression op = ('*' | '/' | '%') rhs = expression                       #binaryExpr
          | lhs = expression op = ('<<' | '>>') rhs = expression                           #binaryExpr
          | lhs = expression op = ('<' | '>' | '<=' | '>=' | '==' | '!=') rhs = expression #binaryExpr
          | lhs = expression op = ('&' | '^' | '|' | '&&' | '||') rhs = expression         #binaryExpr
          | <assoc=right> lhs = expression op = '=' rhs = expression                       #assignExpr
          | '(' expression ')'                                                             #atomExpr
          ;

type: type '[' ']' | INT | BOOL | STRING | IDENTIFIER;
newtype: (INT | BOOL | STRING | IDENTIFIER) ('[' expression ']')+ ('[' ']')+ ('[' expression ']')+  #errorNewtype
       | (INT | BOOL | STRING | IDENTIFIER) ('[' expression ']')+ ('[' ']')*                        #arrayNewtype
       | (INT | BOOL | STRING | IDENTIFIER) '(' ')'                                                 #classNewtype
       | (INT | BOOL | STRING | IDENTIFIER)                                                         #basicNewtype
       ;

//Token

SPACE: ' ';
COMMA: ',';
SEMICOLON: ';';
DOT: '.';

LEFT_PARENTNESS: '(';
RIGHT_PARENTNESS: ')';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';

LESS: '<';
LESS_EQUAL: '<=';
GREATER: '>';
GREATER_EQUAL: '>=';
EQUAL: '==';
NOT_EQUAL: '!=';
LEFT_SHIFT: '<<';
RIGHT_SHIFT: '>>';

ASSIGN: '=';

ADD_ADD: '++';
MINUS_MINUS: '--';

ADD:'+';
MINUS: '-';
MUL: '*';
DIV: '/';
MOD: '%';

AND_ARI: '&';
OR_ARI: '|';
AND_LOGIC: '&&';
OR_LOGIC: '||';
XOR_ARI: '^';
NOT_LOGIC: '!';
NOT_ARI: '~';


