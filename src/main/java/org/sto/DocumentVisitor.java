package org.sto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sto.model.*;

public class DocumentVisitor extends StoMarkupParserBaseVisitor<Object> {

    private static final Set<String> ALLOWED_SECTION_NAMES = Set.of(
        "РЕФЕРАТ",
        "СОДЕРЖАНИЕ",
        "ВВЕДЕНИЕ",
        "ЗАКЛЮЧЕНИЕ",
        "ОПРЕДЕЛЕНИЯ, ОБОЗНАЧЕНИЯ И СОКРАЩЕНИЯ",
        "СПИСОК ИСПОЛЬЗОВАННЫХ ИСТОЧНИКОВ",
        "ПРИЛОЖЕНИЕ"
    );
    private String currentSection = "DOCUMENT_START";

    private Set<String> usedSections = new HashSet<>();
    
    @Override
    public HeaderBlock visitHeaderLevel1(StoMarkupParser.HeaderLevel1Context ctx) {
        String title = ctx.textLine().getText();
        return new HeaderBlock(1, title);
    }

    @Override
    public HeaderBlock visitHeaderLevel2(StoMarkupParser.HeaderLevel2Context ctx) {
        String title = ctx.textLine().getText();
        return new HeaderBlock(2, title);
    }

    @Override
    public HeaderBlock visitHeaderLevel3(StoMarkupParser.HeaderLevel3Context ctx) {
        String title = ctx.textLine().getText();
        return new HeaderBlock(3, title);
    }

    @Override
    public HeaderBlock visitHeaderLevel4(StoMarkupParser.HeaderLevel4Context ctx) {
        String title = ctx.textLine().getText();
        return new HeaderBlock(4, title);
    }

    @Override 
    public SectionBlock visitSection(StoMarkupParser.SectionContext ctx) {
        String fullTitle = ctx.STRING().getText().replace("\"", "");
        String baseTitle = fullTitle.split(" ")[0];

        if (!ALLOWED_SECTION_NAMES.contains(baseTitle) && !ALLOWED_SECTION_NAMES.contains(fullTitle)) {
            throw new RuntimeException(
                "Ошибка СТО (строка " + ctx.getStart().getLine() + "): " +
                "Недопустимый структурный элемент '" + fullTitle + "'. " +
                "Разрешены только: " + ALLOWED_SECTION_NAMES
            );
        }

        boolean isApp = baseTitle.equals("ПРИЛОЖЕНИЕ");
        if (!isApp && usedSections.contains(fullTitle)) {
            throw new RuntimeException(
                "Ошибка СТО (строка " + ctx.getStart().getLine() + "): " +
                "Структурный элемент '" + fullTitle + "' уже встречался в документе!"
            );
        }

        usedSections.add(fullTitle);
        currentSection = fullTitle;

        return new SectionBlock(fullTitle);
    }

    @Override
    public ParagraphBlock visitParagraph(StoMarkupParser.ParagraphContext ctx) {
        String paragraphText = ctx.textLine().getText();
        return new ParagraphBlock(paragraphText);
    }

    @Override
    public ListItemBlock visitHyphenItem(StoMarkupParser.HyphenItemContext ctx) {
        String text = ctx.textLine().getText();
        return new ListItemBlock(ListType.HYPHEN, "-", text);
    }

    @Override
    public ListItemBlock visitLetterItem(StoMarkupParser.LetterItemContext ctx) {
        Set<String> allowedLetterItems = Set.of("а", "б", "в", "г", "д", "е", "ж", "и", "к", "л", "м", "н", "п", "р", "с", "т", "у", "ф", "х");
        String letterChar = ctx.LETTER().getText();

        if (letterChar.length() != 1 || !allowedLetterItems.contains(letterChar)) {
            throw new RuntimeException(
                "Ошибка СТО (строка " + ctx.getStart().getLine() + "): " +
                "Недопустимый буквенный маркер '" + letterChar + "' в маркированном списке. " +
                "Разрешены только: " + allowedLetterItems
            );
        }

        String text = ctx.textLine().getText();
        return new ListItemBlock(ListType.LETTER, letterChar, text);
    }

    @Override
    public ListItemBlock visitNumberItem(StoMarkupParser.NumberItemContext ctx) {
        String numberStr = ctx.NUMBER().getText();
        String text = ctx.textLine().getText();
        return new ListItemBlock(ListType.NUMBER, numberStr, text);
    }

    @Override
    public ListItemBlock visitListItemBlock(StoMarkupParser.ListItemBlockContext ctx) {
        return (ListItemBlock) visit(ctx.listItem());
    }

    @Override
    public MetadataEntry visitMetadata(StoMarkupParser.MetadataContext ctx) {
        String keyToken = ctx.key().getText(); 

        String value = ctx.textLine().getText().replaceAll("^\"|\"$", "");
        if (value.isBlank()) {
            throw new RuntimeException(
                "Ошибка СТО (строка " + ctx.getStart().getLine() + "): " +
                "Пустое значение для ключа '" + keyToken + "' в метаданных документа."
            );
        }

        return new MetadataEntry(keyToken, value);
    }

    @Override
    public Document visitDocument(StoMarkupParser.DocumentContext ctx) {
        List<Block> blocks = new ArrayList<>();
        List<ListItemBlock> currentListItems = new ArrayList<>();

        Metadata metadata = getMetadataFromContext(ctx);

        for (StoMarkupParser.BlockContext bCtx : ctx.block()) {
            Object parsedBlock = visit(bCtx);

            if (parsedBlock instanceof ListItemBlock item) {
                currentListItems.add(item);
            } else {
                if (!currentListItems.isEmpty()) {
                    blocks.add(new ListBlock(new ArrayList<>(currentListItems)));
                    currentListItems.clear();
                }
                if (parsedBlock instanceof Block b) {
                    blocks.add(b);
                }
            }
        }

        if (!currentListItems.isEmpty()) {
            blocks.add(new ListBlock(currentListItems));
        }

        return new Document(metadata, blocks);
    }

    public Metadata getMetadataFromContext(StoMarkupParser.DocumentContext ctx) {
        String documentType = null;
        String university = null;
        String faculty = null;
        String department = null;
        String topic = null;
        String student = null;
        String supervisor = null;
        String year = null;

        for (StoMarkupParser.MetadataContext mCtx : ctx.metadata()) {
            MetadataEntry entry = visitMetadata(mCtx);
            
            switch (entry.getKey()) {
                case "@document_type" -> documentType = entry.getValue();
                case "@university"    -> university = entry.getValue();
                case "@faculty"       -> faculty = entry.getValue();
                case "@department"    -> department = entry.getValue();
                case "@topic"         -> topic = entry.getValue();
                case "@student"       -> student = entry.getValue();
                case "@supervisor"    -> supervisor = entry.getValue();
                case "@year"          -> year = entry.getValue();
            }
        }

        Metadata metadata = new Metadata(
            documentType,
            university,
            faculty,
            department,
            topic,
            student,
            supervisor,
            year
        );
        return metadata;
    }

}