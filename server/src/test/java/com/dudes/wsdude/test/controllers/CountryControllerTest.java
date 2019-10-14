package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.CountryController;
import com.dudes.wsdude.controller.DudeController;
import com.dudes.wsdude.domain.*;
import com.dudes.wsdude.service.CountryService;
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

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryService service;

    @Test
    public void countryGetAllPaginated() throws Exception {

        Country brazil = new Country(1L, "Brazil", "BR");

        Page<Country> allCountries = new PageImpl<>(Arrays.asList(brazil));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allCountries);

        mvc.perform(get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(brazil.getName())));
    }

    @Test
    public void createCountry() throws Exception {
        Country unitedStates = new Country(1L, "United State", "US");

        when(service.add(Mockito.any())).thenReturn(unitedStates);

        String countryJSON = new ObjectMapper().writeValueAsString(unitedStates);


        mvc.perform(post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(countryJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getCountryById() throws Exception {
        Country mexico = new Country(1L, "Mexico", "MEX");

        when(service.get(Mockito.any())).thenReturn(mexico);

        String countryJSON = new ObjectMapper().writeValueAsString(mexico);

        mvc.perform(get("/countries/" + mexico.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(countryJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeCountry() throws Exception {
        Country germany  = new Country(1L, "Germany", "DE");

        String countryJSON = new ObjectMapper().writeValueAsString(germany);

        mvc.perform(delete("/countries/" + germany.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(countryJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCountry() throws Exception {
        Country malaysia = new Country(1L, "Malaysia", "MYS");

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(malaysia);

        String countryJSON = new ObjectMapper().writeValueAsString(malaysia);

        mvc.perform(put("/countries/" + malaysia.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(countryJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
