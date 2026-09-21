package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter 
@AllArgsConstructor 
public class HeaderBlock implements Block {

    private final int level;
    private final String title;
    
}