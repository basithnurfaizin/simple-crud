package com.nurfaizin.backend.repository;

import com.nurfaizin.backend.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    Shopping findByNameAndCreatedDate(String name, Date createdDate);
}
