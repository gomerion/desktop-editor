package org.sto;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class App {
    public static void main(String[] args) {

        String input = 
        """
        #Header1
        Some text here.
        #Header2
        More text here.
        ---
        @section("ВВЕДЕНИЕ")
        Крутое ввдение
        ---
        @section("СПИСОК ИСПОЛЬЗОВАННЫХ ИСТОЧНИКОВ")
        Список источников
        """;

        CharStream charStream = CharStreams.fromString(input);
        StoMarkupLexer lexer = new StoMarkupLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        StoMarkupParser parser = new StoMarkupParser(tokens);
        ParseTree tree = parser.document();

        DocumentVisitor visitor = new DocumentVisitor();
        visitor.visit(tree);
        

    }
}
