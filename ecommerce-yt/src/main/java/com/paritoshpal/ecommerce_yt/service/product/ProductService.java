package com.paritoshpal.ecommerce_yt.service.product;

import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {


    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    String deleteProduct(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getProductByName(String name);
    List<ProductResponseDTO> getProductsByCategory(String category);
    List<ProductResponseDTO> createProducts(List<ProductRequestDTO> productRequestDTOs);
    Page<Product> getFilteredProducts(   String category,
                                         List<String> colors,
                                         List<String> sizes,
                                         Integer minPrice,
                                         Integer maxPrice,
                                         Integer minDiscount,
                                         Integer maxDiscount,
                                         String sort,
                                         String stock,
                                         Integer pageNumber,
                                         Integer pageSize);
}
