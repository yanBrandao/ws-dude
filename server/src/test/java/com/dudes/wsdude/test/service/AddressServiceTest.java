package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.service.AddressService;
import com.dudes.wsdude.service.impl.AddressServiceImpl;
import com.dudes.wsdude.service.impl.GenderServiceImpl;
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
public class AddressServiceTest {
    private AddressService addressService;

    @Mock
    EntityManager entityManager;

    @Mock
    JpaRepository<AddressService, Long> repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        addressService = new AddressServiceImpl();
        ReflectionTestUtils.setField(addressService, "entityManager", entityManager);
        ReflectionTestUtils.setField(addressService, "repository", repository);
    }

    @Test
    public void addAddress(){
        Address address = new Address(0L, "Rua Araxá", "Flores", "251", new City(2L));

        when(repository.save(Mockito.any())).thenReturn(new Address(1L, address.getStreet(), address.getNeighborhood(), address.getNumber(), address.getCity()));

        address = addressService.add(address);

        assertThat(address.getId()).isEqualTo(1L);
        assertThat(address.getStreet()).isEqualTo("Rua Araxá");
        assertThat(address.getNeighborhood()).isEqualTo("Flores");
        assertThat(address.getNumber()).isEqualTo("251");
        assertThat(address.getCity().getId()).isEqualTo(2L);
    }

    @Test
    public void checkTrimAddress(){
        Address address = new Address(0L, "Rua Araxá ", "Flores ", "251 ", new City(2L));
        addressService.trim(address);
        assertThat(address.getStreet()).isEqualTo("Rua Araxá");
        assertThat(address.getNeighborhood()).isEqualTo("Flores");
        assertThat(address.getNumber()).isEqualTo("251");
    }
}
