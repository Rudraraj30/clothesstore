package com.ecommerce.clothesstore.controller;

import com.ecommerce.clothesstore.model.BrandResponse;
import com.ecommerce.clothesstore.model.ProductResponse;
import com.ecommerce.clothesstore.model.TypeResponse;
import com.ecommerce.clothesstore.service.BrandService;
import com.ecommerce.clothesstore.service.ProductService;
import com.ecommerce.clothesstore.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BrandService brandService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer productId)
    {
        ProductResponse productResponse=productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name = "keyword",required = false) String keyword,
            @RequestParam(name = "brandId",required = false) Integer brandId,
            @RequestParam(name = "typeId",required = false) Integer typeId,
            @RequestParam(name = "sort",defaultValue = "name") String sort,
            @RequestParam(name = "order",defaultValue = "asc") String order
            )
    {
        //Convert order to sort direction
        Sort.Direction direction=order.equalsIgnoreCase("desc")? Sort.Direction.DESC :Sort.Direction.ASC;
        Sort sorting=Sort.by(direction,sort);
        Pageable pageable= PageRequest.of(page,size,sorting);
        Page<ProductResponse> productResponses=productService.getProducts(pageable,brandId,typeId,keyword);
        return new ResponseEntity<>(productResponses,HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands()
    {
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return new ResponseEntity<>(brandResponses,HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeResponse>> getTypes()
    {
        List<TypeResponse> typeResponses=typeService.getAllTypes();
        return new ResponseEntity<>(typeResponses,HttpStatus.OK);
    }
}
