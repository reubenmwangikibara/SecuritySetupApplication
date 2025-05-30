package com.Login.Security.Repository;

import com.Login.Security.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserEntity> findByEmail (String email);
}
