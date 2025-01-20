package com.paritoshpal.ecommerce_yt.controller;

import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.response.ApiResponse;
import com.paritoshpal.ecommerce_yt.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        System.out.println("Sizes in DTO CONTROLLER LEVEL ::::: " + productRequestDTO.getSizes());
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(createdProduct, "Product created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/bulk")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> createProducts(@RequestBody List<ProductRequestDTO> productRequestDTOs) {
        List<ProductResponseDTO> createdProducts = productService.createProducts(productRequestDTOs);
        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(createdProducts, "Products created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(updatedProduct, "Product updated successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        String message = productService.deleteProduct(id);
        ApiResponse<String> response = new ApiResponse<>(message, "Product deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        ApiResponse<ProductResponseDTO> response = new ApiResponse<>(product, "Product fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductByName(@PathVariable String name) {
        List<ProductResponseDTO> product = productService.getProductByName(name);
        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(product, "Product fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductsByCategory(@PathVariable String category) {
        List<ProductResponseDTO> product = productService.getProductsByCategory(category);
        ApiResponse<List<ProductResponseDTO>> response = new ApiResponse<>(product, "Product fetched successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<Product>>> getFilteredProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> colors,
            @RequestParam(required = false) List<String> sizes,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(defaultValue = "price_low") String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        colors = (colors == null) ? List.of() : colors;
        sizes = (sizes == null) ? List.of() : sizes;

        Page<Product> filteredProducts = productService.getFilteredProducts(
                category, colors, sizes, minPrice, maxPrice, minDiscount, null, sort, stock, pageNumber, pageSize
        );

        ApiResponse<Page<Product>> response = new ApiResponse<>(filteredProducts, "Filtered products fetched successfully");
        return ResponseEntity.ok(response);
    }




}
