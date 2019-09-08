package p.vitaly.restexample.service.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.exception.EntityNotFoundException;
import p.vitaly.restexample.service.Service;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceImpl<ENTITY, DTO, ID> implements Service<DTO, ID> {
    abstract Converter<ENTITY, DTO> getConverter();
    abstract MongoRepository<ENTITY, ID> getDao();

    @Override
    public DTO getById(ID id) {
        return getConverter().toDto(getDao().findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<DTO> getAll() {
        return getDao()
                .findAll()
                .parallelStream()
                .map(entity -> getConverter().toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void save(DTO dto) {
        getDao().save(getConverter().toEntity(dto));
    }

    @Override
    @Transactional
    public void delete(ID id) {
        if (!getDao().existsById(id)) {
            throw new EntityNotFoundException();
        }
        getDao().deleteById(id);
    }
}
