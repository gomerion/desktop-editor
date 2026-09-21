package org.sto.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter 
@AllArgsConstructor  
public class ListBlock implements Block {

    private final List<ListItemBlock> items;

}