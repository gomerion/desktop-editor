package org.sto;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.sto.model.Document;

public class StoDocumentParser {

    public Document parse(String input) {
        CharStream charStream = CharStreams.fromString(input);
        StoMarkupLexer lexer = new StoMarkupLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StoMarkupParser parser = new StoMarkupParser(tokens);
        ParseTree tree = parser.document();

        DocumentVisitor visitor = new DocumentVisitor();
        Document document = (Document) visitor.visit(tree);
        
        return document;
    }
    
}
