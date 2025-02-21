package com.ecommerce.sportscenter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.sportscenter.entity.Type;
import com.ecommerce.sportscenter.model.TypeResponse;
import com.ecommerce.sportscenter.repository.TypeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeResponse> getAllTypes() {
        log.info("Fetching all types"); 
        List<TypeResponse> typeResponses = typeRepository.findAll().stream()
                        .map(this::convertToTypeResponse)
                        .collect(Collectors.toList());
        log.info("Fetched all types");
        return typeResponses;
    }

    private TypeResponse convertToTypeResponse(Type type){
        return TypeResponse.builder()
                        .id(type.getId())
                        .name(type.getName())
                        .products(type.getProducts())
                        .build();
    }
    
}
