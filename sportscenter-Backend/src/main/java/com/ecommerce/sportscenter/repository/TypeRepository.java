package com.ecommerce.sportscenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.sportscenter.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer>{
}