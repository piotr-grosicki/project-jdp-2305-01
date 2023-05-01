package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.GenericEntity;
import org.springframework.stereotype.Service;

@Service
public class GenericEntityMapper {
    public GenericEntityDto mapToGenericEntityDto(final GenericEntity genericEntity) {
        return new GenericEntityDto(genericEntity.getValue());
    }

    public GenericEntity mapToGenericEntity(final GenericEntityDto genericEntityDto) {
        return new GenericEntity(genericEntityDto.getValue());
    }
}
