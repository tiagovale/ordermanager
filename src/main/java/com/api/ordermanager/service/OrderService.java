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
	

	@Autowired
	private StockMovementService stockMovementService;

	public List<Order> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Order create(OrderDto orderDto) throws Exception {

		validateStockQuantity(orderDto);
		updateStock(orderDto);

		Order order = OrderMapper.mapToEntity(orderDto);
		return repository.save(order);

	}

	private void updateStock(OrderDto orderDto) throws Exception {
	
		stockMovementService.updateQuantity(orderDto);
	}

	private void validateStockQuantity(OrderDto orderDto) throws Exception {
		boolean isStockGreaterThanOrder = stockMovementService.checkItemAndQuantity(orderDto.getItem().getId(),
				orderDto.getQuantity());
		if (!isStockGreaterThanOrder) {
			throw new Exception("The order quantity is greater than the stock quantity");
		}
	}

	public void update(Long id, OrderDto orderDto) throws Exception {

		Optional<Order> order = repository.findById(id);

		Order orderToUpdate = OrderMapper.mapToEntity(orderDto);
		orderToUpdate.setId(order.get().getId());
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
