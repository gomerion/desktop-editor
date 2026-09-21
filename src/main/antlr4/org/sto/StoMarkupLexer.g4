lexer grammar StoMarkupLexer;

PAGE_BREAK: '---' [ \t]* '\r'? '\n';

SECTION: '@section';
DOCUMENT_TYPE: '@document_type';
UNIVERSITY: '@university';
FACULTY: '@faculty';
DEPARTMENT: '@department';
TOPIC: '@topic';
STUDENT: '@student';
GROUP: '@group';
SUPERVISOR: '@supervisor';
YEAR: '@year';

STRING: '"' (~["\r\n])* '"';
LPAREN: '(';
RPAREN: ')';

H4: '####';
H3: '###';
H2: '##';
H1: '#';

HYPHEN: '-';

NUMBER: [0-9]+;
LETTER: [a-zA-Zа-яА-ЯёЁ]+;
WORD: ~[\r\n#*()0-9 \t] ~[\r\n#*()]*;

WS: [ \t]+ -> skip;
NEWLINE: '\r'? '\n';
