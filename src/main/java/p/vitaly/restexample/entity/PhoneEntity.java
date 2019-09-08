package p.vitaly.restexample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "phones")
public final class PhoneEntity {

    @Transient
    public static final String SEQUENCE_NAME = "phones_sequence";

    @Id
    private Long id;
    private String model;
    private String manufacturer;
    private Map<String, String > parameters = new HashMap<>();
}
