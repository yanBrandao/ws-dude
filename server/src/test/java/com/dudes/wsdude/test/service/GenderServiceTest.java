package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.service.GenderService;
import com.dudes.wsdude.service.GenericService;
import com.dudes.wsdude.service.impl.GenderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application.test.properties")
@DataJpaTest
public class GenderServiceTest {
    private GenderService genderService;

    @Mock
    EntityManager entityManager;

    @Mock
    JpaRepository<Gender, Long> repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        genderService = new GenderServiceImpl();
        ReflectionTestUtils.setField(genderService, "entityManager", entityManager);
        ReflectionTestUtils.setField(genderService, "repository", repository);
    }

    @Test
    public void addGender(){
        Gender gender = new Gender(0L, "Male");

        when(repository.save(Mockito.any())).thenReturn(new Gender(1L, gender.getName()));

        gender = genderService.add(gender);

        assertThat(gender.getId()).isEqualTo(1L);
        assertThat(gender.getName()).isEqualTo("Male");
    }

    @Test
    public void checkTrimGender(){
        Gender gender = new Gender(0L, "Male  ");
        genderService.trim(gender);
        assertThat(gender.getName()).isEqualTo("Male");
    }
}
