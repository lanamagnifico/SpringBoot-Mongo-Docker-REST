package p.vitaly.restexample;

import de.flapdoodle.embed.mongo.MongodProcess;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.entity.PhoneEntity;
import p.vitaly.restexample.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestMongoConfig.class, TestPersistenceConfig.class})
public class PhoneDaoTest {

    @Autowired
    private static MongodProcess mongodProcess;

    @Autowired
    private Dao<PhoneEntity, Long> dao;

    @Before
    public void setUp() {
    }

    @AfterClass
    public static void tearDown() {
        if (mongodProcess != null) {
            mongodProcess.stop();
        }
    }

    @Test
    public void whenSaveWithNoId_thenIdGenerated() {
        PhoneEntity entity = new PhoneEntity();
        entity.setModel("iPhone X");
        entity.setManufacturer("Apple");
        dao.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenDeleteByNotExistedId_thenEntityNotFoundExceptionThrown() {
        dao.deleteById(-5L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFindByNotExistedId_thenEntityNotFoundExceptionThrown() {
        dao.findById(-5L);
    }

    @Test
    public void whenFindAll_thenReturnList() {
        List<PhoneEntity> phones = dao.findAll();

        assertThat(phones).isNotNull();
    }

    @Test
    public void whenFindAllWithParams_thenReturnFilteredList() {
        PhoneEntity entity = new PhoneEntity();
        entity.setModel("iPhone X");
        entity.setManufacturer("Apple");
        dao.save(entity);

        entity = new PhoneEntity();
        entity.setModel("iPhone 8");
        entity.setManufacturer("Apple");
        dao.save(entity);

        Map<String, String> params = new HashMap<>();
        params.put("model", entity.getModel());

        List<PhoneEntity> phones = dao.findAllWithParams(params);

        assertThat(phones).isNotNull();
        assertThat(phones.size()).isEqualTo(1);
    }
}
