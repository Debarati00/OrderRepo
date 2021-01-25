package com.pack.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pack.order.entity.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, String>{
	
}
