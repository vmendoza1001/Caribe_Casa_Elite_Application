package org.java.caribecasa_elite.repository;

import org.java.caribecasa_elite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
