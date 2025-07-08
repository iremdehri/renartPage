package com.example.renart_project.dto;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ProductResponse {
    private String name;
    private double price;
    private double popularityOutOf5;
    private double weight;
    private String imageUrl;
    private Map<String, String> images;
}
