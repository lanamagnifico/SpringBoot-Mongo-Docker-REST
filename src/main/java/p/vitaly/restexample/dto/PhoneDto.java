package p.vitaly.restexample.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public final class PhoneDto {
    private Long id;
    private String model;
    private String manufacturer;
    private Map<String, String > parameters = new HashMap<>();
}
