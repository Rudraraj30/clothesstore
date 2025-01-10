package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.Product;
import com.ecommerce.clothesstore.model.ProductResponse;
import com.ecommerce.clothesstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse getProductById(Integer productId) {
        Product product=productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product doesn't exist"));
        return convertToProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable,Integer brandId,Integer typeId,String keyword) {
        Specification<Product> spec = Specification.where(null);
        if (brandId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand").get("id"), brandId));
        }
        if(typeId!=null)
        {
            spec=spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type").get("id"),typeId));
        }
        if(keyword!=null)
        {
            System.out.println(keyword);
            spec=spec.and(((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+keyword+"%")));
        }
        return productRepository.findAll(spec,pageable).map(this::convertToProductResponse);

    }
    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId()).
                name(product.getName()).
                productBrand(product.getBrand().getName()).
                description(product.getDescription()).
                pictureUrl(product.getPictureUrl()).
                productType(product.getType().getName()).
                price(product.getPrice()).
                build();
    }
}
