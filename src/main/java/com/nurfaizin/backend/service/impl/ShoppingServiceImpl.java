package com.nurfaizin.backend.service.impl;

import com.nurfaizin.backend.entity.Shopping;
import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.repository.ShoppingRepository;
import com.nurfaizin.backend.service.ShoppingService;
import com.nurfaizin.backend.model.request.CreateShoppingRequest;
import com.nurfaizin.backend.model.request.ListRequest;
import com.nurfaizin.backend.model.response.ShoppingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    protected ShoppingRepository repository;

    @Override
    public ShoppingResponse createShopping(CreateShoppingRequest request) {
        Shopping shopping = new Shopping();
        shopping.setName(request.getName());
        shopping.setCreatedDate(new Date());
        repository.save(shopping);

        return convertShoppingToResponse(shopping);
    }

    @Override
    public ShoppingResponse get(Long id) throws NotFoundException {
        Shopping shopping = findShoppingByIdOrThrowNotFound(id);

        return convertShoppingToResponse(shopping);
    }

    @Override
    public ShoppingResponse update(CreateShoppingRequest request, Long id) throws NotFoundException {
        Shopping shopping = findShoppingByIdOrThrowNotFound(id);
        shopping.setCreatedDate(new Date());
        shopping.setName(request.getName());
        repository.save(shopping);
        return convertShoppingToResponse(shopping);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Shopping shopping = findShoppingByIdOrThrowNotFound(id);
        repository.delete(shopping);
    }

    @Override
    public List<ShoppingResponse> list(ListRequest request) {
         Page<Shopping> page = repository.findAll(PageRequest.of(request.getPage(), request.getSize()));
         List<Shopping> shoppingList = page.get().collect(Collectors.toList());
         List<ShoppingResponse> shoppingResponses = new ArrayList<>();
         for(Shopping shopping: shoppingList) {
             ShoppingResponse response = convertShoppingToResponse(shopping);
             shoppingResponses.add(response);
         }
        return shoppingResponses;
    }

    private Shopping findShoppingByIdOrThrowNotFound(Long id) throws NotFoundException {
        Optional<Shopping> shopping = repository.findById(id);
        if(shopping.isPresent()) {
            return shopping.get();
        }
        throw new NotFoundException();
    }

    private ShoppingResponse convertShoppingToResponse(Shopping shopping) {
        return new ShoppingResponse(
                shopping.getCreatedDate(),
                shopping.getName(),
                shopping.getId()
        );
    }
}
