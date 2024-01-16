package com.ezorx.ecoom.dao;

import com.ezorx.ecoom.entity.Cart;
import com.ezorx.ecoom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUser(User user);
}
