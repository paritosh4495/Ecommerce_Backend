package com.paritoshpal.ecommerce_yt.mapper.order;

import com.paritoshpal.ecommerce_yt.dto.order.OrderRequestDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderResponseDTO;
import com.paritoshpal.ecommerce_yt.dto.order.OrderSummaryDTO;
import com.paritoshpal.ecommerce_yt.dto.orderItem.OrderItemDTO;
import com.paritoshpal.ecommerce_yt.mapper.address.AddressMapper;
import com.paritoshpal.ecommerce_yt.mapper.orderItem.OrderItemMapper;
import com.paritoshpal.ecommerce_yt.model.Order;
import com.paritoshpal.ecommerce_yt.model.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T13:47:55+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        if ( orderRequestDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setAddress( orderRequestDTO.getAddress() );

        return order;
    }

    @Override
    public OrderSummaryDTO toOrderSummaryDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderSummaryDTO orderSummaryDTO = new OrderSummaryDTO();

        orderSummaryDTO.setOrderId( order.getOrderId() );
        orderSummaryDTO.setOrderDate( order.getOrderDate() );
        orderSummaryDTO.setDeliveryDate( order.getDeliveryDate() );
        orderSummaryDTO.setTotalPrice( order.getTotalPrice() );
        orderSummaryDTO.setOrderStatus( order.getOrderStatus() );

        return orderSummaryDTO;
    }

    @Override
    public OrderResponseDTO toOrderResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setId( order.getId() );
        orderResponseDTO.setOrderId( order.getOrderId() );
        orderResponseDTO.setOrderItems( orderItemListToOrderItemDTOList( order.getOrderItems() ) );
        orderResponseDTO.setOrderDate( order.getOrderDate() );
        orderResponseDTO.setDeliveryDate( order.getDeliveryDate() );
        orderResponseDTO.setAddress( addressMapper.toAddressDTO( order.getAddress() ) );
        orderResponseDTO.setPaymentDetails( order.getPaymentDetails() );
        orderResponseDTO.setTotalPrice( order.getTotalPrice() );
        orderResponseDTO.setTotalDiscountedPrice( order.getTotalDiscountedPrice() );
        orderResponseDTO.setDiscount( order.getDiscount() );
        orderResponseDTO.setOrderStatus( order.getOrderStatus() );
        orderResponseDTO.setTotalItems( order.getTotalItems() );
        orderResponseDTO.setCreatedAt( order.getCreatedAt() );
        orderResponseDTO.setUpdatedAt( order.getUpdatedAt() );

        return orderResponseDTO;
    }

    protected List<OrderItemDTO> orderItemListToOrderItemDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toOrderItemDTO( orderItem ) );
        }

        return list1;
    }
}
