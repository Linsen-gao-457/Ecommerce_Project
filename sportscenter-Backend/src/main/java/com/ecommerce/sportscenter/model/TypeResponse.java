package com.ecommerce.sportscenter.model;

import java.util.List;

import com.ecommerce.sportscenter.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeResponse {
    private Integer id;
    private String name;
    private List<Product> products;
}
