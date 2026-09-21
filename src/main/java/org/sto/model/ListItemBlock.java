package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class ListItemBlock implements Block {
    private final ListType type;
    private final String marker;
    private final String text;
}