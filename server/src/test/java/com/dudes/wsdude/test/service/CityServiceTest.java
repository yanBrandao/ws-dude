package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.CityService;
import com.dudes.wsdude.service.impl.CityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
public class CityServiceTest {
    private CityService cityService;

    @Mock
    EntityManager entityManager;

    @Mock
    JpaRepository<City, Long> repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        cityService = new CityServiceImpl();
        ReflectionTestUtils.setField(cityService, "entityManager", entityManager);
        ReflectionTestUtils.setField(cityService, "repository", repository);
    }

    @Test
    public void addCity(){
        City city = new City(0L, "Manaus", new State());

        when(repository.save(Mockito.any())).thenReturn(new City(1L, "Manaus", new State()));

        city = cityService.add(city);

        assertThat(city.getId()).isEqualTo(1L);
        assertThat(city.getName()).isEqualTo("Manaus");
    }

    @Test
    public void checkTrimCity(){
        City city = new City(1L, "Manaus  ", new State());
        cityService.trim(city);
        assertThat(city.getName()).isEqualTo("Manaus");
    }
}
