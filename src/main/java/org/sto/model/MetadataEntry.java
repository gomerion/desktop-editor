package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor  
public class MetadataEntry {

    private final String key;
    private final String value;
    
}