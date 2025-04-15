package com.backend.api_pizzaria.domain.products.controller;

import com.backend.api_pizzaria.domain.products.dtos.ItemResponseDTO;
import com.backend.api_pizzaria.domain.products.dtos.ItemRequestDTO;
import com.backend.api_pizzaria.domain.products.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    ResponseEntity<ItemResponseDTO> createItem(@RequestBody @Valid ItemRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(request));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
