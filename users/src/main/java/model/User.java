package model;


import enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthdate;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles = new ArrayList<>();

    @ElementCollection
    @CollectionTable (name = "user_favourites", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "pair")
    private Set<Long> usersFavourites = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
