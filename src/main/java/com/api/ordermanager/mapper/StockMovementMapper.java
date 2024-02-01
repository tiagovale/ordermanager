package com.api.ordermanager.mapper;

import java.util.Objects;

import com.api.ordermanager.dto.StockMovementDto;
import com.api.ordermanager.model.Item;
import com.api.ordermanager.model.StockMovement;

public class StockMovementMapper {

	public static StockMovement mapToEntity(StockMovementDto stockMovementDto) {

		if (Objects.isNull(stockMovementDto)) {
			return null;
		}

		StockMovement stockMovement = new StockMovement();
		stockMovement.setId(stockMovementDto.getId());
		stockMovement.setCreationDate(stockMovementDto.getCreationDate());
		stockMovement.setQuantity(stockMovementDto.getQuantity());

		Item item = new Item();
		item.setId(stockMovementDto.getItem().getId());
		item.setName(stockMovementDto.getItem().getName());

		stockMovement.setItem(item);

		return stockMovement;

	}
}
