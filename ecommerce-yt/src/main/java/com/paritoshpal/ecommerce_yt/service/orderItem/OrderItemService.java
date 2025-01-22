package com.paritoshpal.ecommerce_yt.service.orderItem;

import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.model.OrderItem;

public interface OrderItemService {


    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem updateOrderItem(Long orderItemId, OrderItem orderItem);
    String deleteOrderItem(Long orderItemId);
    OrderItemDTO getOrderItemDTO(Long orderItemId);


}
