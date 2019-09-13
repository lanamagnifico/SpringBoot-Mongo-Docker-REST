package p.vitaly.restexample;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import p.vitaly.restexample.convertor.Converter;
import p.vitaly.restexample.convertor.impl.PhoneConverter;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;
import p.vitaly.restexample.service.Service;
import p.vitaly.restexample.service.impl.PhoneServiceImpl;

@TestConfiguration
public class TestServiceConfig {
    @Bean
    public Converter<PhoneEntity, PhoneDto> converter() {
        return new PhoneConverter();
    }

    @Bean
    public Service<PhoneDto, Long> phoneService() {
        return new PhoneServiceImpl();
    }
}
