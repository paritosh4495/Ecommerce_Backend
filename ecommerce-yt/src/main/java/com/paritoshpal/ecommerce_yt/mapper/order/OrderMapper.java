package com.paritoshpal.ecommerce_yt.mapper.order;


import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderSummaryDTO;
import com.paritoshpal.ecommerce_yt.mapper.address.AddressMapper;
import com.paritoshpal.ecommerce_yt.mapper.orderItem.OrderItemMapper;
import com.paritoshpal.ecommerce_yt.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {OrderItemMapper.class, AddressMapper.class})

public interface OrderMapper {

    // A lot of things will have to be set in the service Layer. Watch OUT
    // 1. orderId, 2. User, 3. OrderItems, 4. orderDate, 5. deliveryDate,
    // 6. paymentDetails, 7. totalPrice, 8. totalDiscountedPrice
    // 9. Discount, 10. orderStatus, 11. totalItems
    Order toOrder(OrderRequestDTO orderRequestDTO);


    OrderSummaryDTO toOrderSummaryDTO(Order order);


    OrderResponseDTO toOrderResponseDTO(Order order);


}
