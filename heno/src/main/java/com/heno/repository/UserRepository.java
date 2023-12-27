package com.heno.repository;

import com.heno.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User entity repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * Finds a user by her login (username).
     *
     * @param username User login.
     * @return Optional containing the User if found, otherwise empty.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks whether the username exists in the database.
     *
     * @param username User login.
     * @return boolean indicating the existence of the username.
     */
    boolean existsByUsername(String username);
}
