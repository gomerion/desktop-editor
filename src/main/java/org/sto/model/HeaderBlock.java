package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class HeaderBlock implements Block {

    private final int level;
    private final String title;
    
}