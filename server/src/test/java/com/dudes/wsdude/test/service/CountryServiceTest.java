package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.service.CountryService;
import com.dudes.wsdude.service.impl.CountryServiceImpl;
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
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application.test.properties")
@DataJpaTest
public class CountryServiceTest {
    private CountryService countryService;

    @Mock
    EntityManager entityManager;

    @Mock
    JpaRepository<Country, Long> repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        countryService = new CountryServiceImpl();
        ReflectionTestUtils.setField(countryService, "entityManager", entityManager);
        ReflectionTestUtils.setField(countryService, "repository", repository);
    }

    @Test
    public void addCountry(){
        Country country = new Country(0L, "Brazil", "BR");

        when(repository.save(Mockito.any())).thenReturn(new Country(1L, "Brazil", "BR"));

        country = countryService.add(country);

        assertThat(country.getId()).isEqualTo(1L);
        assertThat(country.getName()).isEqualTo("Brazil");
        assertThat(country.getInitials()).isEqualTo("BR");
    }

    @Test
    public void checkTrimCountry(){
        Country country = new Country(0L, "Brazil ", "BR ");

        countryService.trim(country);
        assertThat(country.getName()).isEqualTo("Brazil");
        assertThat(country.getInitials()).isEqualTo("BR");
    }
}
