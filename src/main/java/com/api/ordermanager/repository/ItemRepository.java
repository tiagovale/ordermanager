package com.api.ordermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ordermanager.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>  {

}
