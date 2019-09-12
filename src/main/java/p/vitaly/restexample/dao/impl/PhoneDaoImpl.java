package p.vitaly.restexample.dao.impl;

import org.springframework.stereotype.Repository;
import p.vitaly.restexample.entity.PhoneEntity;

import java.util.List;
import java.util.Map;

@Repository
public class PhoneDaoImpl extends DaoImpl<PhoneEntity, Long> {

    public PhoneDaoImpl() {
        super(PhoneEntity.class);
    }

    @Override
    public List<PhoneEntity> findAllWithParams(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();
        String model = parameters.remove("model");
        if (model != null) {
            sb.append("'model':'").append(model).append("'");
        }
        String manufacturer = parameters.remove("manufacturer");
        if (manufacturer != null) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append("'manufacturer':'").append(manufacturer).append("'");
        }
        if (!parameters.isEmpty()) {
            parameters.forEach((k, v) -> {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append("'parameters.").append(k).append("':'").append(v).append("'");
            });
        }
        return em.createNativeQuery("db.PhoneEntity.find({" + sb.toString() + "})", entityClass).getResultList();
    }
}
