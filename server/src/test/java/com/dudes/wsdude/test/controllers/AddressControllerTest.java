package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.AddressController;
import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.AddressService;
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
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressService service;

    @Test
    public void addressGetAllPaginated() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);
        Address myAddress = new Address(1L, "Rua axará", "Flores", "251", manaus);

        Page<Address> allAddresses = new PageImpl<>(Arrays.asList(myAddress));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allAddresses);

        mvc.perform(get("/addresses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].street", is(myAddress.getStreet())));
    }

    @Test
    public void createAddress() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);
        Address myAddress = new Address(1L, "Rua axará", "Flores", "251", manaus);

        when(service.add(Mockito.any())).thenReturn(myAddress);

        String addressJSON = new ObjectMapper().writeValueAsString(myAddress);


        mvc.perform(post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getAddressById() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);
        Address myAddress = new Address(1L, "Rua axará", "Flores", "251", manaus);

        when(service.get(Mockito.any())).thenReturn(myAddress);

        String addressJSON = new ObjectMapper().writeValueAsString(myAddress);

        mvc.perform(get("/addresses/" + myAddress.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeAddress() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);
        Address myAddress = new Address(1L, "Rua axará", "Flores", "251", manaus);

        String addressJSON = new ObjectMapper().writeValueAsString(myAddress);

        mvc.perform(delete("/addresses/" + myAddress.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAddress() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);
        City manaus = new City(1L, "Manaus", amazonas);
        Address myAddress = new Address(1L, "Rua axará", "Flores", "251", manaus);

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(myAddress);

        String addressJSON = new ObjectMapper().writeValueAsString(myAddress);

        mvc.perform(put("/addresses/" + myAddress.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(addressJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
