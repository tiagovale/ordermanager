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

import com.api.ordermanager.dto.StockMovementDto;
import com.api.ordermanager.model.StockMovement;
import com.api.ordermanager.service.StockMovementService;

@RestController
@RequestMapping("/movements")
public class StockMovementController {

	private StockMovementService service;

	@Autowired
	public StockMovementController(StockMovementService service) {
		this.service = service;
	}

	@GetMapping
	public List<StockMovement> getAll() {
		return service.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<StockMovement> create(@Valid @RequestBody StockMovementDto stockMovementDto) {
		return ResponseEntity.ok(service.create(stockMovementDto));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody StockMovementDto stockMovementDto) throws Exception {
		service.update(id, stockMovementDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
