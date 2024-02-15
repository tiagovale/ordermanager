package com.api.ordermanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.ordermanager.dto.OrderDto;
import com.api.ordermanager.model.Order;
import com.api.ordermanager.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService service;

	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}

	@GetMapping
	public List<Order> getAll() {
		return service.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity create(@Valid @RequestBody OrderDto orderDto) {
		try {

			return ResponseEntity.ok(service.create(orderDto));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error message: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody OrderDto orderDto) throws Exception {
		service.update(id, orderDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
