package p.vitaly.restexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class PersistenceConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("mongodb");
    }

    @Bean(name = "mongoEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }
}
