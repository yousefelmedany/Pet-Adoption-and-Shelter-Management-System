package com.example.pets.entity;

import javax.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_table",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String company_name;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String phone_number;
    @Column
    private String country;
    @Column
    private String address;
    @Column
    private String department;
    @Column(length = 16777215)
    private String  img;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OrderColumn(name = "Id")
    @Column
    private Set<Role> roles = new HashSet<>();

    @Column
    private boolean isEnabled = false;

    @Column
    private boolean isCredentialsNonExpired= true;

    @Column
    private boolean isAccountNonLocked = true;
    @Column
    private boolean isAccountNonExpired= true;

    @Column(length = 64)
    private String verificationCode;


    public User(int id) {
        super();
        this.Id = id;
    }
    //make constructor with all properties except img
    public User(int id, String company_name, String username, String password, String email, String phone_number, String country, String address, String department, Set<Role> roles, boolean isEnabled, boolean isCredentialsNonExpired, boolean isAccountNonLocked, boolean isAccountNonExpired, String verificationCode) {
        super();
        Id = id;
        this.company_name = company_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.country = country;
        this.address = address;
        this.department = department;
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isAccountNonExpired = isAccountNonExpired;
        this.verificationCode = verificationCode;
    }
}
