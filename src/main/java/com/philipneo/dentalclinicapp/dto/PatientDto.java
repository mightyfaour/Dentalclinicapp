package com.philipneo.dentalclinicapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientDTO {
    private String name;
    private String address;
    private String email;
    private String phone;
    private Date dob;

}
