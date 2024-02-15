package com.api.ordermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.ordermanager.dto.StockMovementDto;
import com.api.ordermanager.mapper.StockMovementMapper;
import com.api.ordermanager.model.Item;
import com.api.ordermanager.model.StockMovement;
import com.api.ordermanager.repository.StockMovementRepository;

@Service
public class StockMovementService {

	@Autowired
	private StockMovementRepository repository;

	@Autowired
	private ItemService itemService;

	public List<StockMovement> getAll() {
		return repository.findAll();
	}

	@Transactional
	public StockMovement create(StockMovementDto stockMovementDto) {

		try {
			StockMovement stockMovement = StockMovementMapper.mapToEntity(stockMovementDto);
			return repository.save(stockMovement);
		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

		return null;

	}

	public void update(Long id, StockMovementDto stockMovementDto) throws Exception {

		StockMovement stockMovement = repository.findById(id)
				.orElseThrow(() -> new Exception("StockMovement not found"));

		StockMovement stockMovementToUpdate = StockMovementMapper.mapToEntity(stockMovementDto);
		stockMovementToUpdate.setId(stockMovement.getId());
		repository.save(stockMovementToUpdate);

	}

	@Transactional
	public void delete(Long stockMovementId) {
		try {
			Optional<StockMovement> stockMovement = repository.findById(stockMovementId);

			if (stockMovement.isPresent()) {
				repository.delete(stockMovement.get());
			}

		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

	}

	public boolean checkItemAndQuantity(Long itemId, Integer orderQuantity) throws Exception {

		Optional<Item> item = itemService.getById(itemId);

		if (!item.isPresent()) {
			throw new Exception("Item not found");
		}

		Optional<StockMovement> stockMovement = repository.findByItemId(item.get().getId());

		if (!stockMovement.isPresent()) {
			throw new Exception("Stock movement not found");
		}

		int stockQuantity = stockMovement.get().getQuantity();

		return orderQuantity <= stockQuantity;
	}

	public void updateQuantity(Long itemId, Integer orderQuantity) {
		Optional<Item> item = itemService.getById(itemId);
		Optional<StockMovement> stockMovement = repository.findByItemId(item.get().getId());
		
		int stockQuantity = stockMovement.get().getQuantity();
		
		if (orderQuantity <= stockQuantity) {
			stockMovement.get().setQuantity(stockQuantity - orderQuantity);
			repository.save(stockMovement.get());

		}

	}
}
