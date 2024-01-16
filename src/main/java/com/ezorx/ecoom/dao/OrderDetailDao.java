package com.ezorx.ecoom.dao;


import com.ezorx.ecoom.entity.OrderDetail;
import com.ezorx.ecoom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {


    public List<OrderDetail> findByUser(User user);
    public List<OrderDetail> findByOrderStatus(String status);
}
