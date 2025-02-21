package com.ecommerce.sportscenter.service;

import java.util.List;

import com.ecommerce.sportscenter.model.TypeResponse;

public interface TypeService {
    // all methods in an interface are implicitly public and abstract
    List<TypeResponse> getAllTypes();
}