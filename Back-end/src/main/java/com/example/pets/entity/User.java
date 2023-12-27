package com.example.pets.entity;

import javax.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_table",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})

@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column
    private String email;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles" ,
            joinColumns = @JoinColumn(name = "email"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @OrderColumn(name = "Id")
    @Column
    private Set<Role> roles = new HashSet<>();
    @Column
    private boolean isEnabled = false;
    @Column
    private Long personId;

}
