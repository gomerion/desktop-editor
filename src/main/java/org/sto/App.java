package org.sto;

import org.sto.model.Document;

public class App {
    public static void main(String[] args) {

        String input = 
        """
        #Header1
        Some text here.
        ##Header2
        More text here.
        ---
        @section("ВВЕДЕНИЕ")
        Крутое ввдение
        ---
        @section("СПИСОК ИСПОЛЬЗОВАННЫХ ИСТОЧНИКОВ")
        Список источников
        """;

        StoDocumentParser parser = new StoDocumentParser();
        Document document = parser.parse(input);
        
        for (var block : document.getBlocks()) {
            System.out.println(block.toString());
        }

    }
}
