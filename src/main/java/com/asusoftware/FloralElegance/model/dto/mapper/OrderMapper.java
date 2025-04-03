package com.asusoftware.FloralElegance.model.dto.mapper;

import com.asusoftware.FloralElegance.model.Order;
import com.asusoftware.FloralElegance.model.dto.OrderDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FlowerMapper.class, DecorationMapper.class})
public interface OrderMapper {
    OrderDto toDto(Order order);
    List<OrderDto> toDtoList(List<Order> orders);
}