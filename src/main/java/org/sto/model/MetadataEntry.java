package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter 
@AllArgsConstructor  
public class MetadataEntry {

    private final String key;
    private final String value;
    
}