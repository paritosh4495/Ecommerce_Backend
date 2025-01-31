package com.paritoshpal.ecommerce_yt.mapper.orderItem;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )

public interface OrderItemMapper {

    @Mapping(source = "product.id",target = "productId")
    @Mapping(source = "product.title",target = "title")
    @Mapping(source = "product.brand",target = "brand")
    @Mapping(source = "product.imageUrl",target = "imageUrl")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

}
