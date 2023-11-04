package com.eventec.eventec.repositories;

import com.eventec.eventec.models.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    //@Query(value = "SELECT * FROM user_items WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<UserItem> findUserByEmailAndPassword(String email, String password);

    Optional<UserItem> findUserByEmail(String email);
}
