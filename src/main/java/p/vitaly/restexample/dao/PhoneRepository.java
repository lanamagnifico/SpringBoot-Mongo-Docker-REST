package p.vitaly.restexample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import p.vitaly.restexample.entity.PhoneEntity;

public interface PhoneRepository extends MongoRepository<PhoneEntity, Long> {
}
