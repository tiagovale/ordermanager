package com.api.ordermanager.mapper;

import java.util.Objects;

import com.api.ordermanager.dto.ItemDto;
import com.api.ordermanager.model.Item;

public class ItemMapper {

	public static Item mapToEntity(ItemDto itemDto) {

		if (Objects.isNull(itemDto)) {
			return null;
		}

		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		return item;

	}
}
