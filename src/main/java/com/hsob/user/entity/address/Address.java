package com.hsob.user.entity.address;

import com.hsob.user.entity.user.User;
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
@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max=100)
    private String city;
    @NotBlank
    @Size(max=2)
    private String state;
    @NotBlank
    @Size(max=30)
    private String country;
    @NotBlank
    @Size(max=20)
    private String zipcode;
    @NotBlank
    @Size(max=10)
    private String complement;
    @NotBlank
    @Size(max=100)
    private String street;
    @NotBlank
    @Size(max=10)
    private String number;
    @ManyToOne
    private User user;
}
