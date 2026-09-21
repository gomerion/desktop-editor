package org.sto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString 
@Getter
@Builder 
@AllArgsConstructor
public class Metadata {

    private final String documentType;
    private String university;
    private String faculty;
    private String department;
    private String topic;
    private String student;
    private String supervisor;
    private String year;

}
