package edu.miu.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailAddress {
    private String address;
    private String label;

    public EmailAddress() {
    }


}