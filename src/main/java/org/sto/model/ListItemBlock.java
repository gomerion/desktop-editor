package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter 
@AllArgsConstructor 
public class ListItemBlock implements Block {
    private final ListType type;
    private final String marker;
    private final String text;
}