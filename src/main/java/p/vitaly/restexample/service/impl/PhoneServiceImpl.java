package p.vitaly.restexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.dao.PhoneRepository;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public final class PhoneServiceImpl extends ServiceImpl<PhoneEntity, PhoneDto, Long> {
    @Autowired
    private Converter<PhoneEntity, PhoneDto> converter;

    @Autowired
    private PhoneRepository repository;

    @Override
    Converter<PhoneEntity, PhoneDto> getConverter() {
        return converter;
    }

    @Override
    MongoRepository<PhoneEntity, Long> getDao() {
        return repository;
    }

    @Override
    public List<PhoneDto> getAllWithParams(Map<String, String> params) {
        if (params == null) {
            return getAll();
        }
        return repository
                .findAll(Example.of(createPhoneEntity(params)))
                .stream()
                .map(entity -> converter.toDto(entity))
                .collect(Collectors.toList());
    }

    private PhoneEntity createPhoneEntity(Map<String, String> params) {
        PhoneEntity entity = new PhoneEntity();
        try {
            entity.setId(Long.parseLong(params.remove("id")));
        } catch (NumberFormatException ignore) { }
        entity.setModel(params.remove("model"));
        entity.setManufacturer(params.remove("manufacturer"));
        entity.setParameters(params);
        return entity;
    }
}
