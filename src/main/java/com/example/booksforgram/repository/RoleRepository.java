package com.example.booksforgram.repository;

import com.example.booksforgram.model.entity.Role;
import com.example.booksforgram.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(UserRoleEnum admin);
}
