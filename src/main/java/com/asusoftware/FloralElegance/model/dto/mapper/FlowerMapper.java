package com.asusoftware.FloralElegance.model.dto.mapper;

import com.asusoftware.FloralElegance.model.Flower;
import com.asusoftware.FloralElegance.model.dto.FlowerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlowerMapper {
    FlowerDto toDto(Flower flower);
    List<FlowerDto> toDtoList(List<Flower> flowers);
    Flower toEntity(FlowerDto dto);
}