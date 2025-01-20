package com.paritoshpal.ecommerce_yt.mapper.orderItem;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )

public interface OrderItemMapper {

    @Mapping(source = "product.id",target = "productId")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

}
