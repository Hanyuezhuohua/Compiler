grammar Mymx;

complication_code: (function_def_unit | class_def_unit | var_def_unit| ';')*;
function_def_unit: (VOID | type) IDENTIFIER '(' parameter_list?')' suite; // SUITE_TO_DO
class_def_unit: CLASS IDENTIFIER '{' (var_def_unit | function_def_unit | constructor_def_unit)* '}' ';';
constructor_def_unit: IDENTIFIER '(' parameter_list?')' suite;
var_def_unit: variable_list ';';

expression_list: expression (',' expression)*;
parameter_list: type IDENTIFIER (',' type IDENTIFIER)*;
variable_list: type variable_decl (',' variable_decl)*;
variable_decl: IDENTIFIER ('=' expression)? ;
suite: '{' statement* '}';

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
statement: suite
         | var_def_unit
         | IF '(' expression ')' statement (ELSE statement)?
         | WHILE '(' expression ')' statement
         | FOR '(' (expression | variable_list)? ';' expression? ';' expression? ')' statement
         | CONTINUE ';'
         | BREAK ';'
         | RETURN expression? ';'
         | expression? ';'
         ;

expression: THIS
          | BOOL_LITERAL | INTEGER_LITERAL | STRING_LITERAL | NULL_LITERAL
          | IDENTIFIER
          | expression ('++' | '--')
          | expression '.' IDENTIFIER
          | expression '(' expression_list? ')'
          | expression '[' expression ']'
          | <assoc=right> ('++' | '--') expression
          | <assoc=right> ('+' | '-') expression
          | <assoc=right> ('!' | '~') expression
          | expression ('+' | '-' ) expression
          | expression ('*' | '/' | '%') expression
          | expression ('<<' | '>>') expression
          | expression ('<' | '>' | '<=' | '>=' | '==' | '!=') expression
          | expression ('&' | '^' | '|' | '&&' | '||') expression
          | <assoc=right> expression '=' expression
          | '(' expression ')'
          | NEW newtype
          ;

type: type '[' ']' | INT | BOOL | STRING | IDENTIFIER;
newtype: INT | BOOL | STRING | IDENTIFIER
       | (INT | BOOL | STRING | IDENTIFIER) '(' ')'
       | (INT | BOOL | STRING | IDENTIFIER) ('[' expression ']')+ ('[' ']')*
       | (INT | BOOL | STRING | IDENTIFIER) ('[' expression ']')+ ('[' ']')+ ('[' expression ']')+;
//Token
BOOL_LITERAL: TRUE | FALSE;
INTEGER_LITERAL: '0' | [1-9][0-9]*;
STRING_LITERAL: '"' (~["\\\r\n]| '\\' ["n\\])* '"';
NULL_LITERAL: NULL;

//skip
WHITESPACE: [ \t\n\r]+ -> skip;
LINECOMMENT: '//' ~[\r\n]* -> skip;
BLOCKCOMMENT: '/*' .*? '*/' -> skip;

