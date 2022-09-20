package com.hsob.user.dto.user;

import com.hsob.documentdb.user.Address;
import com.hsob.documentdb.user.DocumentType;
import com.hsob.documentdb.user.Gender;
import com.hsob.documentdb.user.GenderIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private Address address;
}
