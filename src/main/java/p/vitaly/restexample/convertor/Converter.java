package p.vitaly.restexample.convertor;

public interface Converter<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);
}
