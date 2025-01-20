package com.paritoshpal.ecommerce_yt.service.product;

import com.paritoshpal.ecommerce_yt.dto.product.ProductRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.product.ProductResponseDTO;
import com.paritoshpal.ecommerce_yt.exception.CategoryNotFoundException;
import com.paritoshpal.ecommerce_yt.exception.ProductAlreadyExistsException;
import com.paritoshpal.ecommerce_yt.exception.ProductNotFoundException;
import com.paritoshpal.ecommerce_yt.mapper.product.ProductMapper;
import com.paritoshpal.ecommerce_yt.model.Category;
import com.paritoshpal.ecommerce_yt.model.Product;
import com.paritoshpal.ecommerce_yt.repository.CategoryRepository;
import com.paritoshpal.ecommerce_yt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        validateRequest(productRequestDTO);
        // Debug statement to check sizes in DTO
        System.out.println("Sizes in DTO" + productRequestDTO.getSizes());

        Category topLevel = findOrCreateCategory(productRequestDTO.getTopLevelCategory(), null, 1);
        Category secondLevel = findOrCreateCategory(productRequestDTO.getSecondLevelCategory(), topLevel, 2);
        Category thirdLevel = findOrCreateCategory(productRequestDTO.getThirdLevelCategory(), secondLevel, 3);

        Product product = productMapper.toProduct(productRequestDTO);
        // Manually convert List<Size> to Set<Size>
//        if (productRequestDTO.getSizes() != null) {
//            product.setSizes(new HashSet<>(productRequestDTO.getSizes()));
//        }
        product.setCategory(thirdLevel);

        // Debug statement to check sizes before saving
        System.out.println("Sizes in Product before saving: " + product.getSizes());

        Product savedProduct = productRepository.save(product);


        // Debug statement to check sizes after saving
        System.out.println("Sizes in Product after saving: " + savedProduct.getSizes());
        return mapToResponseDTO(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> createProducts(List<ProductRequestDTO> productRequestDTOs) {
        if (productRequestDTOs == null || productRequestDTOs.isEmpty()) {
            throw new RuntimeException("Product list cannot be null or empty");
        }

        return productRequestDTOs.stream().map(this::createProduct).collect(Collectors.toList());
    }

    @Override
    public Page<Product> getFilteredProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, Integer maxDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Call the repository query
        List<Product> products = productRepository.getFilteredProducts(
                category != null && !category.isBlank() ? category : null,
                minPrice,
                maxPrice,
                minDiscount,
                maxDiscount,
                colors != null && !colors.isEmpty() ? colors : null,
                sizes != null && !sizes.isEmpty() ? sizes : null,
                stock != null && !stock.isBlank() ? stock : null,
                sort
        );

        // Pagination logic
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);

        return new PageImpl<>(pageContent, pageable, products.size());
    }


    @Override
    public String deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.deleteById(id);
        return "Product deleted";
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = findProductById(id);

        updateProductDetails(existingProduct, productRequestDTO);

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = findProductById(id);
        return mapToResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getProductByName(String name) {
        List<Product> product = productRepository.findByTitle(name);
        if(product.isEmpty()){
            throw new ProductNotFoundException("No Products found");
        }
        List<ProductResponseDTO> products = product.stream()
                .map(product1 -> mapToResponseDTO(product1))
                .collect(Collectors.toList());

        return products;
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        categoryRepository.findByName(category)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found!"));

        List<Product> product = productRepository.findByCategoryName(category);

        if(product.isEmpty()){
            throw new ProductNotFoundException("No Products found");
        }
        List<ProductResponseDTO> products = product.stream()
                .map(product1 -> mapToResponseDTO(product1))
                .collect(Collectors.toList());

        return products;
    }



    private void validateRequest(ProductRequestDTO productRequestDTO) {
        if (productRequestDTO == null) {
            throw new RuntimeException("Product cannot be null");
        }
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    private void updateProductDetails(Product product, ProductRequestDTO productRequestDTO) {
        if (productRequestDTO.getTitle() != null && !product.getTitle().equals(productRequestDTO.getTitle())) {
            if (productRepository.existsByTitle(productRequestDTO.getTitle())) {
                throw new ProductAlreadyExistsException("Product with same name already exists");
            }
            product.setTitle(productRequestDTO.getTitle());
        }

        if (productRequestDTO.getDescription() != null) {
            product.setDescription(productRequestDTO.getDescription());
        }
        if (productRequestDTO.getPrice() != null) {
            product.setPrice(productRequestDTO.getPrice());
        }
        if (productRequestDTO.getDiscountedPrice() != null) {
            product.setDiscountedPrice(productRequestDTO.getDiscountedPrice());
        }
        if (productRequestDTO.getDiscountPercentage() > 0) {
            product.setDiscountPercentage(productRequestDTO.getDiscountPercentage());
        }
        if (productRequestDTO.getQuantity() > 0) {
            product.setQuantity(productRequestDTO.getQuantity());
        }
        if (productRequestDTO.getBrand() != null) {
            product.setBrand(productRequestDTO.getBrand());
        }
        if (productRequestDTO.getColor() != null) {
            product.setColor(productRequestDTO.getColor());
        }
        if (productRequestDTO.getImageUrl() != null) {
            product.setImageUrl(productRequestDTO.getImageUrl());
        }
        if (!productRequestDTO.getSizes().isEmpty()) {
            product.setSizes(new HashSet<>(productRequestDTO.getSizes()));
        }

        if (productRequestDTO.getTopLevelCategory() != null) {
            Category topLevel = findOrCreateCategory(productRequestDTO.getTopLevelCategory(), null, 1);
            Category secondLevel = productRequestDTO.getSecondLevelCategory() != null
                    ? findOrCreateCategory(productRequestDTO.getSecondLevelCategory(), topLevel, 2)
                    : null;
            Category thirdLevel = productRequestDTO.getThirdLevelCategory() != null
                    ? findOrCreateCategory(productRequestDTO.getThirdLevelCategory(), secondLevel, 3)
                    : null;

            product.setCategory(thirdLevel != null ? thirdLevel : (secondLevel != null ? secondLevel : topLevel));
        }
    }


    private Category findOrCreateCategory(String categoryName, Category parentCategory, int level) {
        if (parentCategory == null) {
            return categoryRepository.findByName(categoryName)
                    .orElseGet(() -> createCategory(categoryName, null, level));
        }
        return categoryRepository.findByNameAndParentCategory(categoryName, parentCategory)
                .orElseGet(() -> createCategory(categoryName, parentCategory, level));
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        ProductResponseDTO responseDTO = productMapper.toProductResponseDTO(product);
        setCategoryFields(product, responseDTO);
        return responseDTO;
    }

    private void setCategoryFields(Product savedProduct, ProductResponseDTO productResponseDTO) {
        Category category = savedProduct.getCategory();
        if (category != null) {
            productResponseDTO.setThirdLevelCategory(category.getName());
            Category secondLevel = category.getParentCategory();
            if (secondLevel != null) {
                productResponseDTO.setSecondLevelCategory(secondLevel.getName());
                Category topLevel = secondLevel.getParentCategory();
                if (topLevel != null) {
                    productResponseDTO.setTopLevelCategory(topLevel.getName());
                }
            }
        }
    }

    private Category createCategory(String name, Category parentCategory, int level) {
        Category category = new Category();
        category.setName(name);
        category.setParentCategory(parentCategory);
        category.setLevel(level);
        return categoryRepository.save(category);
    }
}
