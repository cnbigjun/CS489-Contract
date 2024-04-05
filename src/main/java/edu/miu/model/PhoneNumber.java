package edu.miu.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PhoneNumber {
    private String number;
    private String label;



    public PhoneNumber() {}


}