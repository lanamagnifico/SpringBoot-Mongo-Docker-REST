package p.vitaly.restexample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "db_sequences")
public final class DBSequence {
    @Id
    private String id;
    private long seq;
}
