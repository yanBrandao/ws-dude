package com.dudes.wsdude.integration_test;

import com.dudes.wsdude.WsDudeApplication;
import com.dudes.wsdude.WsDudeApplicationTests;
import com.dudes.wsdude.configuration.SwaggerConfiguration;
import com.dudes.wsdude.controller.DudeController;
import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.service.DudeService;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(DudeController.class)
public class DudeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DudeService service;

    @Test
    public void DudeGetAllPaginated() throws Exception {

        Dude dude = new Dude(1L, "Yan", "Tapajós", LocalDate.now(), "001234", new Gender(), new Address());
        Pageable page = PageRequest.of(1, 20);

        Page<Dude> allDudes = new PageImpl<>(Arrays.asList(dude));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allDudes);

        Page<Dude> dudeReturn = service.getAllPaginated(page);


        mvc.perform(get("/dudes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(dude.getName())));
    }
}
