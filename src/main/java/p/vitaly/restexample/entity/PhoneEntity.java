package p.vitaly.restexample.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
public final class PhoneEntity implements BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "phone")
    @TableGenerator(
            name = "phone",
            table = "sequences",
            pkColumnName = "key",
            pkColumnValue = "phone_seq",
            valueColumnName = "seed"
    )
    protected Long id;
    private String model;
    private String manufacturer;
    @ElementCollection
    private Map<String, String> parameters;
}
