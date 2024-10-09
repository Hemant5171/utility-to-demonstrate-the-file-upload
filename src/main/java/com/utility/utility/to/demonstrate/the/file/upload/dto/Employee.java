package com.utility.utility.to.demonstrate.the.file.upload.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class Employee {

    private String empName;
    private String machine;
    private LocalDate accessEndDate;
}
