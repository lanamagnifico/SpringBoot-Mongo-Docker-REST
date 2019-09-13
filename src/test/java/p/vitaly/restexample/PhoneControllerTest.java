package p.vitaly.restexample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import p.vitaly.restexample.controller.PhoneController;
import p.vitaly.restexample.dto.PhoneDto;
import p.vitaly.restexample.service.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {

    @MockBean
    private Service<PhoneDto, Long> service;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        PhoneDto iPhoneX = new PhoneDto();
        iPhoneX.setId(1L);
        iPhoneX.setManufacturer("Apple");
        iPhoneX.setModel("iPhone X");
        Map<String, String> params = new HashMap<>();
        params.put("length", "150");
        params.put("width", "70");
        params.put("weight", "100");
        iPhoneX.setParameters(params);

        PhoneDto iPhone8 = new PhoneDto();
        iPhone8.setId(2L);
        iPhone8.setManufacturer("Apple");
        iPhone8.setModel("iPhone 8");
        params = new HashMap<>();
        params.put("length", "100");
        params.put("width", "50");
        params.put("weight", "70");
        iPhone8.setParameters(params);

        List<PhoneDto> phones = Arrays.asList(iPhoneX, iPhone8);

        Mockito.when(service.getAllWithParams(Mockito.any())).thenReturn(phones);
        Mockito.when(service.getById(Mockito.any())).thenReturn(iPhone8);
    }

    @Test
    public void whenGetPhones_thenReturnJsonArray() throws Exception {
        mvc.perform(get("/phones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("iPhone X")))
                .andExpect(jsonPath("$[1].model", is("iPhone 8")));
    }

    @Test
    public void whenGetById_thenReturnJsonObject() throws Exception {
        mvc.perform(get("/phones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer", is("Apple")))
                .andExpect(jsonPath("$.model", is("iPhone 8")));
    }

    @Test
    public void whenSaveWithId_thenStatusOk() throws Exception {
        String content = "{" +
                "\"id\": 1," +
                "\"manufacturer\": \"Apple\"," +
                "\"model\": \"iPhone X\"," +
                "\"parameters\": {" +
                "\"length\": 150," +
                "\"width\": 100," +
                "\"weight\": 75" +
                "}}";
        mvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void whenSaveWithNoId_thenStatusCreated() throws Exception {
        String content = "{" +
                "\"manufacturer\": \"Apple\"," +
                "\"model\": \"iPhone X\"," +
                "\"parameters\": {" +
                "\"length\": 150," +
                "\"width\": 100," +
                "\"weight\": 75" +
                "}}";
        mvc.perform(post("/phones")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenDelete_thenStatusOk() throws Exception {
        mvc.perform(delete("/phones/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}
