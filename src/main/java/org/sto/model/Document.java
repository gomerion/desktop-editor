package org.sto.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter 
@AllArgsConstructor 
public class Document implements Block {

    private final Metadata metadata;
    private final List<Block> blocks;
    
}