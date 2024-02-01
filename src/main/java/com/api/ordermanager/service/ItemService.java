package com.api.ordermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.ordermanager.dto.ItemDto;
import com.api.ordermanager.mapper.ItemMapper;
import com.api.ordermanager.model.Item;
import com.api.ordermanager.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private  ItemRepository repository;

	public List<Item> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Item create(ItemDto itemDto) {

		try {
			Item item = ItemMapper.mapToEntity(itemDto);
			return repository.save(item);
		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

		return null;

	}
	
	public void update(Long id, ItemDto itemDto) throws Exception {

		Item item = repository.findById(id).orElseThrow(() -> new Exception("User not found"));

		Item itemToUpdate = ItemMapper.mapToEntity(itemDto);
		itemToUpdate.setId(item.getId());
		repository.save(itemToUpdate);

	}

	@Transactional
	public void delete(Long itemId) {
		try {
			Optional<Item> item = repository.findById(itemId);

			if (item.isPresent()) {
				repository.delete(item.get());
			}

		} catch (Exception e) {
			System.out.println("Error message" + e.getMessage());
		}

	}
}
