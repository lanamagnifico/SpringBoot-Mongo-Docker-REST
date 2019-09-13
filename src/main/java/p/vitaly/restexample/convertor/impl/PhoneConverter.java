package p.vitaly.restexample.convertor.impl;

import org.springframework.stereotype.Component;
import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;

@Component
public final class PhoneConverter implements Converter<PhoneEntity, PhoneDto> {
    @Override
    public PhoneEntity toEntity(PhoneDto dto) {
        if (dto == null) {
            return null;
        }
        PhoneEntity entity = new PhoneEntity();
        entity.setId(dto.getId());
        entity.setManufacturer(dto.getManufacturer());
        entity.setModel(dto.getModel());
        entity.setParameters(dto.getParameters());
        return entity;
    }

    @Override
    public PhoneDto toDto(PhoneEntity entity, boolean brief) {
        if (entity == null) {
            return null;
        }
        PhoneDto dto = new PhoneDto();
        dto.setId(entity.getId());
        dto.setManufacturer(entity.getManufacturer());
        dto.setModel(entity.getModel());
        if (!brief) {
            dto.setParameters(entity.getParameters());
        }
        return dto;
    }
}
