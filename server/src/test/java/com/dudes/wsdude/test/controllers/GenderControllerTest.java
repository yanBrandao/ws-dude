package com.dudes.wsdude.test.controllers;

import com.dudes.wsdude.controller.GenderController;
import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.GenderService;
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
@WebMvcTest(GenderController.class)
public class GenderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenderService service;

    @Test
    public void cityGetAllPaginated() throws Exception {
        Gender gender = new Gender(1L, "Male");

        Page<Gender> allGenders = new PageImpl<>(Arrays.asList(gender));

        when(service.getAllPaginated(Mockito.any())).thenReturn(allGenders);

        mvc.perform(get("/genders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['content']", hasSize(1)))
                .andExpect(jsonPath("$['content'].[0].name", is(gender.getName())));
    }

    @Test
    public void createGender() throws Exception {
        Gender gender = new Gender(1L, "Male");

        when(service.add(Mockito.any())).thenReturn(gender);

        String genderJSON = new ObjectMapper().writeValueAsString(gender);


        mvc.perform(post("/genders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(genderJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void getGenderById() throws Exception {
        Gender gender = new Gender(1L, "Male");

        when(service.get(Mockito.any())).thenReturn(gender);

        String genderJSON = new ObjectMapper().writeValueAsString(gender);

        mvc.perform(get("/genders/" + gender.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(genderJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }

    @Test
    public void removeGender() throws Exception {
        Gender gender = new Gender(1L, "Male");

        String genderJSON = new ObjectMapper().writeValueAsString(gender);

        mvc.perform(delete("/genders/" + gender.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(genderJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateGender() throws Exception {
        Gender gender = new Gender(1L, "Male");

        when(service.update(Mockito.any(), Mockito.any())).thenReturn(gender);

        String genderJSON = new ObjectMapper().writeValueAsString(gender);

        mvc.perform(put("/genders/" + gender.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(genderJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)));
    }
}
