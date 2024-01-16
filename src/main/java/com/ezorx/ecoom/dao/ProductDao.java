package com.ezorx.ecoom.dao;


import com.ezorx.ecoom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product , Integer> {
//    public List<Product> findAll(Pageable pageable);
//    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String key1 , String key2 , Pageable pageable);
}

