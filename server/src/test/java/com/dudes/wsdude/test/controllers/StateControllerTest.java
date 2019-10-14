package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.StateController;
import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.StateService;
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
@WebMvcTest(StateController.class)
public class StateControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StateService service;

    @Test
    public void countryGetAllPaginated() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);

        Page<State> allStates = new PageImpl<>(Arrays.asList(amazonas));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allStates);

        mvc.perform(get("/states")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(amazonas.getName())));
    }

    @Test
    public void createState() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);

        when(service.add(Mockito.any())).thenReturn(amazonas);

        String stateJSON = new ObjectMapper().writeValueAsString(amazonas);


        mvc.perform(post("/states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(stateJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getStateById() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);

        when(service.get(Mockito.any())).thenReturn(amazonas);

        String stateJSON = new ObjectMapper().writeValueAsString(amazonas);

        mvc.perform(get("/states/" + amazonas.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(stateJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeState() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);

        String stateJSON = new ObjectMapper().writeValueAsString(amazonas);

        mvc.perform(delete("/states/" + amazonas.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(stateJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateState() throws Exception {
        Country brazil = new Country(1L, "Brazil", "BR");
        State amazonas = new State(1L, "Amazonas", "AM", brazil);

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(amazonas);

        String stateJSON = new ObjectMapper().writeValueAsString(amazonas);

        mvc.perform(put("/states/" + amazonas.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(stateJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
