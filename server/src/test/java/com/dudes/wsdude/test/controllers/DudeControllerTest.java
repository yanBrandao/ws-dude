package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.DudeController;
import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.service.DudeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DudeController.class)
public class DudeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DudeService service;

    @Test
    public void dudeGetAllPaginated() throws Exception {

        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(), new Address());

        Page<Dude> allDudes = new PageImpl<>(Arrays.asList(dude));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allDudes);

        mvc.perform(get("/dudes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(dude.getName())));
    }

    @Test
    public void createDude() throws Exception {
        Gender gender = new Gender(1L, "Male");
        Address address = new Address(1L, "Rua Araxá", "251", "Flores", new City(1L));
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(LocalDate.of(1992, 07, 25).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()) , "001234", gender, address);

        when(service.add(Mockito.any())).thenReturn(dude);

        String dudeJSON = new ObjectMapper().writeValueAsString(dude);

        mvc.perform(post("/dudes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dudeJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getDudeById() throws Exception {
        Gender gender = new Gender(1L, "Male");
        Address address = new Address(1L, "Rua Araxá", "251", "Flores", new City(1L));
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(LocalDate.of(1992, 07, 25).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()) , "001234", gender, address);

        when(service.get(Mockito.any())).thenReturn(dude);

        String dudeJSON = new ObjectMapper().writeValueAsString(dude);

        mvc.perform(get("/dudes/" + dude.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dudeJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getDudeByCPF() throws Exception {
        Gender gender = new Gender(1L, "Male");
        Address address = new Address(1L, "Rua Araxá", "251", "Flores", new City(1L));
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(LocalDate.of(1992, 07, 25).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()) , "001234", gender, address);

        when(service.findByCPF(Mockito.any())).thenReturn(dude);

        String dudeJSON = new ObjectMapper().writeValueAsString(dude);

        mvc.perform(get("/dudes/cpf/" + dude.getCPF())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dudeJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeDude() throws Exception {
        Gender gender = new Gender(1L, "Male");
        Address address = new Address(1L, "Rua Araxá", "251", "Flores", new City(1L));
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(LocalDate.of(1992, 07, 25).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()) , "001234", gender, address);

        String dudeJSON = new ObjectMapper().writeValueAsString(dude);

        mvc.perform(delete("/dudes/" + dude.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dudeJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateDude() throws Exception {
        Gender gender = new Gender(1L, "Male");
        Address address = new Address(1L, "Rua Araxá", "251", "Flores", new City(1L));
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(LocalDate.of(1992, 07, 25).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()) , "001234", gender, address);

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(dude);

        String dudeJSON = new ObjectMapper().writeValueAsString(dude);

        mvc.perform(put("/dudes/" + dude.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dudeJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
