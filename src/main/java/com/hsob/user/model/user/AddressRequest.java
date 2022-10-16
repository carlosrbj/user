package com.hsob.user.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private Long id;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String complement;
    private String street;
    private String number;
}
