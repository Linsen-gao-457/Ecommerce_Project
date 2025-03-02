package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Type;
import com.ecommerce.sportscenter.model.TypeResponse;
import com.ecommerce.sportscenter.repository.TypeRepository;
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
class TypeServiceImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeServiceImpl typeService;

    private Type type1, type2;

    @BeforeEach
    void setUp() {
        type1 = new Type(1, "type1", null);
        type2 = new Type(2, "type2", null);
    }

    @Test
    void testGetAllTypes() {
        when(typeRepository.findAll()).thenReturn(Arrays.asList(type1, type2));

        List<TypeResponse> result = typeService.getAllTypes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("type1", result.get(0).getName());
        assertEquals("type2", result.get(1).getName());

        verify(typeRepository, times(1)).findAll();
    }
}