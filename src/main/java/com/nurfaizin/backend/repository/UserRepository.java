package com.nurfaizin.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nurfaizin.backend.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
