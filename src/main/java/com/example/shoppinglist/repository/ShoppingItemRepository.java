package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
}
