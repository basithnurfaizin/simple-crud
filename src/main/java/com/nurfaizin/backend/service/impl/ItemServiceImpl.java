package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.Item;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ItemUpdateRequest;
import com.nurfaizin.backend.model.response.ItemResponse;
import com.nurfaizin.backend.repository.ItemRepository;
import com.nurfaizin.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public ItemResponse createItem(ItemUpdateRequest request) {
        Item item = new Item();
        item.setItemName(request.getItemName());
        item.setIsDone(request.getIsCompleted());
        return new ItemResponse(item.getId(), item.getItemName(), item.getIsDone());
    }

    @Override
    public ItemResponse updateItem(Long id, ItemUpdateRequest request) throws NotFoundException {
        Item item = findItemById(id);
        item.setItemName(request.getItemName());
        item.setIsDone(request.getIsCompleted());
        repository.save(item);
        return new ItemResponse(item.getId(), item.getItemName(), item.getIsDone());
    }

    private Item findItemById(Long id) throws NotFoundException {
        Optional<Item> item = repository.findById(id);
        if(item.isPresent()) {
            return item.get();
        }
        throw new NotFoundException();
    }
}
