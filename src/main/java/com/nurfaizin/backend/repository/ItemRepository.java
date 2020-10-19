package com.nurfaizin.backend.repository;

import com.nurfaizin.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String name);
}
