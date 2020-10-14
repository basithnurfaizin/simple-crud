package com.nurfaizin.backend.service;
;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.CreateShoppingRequest;
import com.nurfaizin.backend.model.ListShoppingRequest;
import com.nurfaizin.backend.model.ShoppingResponse;

import java.util.List;

public interface ShoppingService {

    ShoppingResponse createShopping(CreateShoppingRequest request);

    ShoppingResponse get(Long id) throws NotFoundException;

    ShoppingResponse update(CreateShoppingRequest request, Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException, com.nurfaizin.backend.error.NotFoundException;

    List<ShoppingResponse> list(ListShoppingRequest request);
}
