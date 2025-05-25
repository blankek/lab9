package com.example.shoppinglist.service;

import com.example.shoppinglist.model.ShoppingItem;
import com.example.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    private final ShoppingItemRepository repository;

    public ShoppingService(ShoppingItemRepository repository) {
        this.repository = repository;
    }

    public List<ShoppingItem> getAllItems() {
        return repository.findAll();
    }

    public ShoppingItem addItem(String name) {
        return repository.save(new ShoppingItem(null, name, false));
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public ShoppingItem markPurchased(Long id) {
        ShoppingItem item = repository.findById(id).orElseThrow();
        item.setPurchased(true);
        return repository.save(item);
    }
}