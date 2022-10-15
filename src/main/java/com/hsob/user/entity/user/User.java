package com.hsob.user.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hsob.user.entity.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max=100)
    private String name;
    @NotBlank
    @Size(min=11, max=14)
    private String document;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DocumentType document_type;
    @NotNull
    @Size(max=50)
    private String email;
    @Size(max=30)
    private String phone;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderIdentity gender_identity;
    @Size(max=100)
    private String social_name;
    @NotNull
    private String digest;
    @NotNull
    private String salt;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Address> addresses;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String authpass;


}