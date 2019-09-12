package p.vitaly.restexample.service.impl;

import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.service.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceImpl<ENTITY, DTO, ID> implements Service<DTO, ID> {
    abstract Converter<ENTITY, DTO> getConverter();
    abstract Dao<ENTITY, ID> getDao();

    @Override
    public DTO getById(ID id) {
        return getConverter().toDto(getDao().findById(id), false);
    }

    @Override
    public List<DTO> getAll() {
        return getDao()
                .findAll()
                .parallelStream()
                .map(entity -> getConverter().toDto(entity, true))
                .collect(Collectors.toList());
    }

    @Override
    public void save(DTO dto) {
        getDao().save(getConverter().toEntity(dto));
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }
}
