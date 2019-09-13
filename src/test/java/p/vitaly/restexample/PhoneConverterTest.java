package p.vitaly.restexample;

import org.junit.Before;
import org.junit.Test;
import p.vitaly.restexample.convertor.impl.PhoneConverter;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class PhoneConverterTest {
    private PhoneConverter converter;

    @Before
    public void setUp() {
        converter = new PhoneConverter();
    }

    @Test
    public void whenToDtoBriefFalse_thenParametersIsNotNull() {
        PhoneEntity entity = new PhoneEntity();
        entity.setId(1L);
        entity.setModel("iPhone 8");
        entity.setManufacturer("Apple");
        Map<String,String> params = new HashMap<>();
        params.put("length", "150");
        params.put("width", "70");
        entity.setParameters(params);

        PhoneDto dto = converter.toDto(entity,false);

        assertThat(dto).isNotNull();
        assertThat(dto.getParameters()).isNotNull();
    }

    @Test
    public void whenToDtoBriefTrue_thenParametersIsNull() {
        PhoneEntity entity = new PhoneEntity();
        entity.setId(1L);
        entity.setModel("iPhone 8");
        entity.setManufacturer("Apple");
        Map<String,String> params = new HashMap<>();
        params.put("length", "150");
        params.put("width", "70");
        entity.setParameters(params);

        PhoneDto dto = converter.toDto(entity,true);

        assertThat(dto).isNotNull();
        assertThat(dto.getParameters()).isNull();
    }

    @Test
    public void toEntityConversion() {
        PhoneDto dto = new PhoneDto();
        dto.setId(1L);
        dto.setModel("iPhone 8");
        dto.setManufacturer("Apple");
        Map<String,String> params = new HashMap<>();
        params.put("length", "150");
        params.put("width", "70");
        dto.setParameters(params);

        PhoneEntity entity = converter.toEntity(dto);

        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getModel()).isEqualTo(dto.getModel());
        assertThat(entity.getManufacturer()).isEqualTo(dto.getManufacturer());
        assertThat(entity.getParameters().size()).isEqualTo(dto.getParameters().size());
    }
}
