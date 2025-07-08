package com.example.renart_project.model;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Product {
    private String name;
    private double popularityScore;
    private double weight;
    private Map<String, String> images;  // yellow gold,white gold, rose gold
}
