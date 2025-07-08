package com.example.renart_project.service;


import com.example.renart_project.dto.ProductResponse;
import com.example.renart_project.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final String GOLD_API_URL = "https://www.goldapi.io/api/XAU/USD";
    private static final String GOLD_API_KEY =  "goldapi-bf4kbwsmcs2gnuw-io";

    public List<ProductResponse> getAllProducts(){
        List<Product> products = loadProductsFromJson();
        double goldPrice= getGoldPrice();

        return products.stream()
                .map(p -> ProductResponse.builder()
                        .name(p.getName())
                        .weight(p.getWeight())
                        .popularityOutOf5(Math.round(p.getPopularityScore() * 5.0 * 10.0) / 10.0)
                        .price(Math.round((p.getPopularityScore()+1) * p.getWeight() * goldPrice * 100.0) / 100.0)
                                .imageUrl(p.getImages().get("yellow")) // default color
                        .images(p.getImages())
                                .build())
                                .collect(Collectors.toList());
    }

    private List<Product> loadProductsFromJson(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("products.json")){
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<List<Product>>() {});
        } catch (Exception e){
            throw new RuntimeException("Failed to load products.json", e);
        }
    }

    private double getGoldPrice(){
        try{
            URL url = new URL(GOLD_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("x-access-token", GOLD_API_KEY);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestMethod("GET");

                ObjectMapper mapper = new ObjectMapper();
                var node = mapper.readTree(connection.getInputStream());
                return node.get("price").asDouble();    // prica as USD
        } catch (Exception e){
            return 100.0; //fallback price
        }
    }
}
