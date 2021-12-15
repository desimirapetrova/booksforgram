package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.User;
import com.example.booksforgram.model.entity.enums.GenderEnum;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import com.example.booksforgram.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmailIgnoreCase(String email);


    Set<Role> findByRoles(UserRoleEnum roleEnum);

    GenderEnum findByGender(GenderEnum gender);

}
