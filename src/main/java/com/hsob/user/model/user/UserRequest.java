package com.hsob.user.model.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsob.user.entity.user.*;
//import com.hsob.user.model.address.AddressValidateInterface;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "First letter of name must be capitalized")
    private String name;

    @NotBlank(message = "document cannot be empty")
    private String document;

    @NotBlank(message = "document_type cannot be empty")
    private String document_type;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Invalid E-mail format")
    private String email;

    @NotBlank(message = "phone cannot be empty")
    private String phone;

    @NotBlank(message = "gender cannot be empty")
    private String gender;

    @NotBlank(message = "gender_identity cannot be empty")
    private String gender_identity;

    private String social_name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String digest;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    private AddressRequest address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String authpass;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirm_password;
}
