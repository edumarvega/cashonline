package com.broker.cashonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.broker.cashonline.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
