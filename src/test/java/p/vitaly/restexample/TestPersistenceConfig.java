package p.vitaly.restexample;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.dao.impl.PhoneDaoImpl;
import p.vitaly.restexample.entity.PhoneEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@TestConfiguration
@ComponentScan(basePackages = {"p.vitaly.restexample.entity"})
public class TestPersistenceConfig {
    @Bean
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("mongodb-test").createEntityManager();
    }

    @Bean
    public Dao<PhoneEntity, Long> phoneDao() {
        return new PhoneDaoImpl();
    }
}
