package com.nurfaizin.backend.controller;

import com.nurfaizin.backend.error.NotFoundException;
import com.nurfaizin.backend.service.ShoppingService;
import com.nurfaizin.backend.model.request.CreateShoppingRequest;
import com.nurfaizin.backend.model.request.ListShoppingRequest;
import com.nurfaizin.backend.model.response.ShoppingResponse;
import com.nurfaizin.backend.model.response.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ShoppingController {

    protected ShoppingService service;

    @Autowired
    public ShoppingController(ShoppingService shoppingService){
        this.service = shoppingService;
    }


    @PostMapping(value = "/api/shopping",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<ShoppingResponse> createShopping(@RequestBody CreateShoppingRequest request){
        ShoppingResponse response = service.createShopping(request);
        return  new WebResponse<>(200, "success", response);
    }

    @PutMapping(value = "/api/shopping/{id}",
            produces = "application/json",
            consumes = "application/json")
    public WebResponse<ShoppingResponse> updateShopping(@RequestBody CreateShoppingRequest request,
                                                        @PathVariable Long id) throws NotFoundException, javassist.NotFoundException {
        ShoppingResponse response = service.update(request, id);
        return  new WebResponse<>(200, "success", response);

    }

    @GetMapping(value = "/api/shopping/{id}")
    public WebResponse<ShoppingResponse> getShopping(@PathVariable Long id) throws NotFoundException {
        ShoppingResponse response = service.get(id);
        return  new WebResponse<>(200, "success", response);
    }

    @DeleteMapping(value = "/api/shopping/{id}")
    public WebResponse<String> deleteShopping(@PathVariable Long id) throws NotFoundException {
        service.delete(id);
        return new WebResponse<>(200, "success", "deleted");
    }

    @GetMapping(
            value = "/api/shopping",
            produces = "application/json"
    )
    public WebResponse<List<ShoppingResponse>> listShopping(@RequestParam(value = "size", defaultValue = "10") Integer size,
                                                            @RequestParam(value = "page", defaultValue = "0") Integer page){
        ListShoppingRequest shoppingRequest = new ListShoppingRequest(page, size);
        List<ShoppingResponse> responses = service.list(shoppingRequest);
        return new WebResponse<>( 200, "success", responses, page, size);
    }
}
