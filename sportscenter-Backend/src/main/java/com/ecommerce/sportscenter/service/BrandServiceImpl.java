package com.ecommerce.sportscenter.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import org.springframework.stereotype.Service;

import com.ecommerce.sportscenter.entity.Brand;
import com.ecommerce.sportscenter.model.BrandResponse;
import com.ecommerce.sportscenter.repository.BrandRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    // dependency injection here
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        log.info("Fetching all brands");
        List<BrandResponse> brandResponses = brandRepository.findAll().stream()
                                                        .map(this::convertBrandToBrandResponse)
                                                        .collect(Collectors.toList());
        log.info("Fetched all brands");
        return brandResponses;
    }

    private BrandResponse convertBrandToBrandResponse(Brand brand){
        return BrandResponse.builder()
                    .id(brand.getId())
                    .name(brand.getName())
                    .products(brand.getProducts())
                    .build();
    } 
}
