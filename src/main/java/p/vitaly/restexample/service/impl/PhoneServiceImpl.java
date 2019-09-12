package p.vitaly.restexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneEntity, PhoneDto, Long> {
    @Autowired
    private Converter<PhoneEntity, PhoneDto> converter;

    @Autowired
    private Dao<PhoneEntity, Long> repository;

    @Override
    Converter<PhoneEntity, PhoneDto> getConverter() {
        return converter;
    }

    @Override
    Dao<PhoneEntity, Long> getDao() {
        return repository;
    }

    @Override
    public List<PhoneDto> getAllWithParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return getAll();
        }
        return repository
                .findAllWithParams(params)
                .parallelStream()
                .map(entity -> getConverter().toDto(entity, true))
                .collect(Collectors.toList());
    }
}
