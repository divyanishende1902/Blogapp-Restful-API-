package com.myblog1.myblog1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myblog1.myblog1.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long > {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}
