package p.vitaly.restexample.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import p.vitaly.restexample.entity.PhoneEntity;
import p.vitaly.restexample.service.SequenceGeneratorService;

@Component
public final class PhoneEntityEventListener extends AbstractMongoEventListener<PhoneEntity> {
    @Autowired
    private SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<PhoneEntity> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(generatorService.generateForSequence(PhoneEntity.SEQUENCE_NAME));
        }
    }
}
