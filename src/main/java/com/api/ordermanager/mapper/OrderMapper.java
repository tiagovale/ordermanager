package com.api.ordermanager.mapper;

import java.util.Objects;

import com.api.ordermanager.dto.OrderDto;
import com.api.ordermanager.model.Item;
import com.api.ordermanager.model.Order;
import com.api.ordermanager.model.User;

public class OrderMapper {

	public static Order mapToEntity(OrderDto orderDto) {

		if (Objects.isNull(orderDto)) {
			return null;
		}
		Order order = new Order();
		order.setCreationDate(orderDto.getCreationDate());

		Item item = new Item();
		item.setId(orderDto.getItem().getId());
		item.setName(orderDto.getItem().getName());

		order.setQuantity(orderDto.getQuantity());

		User user = new User();
		user.setId(orderDto.getUser().getId());
		user.setName(orderDto.getUser().getName());
		user.setEmail(orderDto.getUser().getEmail());

		order.setItem(item);
		order.setUser(user);

		return order;

	}
}
