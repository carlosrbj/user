package com.hsob.user.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsob.user.entity.address.Address;
import com.hsob.user.entity.user.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String document;
    private DocumentType document_type;
    private String email;
    private String phone;
    private Gender gender;
    private GenderIdentity gender_identity;
    private String social_name;
    private String digest;
    private String salt;
    private AddressDto address;
    private String authpass;
}
