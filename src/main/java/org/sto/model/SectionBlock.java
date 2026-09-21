package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
 
@ToString 
@Getter 
@AllArgsConstructor 
public class SectionBlock implements Block {

    private final String title;
    
}