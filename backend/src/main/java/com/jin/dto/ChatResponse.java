package com.jin.dto;

import com.jin.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatResponse {
    private String reply;
    private String sentiment; // positive | neutral | negative
    private List<Product> recommendations;
}
