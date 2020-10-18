package com.nurfaizin.backend.service;
;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.model.request.CreateShoppingRequest;
import com.nurfaizin.backend.model.request.ListShoppingRequest;
import com.nurfaizin.backend.model.response.ShoppingResponse;

import java.util.List;

public interface ShoppingService {

    ShoppingResponse createShopping(CreateShoppingRequest request);

    ShoppingResponse get(Long id) throws NotFoundException;

    ShoppingResponse update(CreateShoppingRequest request, Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException, com.nurfaizin.backend.error.NotFoundException;

    List<ShoppingResponse> list(ListShoppingRequest request);
}
