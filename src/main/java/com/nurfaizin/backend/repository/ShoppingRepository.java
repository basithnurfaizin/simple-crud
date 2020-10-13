package com.nurfaizin.backend.repository;

import com.nurfaizin.backend.entity.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}
