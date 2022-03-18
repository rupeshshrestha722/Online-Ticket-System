package com.interview.project.onlineticketsystem_backend.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The user entity
 */
@Entity
@Table(name = "users")
@DiscriminatorValue("2")
@Getter
@Setter
@ToString(callSuper = true, exclude = { "password" })
public class User extends IdentifiableEntity {

    /**
     * First Name
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last Name
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email
     */
    @NotBlank
    @Email
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    /**
     * Username
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;

    /**
     * Password
     */
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    /**
     * Role
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    /**
     * Booking List
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Booking> bookingList;
}
