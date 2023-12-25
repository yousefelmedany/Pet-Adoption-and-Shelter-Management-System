package com.example.pets.repository;

import com.example.pets.entity.Role;
import com.example.pets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@Param("email") String email);
    boolean existsByUsername(@Param("username") String username);
    Optional<User> findByUsername(@Param("username") String username);
    Optional<User> findByEmail(@Param("email") String email);
    Collection<? extends User> findAllByRoles(Set<Role> roles);

    Optional<User> findByVerificationCode(String code);

    @Query("SELECT new User(u.Id, u.company_name, u.username, u.password, u.email, u.phone_number, u.country, u.address, u.department, u.roles, u.isEnabled, u.isCredentialsNonExpired, u.isAccountNonLocked, u.isAccountNonExpired, u.verificationCode) FROM User u")
    List<User> findAllExceptImg();
}
