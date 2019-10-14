package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.CityController;
import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.CityService;
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

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService service;

    @Test
    public void cityGetAllPaginated() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);

        Page<City> allCities = new PageImpl<>(Arrays.asList(manaus));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allCities);

        mvc.perform(get("/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(manaus.getName())));
    }

    @Test
    public void createCity() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);

        when(service.add(Mockito.any())).thenReturn(manaus);

        String cityJSON = new ObjectMapper().writeValueAsString(manaus);


        mvc.perform(post("/cities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cityJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getCityById() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);

        when(service.get(Mockito.any())).thenReturn(manaus);

        String cityJSON = new ObjectMapper().writeValueAsString(manaus);

        mvc.perform(get("/cities/" + manaus.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(cityJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeCity() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);

        String cityJSON = new ObjectMapper().writeValueAsString(manaus);

        mvc.perform(delete("/cities/" + manaus.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(cityJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCity() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(manaus);

        String cityJSON = new ObjectMapper().writeValueAsString(manaus);

        mvc.perform(put("/cities/" + manaus.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(cityJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
