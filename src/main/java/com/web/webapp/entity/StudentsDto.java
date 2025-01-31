package com.web.webapp.entity;

import jakarta.validation.constraints.*;

public class StudentsDto {
    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "class is required")
    private String className;

    @NotNull(message = "roll no is required")
    private Integer rollNo;

    private String profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getRollNo() {

        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        
        this.rollNo = rollNo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

}
