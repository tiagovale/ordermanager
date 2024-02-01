package com.api.ordermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ordermanager.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {

}
