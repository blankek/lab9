package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.ShoppingItem;
import com.example.shoppinglist.service.ShoppingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ShoppingController {
    private final ShoppingService service;

    public ShoppingController(ShoppingService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShoppingItem> getItems() {
        return service.getAllItems();
    }

    @PostMapping
    public ShoppingItem addItem(@RequestBody Map<String, String> payload) {
        return service.addItem(payload.get("name"));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
    }

    @PatchMapping("/{id}/purchase")
    public ShoppingItem purchaseItem(@PathVariable Long id) {
        return service.markPurchased(id);
    }
}
