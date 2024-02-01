package com.api.ordermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.ordermanager.dto.OrderDto;
import com.api.ordermanager.mapper.OrderMapper;
import com.api.ordermanager.model.Order;
import com.api.ordermanager.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public List<Order> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Order create(OrderDto orderDto) {

		try {
			Order order = OrderMapper.mapToEntity(orderDto);
			return repository.save(order);
		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

		return null;

	}

	public void update(Long id, OrderDto orderDto) throws Exception {

		Order order = repository.findById(id).orElseThrow(() -> new Exception("Order not found"));

		Order orderToUpdate = OrderMapper.mapToEntity(orderDto);
		orderToUpdate.setId(order.getId());
		repository.save(orderToUpdate);

	}

	@Transactional
	public void delete(Long orderId) {
		try {
			Optional<Order> order = repository.findById(orderId);

			if (order.isPresent()) {
				repository.delete(order.get());
			}

		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

	}
}
