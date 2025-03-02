package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Brand;
import com.ecommerce.sportscenter.model.BrandResponse;
import com.ecommerce.sportscenter.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    private Brand brand1, brand2;

    @BeforeEach
    void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
        brand1 = new Brand(1, "brand1", null);
        brand2 = new Brand(2, "brand2", null);
    }

    @Test
    void testGetAllBrands() {
        when(brandRepository.findAll()).thenReturn(Arrays.asList(brand1, brand2));

        List<BrandResponse> brandResponses = brandService.getAllBrands();

        assertNotNull(brandResponses);
        assertEquals(2, brandResponses.size());
        assertEquals("brand1", brandResponses.get(0).getName());
        assertEquals("brand2", brandResponses.get(1).getName());

        verify(brandRepository, times(1)).findAll();
    }
}
