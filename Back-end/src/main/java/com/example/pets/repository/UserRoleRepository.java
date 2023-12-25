package com.example.pets.repository;
import com.example.pets.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users_roles SET role_id = :newRoleId WHERE user_id = :userId", nativeQuery = true)
    void updateUserRoleInUsersRolesTable(int userId, int newRoleId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users_roles WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(int userId);
}