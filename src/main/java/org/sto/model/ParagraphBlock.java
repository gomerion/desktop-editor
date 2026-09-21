package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class ParagraphBlock implements Block {
    
    private final String text;

}