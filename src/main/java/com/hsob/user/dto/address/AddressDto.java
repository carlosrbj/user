package com.hsob.user.dto.address;

import com.hsob.documentdb.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author carlos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String complement;
    private String street;
    private String number;
}
