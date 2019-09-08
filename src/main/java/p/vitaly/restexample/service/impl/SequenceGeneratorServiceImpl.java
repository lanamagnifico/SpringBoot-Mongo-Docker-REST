package p.vitaly.restexample.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import p.vitaly.restexample.entity.DBSequence;
import p.vitaly.restexample.service.SequenceGeneratorService;

import java.util.Objects;

/**
 * Service used for generating 'id' for new entities
 */
@Service
public final class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    @Autowired
    private MongoOperations mongo;

    @Override
    public long generateForSequence(String seqName) {
        DBSequence counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                DBSequence.class);
        return Objects.isNull(counter) ? 1 : counter.getSeq();
    }
}
