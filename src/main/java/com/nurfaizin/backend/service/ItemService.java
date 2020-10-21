package com.nurfaizin.backend.service;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.ItemUpdateRequest;
import com.nurfaizin.backend.model.response.ItemResponse;

public interface ItemService {

    ItemResponse createItem(ItemUpdateRequest request);

    ItemResponse updateItem(Long id, ItemUpdateRequest request) throws NotFoundException;
}
