package p.vitaly.restexample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.entity.PhoneEntity;
import p.vitaly.restexample.service.Service;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestServiceConfig.class)
public class PhoneServiceTest {

    @MockBean
    private Dao<PhoneEntity, Long> phoneDao;

    @Autowired
    private Service<PhoneDto, Long> phoneService;

    @Before
    public void setUp() {
        PhoneEntity iPhoneX = new PhoneEntity();
        iPhoneX.setId(1L);
        iPhoneX.setManufacturer("Apple");
        iPhoneX.setModel("iPhone X");
        Map<String, String> params = new HashMap<>();
        params.put("length", "150");
        params.put("width", "70");
        params.put("weight", "100");
        iPhoneX.setParameters(params);

        PhoneEntity iPhone8 = new PhoneEntity();
        iPhone8.setId(2L);
        iPhone8.setManufacturer("Apple");
        iPhone8.setModel("iPhone 8");
        params = new HashMap<>();
        params.put("length", "100");
        params.put("width", "50");
        params.put("weight", "70");
        iPhone8.setParameters(params);

        List<PhoneEntity> phones = Arrays.asList(iPhoneX, iPhone8);
        List<PhoneEntity> filteredPhones = Collections.singletonList(iPhoneX);

        Mockito.when(phoneDao.findById(iPhoneX.getId())).thenReturn(iPhoneX);
        Mockito.when(phoneDao.findById(iPhone8.getId())).thenReturn(iPhone8);

        Mockito.when(phoneDao.findAll()).thenReturn(phones);

        Mockito.when(phoneDao.findAllWithParams(Mockito.anyMap())).thenReturn(filteredPhones);
        Mockito.when(phoneDao.findAllWithParams(null)).thenReturn(phones);
    }

    @Test
    public void whenGetById_thenReturnPhone() {
        Long id = 1L;
        PhoneDto found = phoneService.getById(id);

        assertThat(found).isNotNull();
    }

    @Test
    public void whenGetAll_thenReturnList() {
        List<PhoneDto> phones = phoneService.getAll();

        assertThat(phones).isNotNull();
    }

    @Test
    public void whenGetAllWithEmptyParams_thenReturnFullList() {
        List<PhoneDto> filtered = phoneService.getAllWithParams(null);

        assertThat(filtered).isNotNull();
        assertThat(filtered.size()).isEqualTo(2);
    }

    @Test
    public void whenGetAllWithParams_thenReturnFilteredList() {
        Map<String, String> params = new HashMap<>();
        params.put("model", "iPhone X");
        List<PhoneDto> filtered = phoneService.getAllWithParams(params);

        assertThat(filtered).isNotNull();
        assertThat(filtered.size()).isEqualTo(1);
    }
}
