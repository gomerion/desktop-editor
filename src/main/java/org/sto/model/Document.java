package org.sto.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor 
public class Document implements Block {

    private final Metadata metadata;
    private final List<Block> blocks;
    
}