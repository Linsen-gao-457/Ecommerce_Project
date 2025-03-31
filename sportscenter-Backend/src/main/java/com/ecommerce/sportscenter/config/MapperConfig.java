package com.ecommerce.sportscenter.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.sportscenter.mapper.OrderMapper;

@Configuration
public class MapperConfig {

    @Bean
    public OrderMapper OrderMapper(){
        return Mappers.getMapper(OrderMapper.class);
    }
}
