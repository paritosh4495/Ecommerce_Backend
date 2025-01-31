package com.paritoshpal.ecommerce_yt.mapper.orderItem;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import com.paritoshpal.ecommerce_yt.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-30T22:36:37+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setProductId( orderItemProductId( orderItem ) );
        orderItemDTO.setTitle( orderItemProductTitle( orderItem ) );
        orderItemDTO.setBrand( orderItemProductBrand( orderItem ) );
        orderItemDTO.setImageUrl( orderItemProductImageUrl( orderItem ) );
        orderItemDTO.setSize( orderItem.getSize() );
        orderItemDTO.setQuantity( orderItem.getQuantity() );
        orderItemDTO.setPrice( orderItem.getPrice() );
        orderItemDTO.setDiscountedPrice( orderItem.getDiscountedPrice() );

        return orderItemDTO;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String orderItemProductTitle(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getTitle();
    }

    private String orderItemProductBrand(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getBrand();
    }

    private String orderItemProductImageUrl(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getImageUrl();
    }
}
