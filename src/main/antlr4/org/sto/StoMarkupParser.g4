parser grammar StoMarkupParser;

options {
    tokenVocab = StoMarkupLexer;
}

document
    : metadata* block* EOF
    ;

metadata
    : key LPAREN textLine RPAREN NEWLINE
    ;

key
    : DOCUMENT_TYPE
    | UNIVERSITY
    | FACULTY
    | DEPARTMENT
    | TOPIC
    | STUDENT
    | GROUP
    | SUPERVISOR
    | YEAR
    ;

block
    : section   #SectionBlock
    | header    #HeaderBlock
    | listItem  #ListItemBlock
    | paragraph #ParagraphBlock
    ;

textLine
    : (WORD | LETTER | NUMBER)+
    ;

section
    : PAGE_BREAK SECTION LPAREN STRING RPAREN NEWLINE
    ;

header
    : H1 textLine NEWLINE   #HeaderLevel1
    | H2 textLine NEWLINE   #HeaderLevel2
    | H3 textLine NEWLINE   #HeaderLevel3
    | H4 textLine NEWLINE   #HeaderLevel4
    ;

listItem
    : HYPHEN textLine NEWLINE #HyphenItem
    | LETTER RPAREN textLine NEWLINE  #LetterItem
    | NUMBER RPAREN textLine NEWLINE  #NumberItem
    ;

paragraph
    : textLine NEWLINE+
    ;

