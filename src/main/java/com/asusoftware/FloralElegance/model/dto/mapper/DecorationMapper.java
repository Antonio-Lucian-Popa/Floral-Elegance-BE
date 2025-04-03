package com.asusoftware.FloralElegance.model.dto.mapper;

import com.asusoftware.FloralElegance.model.Decoration;
import com.asusoftware.FloralElegance.model.dto.DecorationDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DecorationMapper {
    DecorationDto toDto(Decoration decoration);
    List<DecorationDto> toDtoList(List<Decoration> decorations);
    // 🔥 Adaugă aceasta:
    Decoration toEntity(DecorationDto dto);

    // 🆕 mapare între ColorDto și ColorOption
    Decoration.ColorOption toEntity(DecorationDto.ColorDto dto);
    DecorationDto.ColorDto toDto(Decoration.ColorOption option);
    List<Decoration.ColorOption> toEntityColors(List<DecorationDto.ColorDto> dtos);
    List<DecorationDto.ColorDto> toDtoColors(List<Decoration.ColorOption> options);
}
