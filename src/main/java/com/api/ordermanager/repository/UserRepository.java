package com.api.ordermanager.repository;

import org.springframework.stereotype.Repository;

import com.api.ordermanager.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}
