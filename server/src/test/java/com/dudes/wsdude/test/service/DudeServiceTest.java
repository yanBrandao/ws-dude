package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.exception.InvalidEntityException;
import com.dudes.wsdude.exception.InvalidValueException;
import com.dudes.wsdude.exception.NotFoundException;
import com.dudes.wsdude.repository.DudeRepository;
import com.dudes.wsdude.service.DudeService;
import com.dudes.wsdude.service.impl.DudeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application.test.properties")
@DataJpaTest
public class DudeServiceTest {
    private DudeService dudeService;

    @Mock
    EntityManager entityManager;

    @Mock
    DudeRepository repository;

    @Mock
    JpaRepository<Dude, Long> genericRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        dudeService = new DudeServiceImpl();
        ReflectionTestUtils.setField(dudeService, "entityManager", entityManager);
        ReflectionTestUtils.setField(dudeService, "dudeRepository", repository);
        ReflectionTestUtils.setField(dudeService, "repository", genericRepository);
    }

    @Test
    public void addDude(){
        Dude dude = new Dude(0L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L));

        when(genericRepository.save(Mockito.any())).thenReturn(new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L)));

        dude = dudeService.add(dude);

        assertThat(dude.getId()).isEqualTo(1L);
        assertThat(dude.getName()).isEqualTo("Yan");
        assertThat(dude.getLastName()).isEqualTo("Tapajós");
        assertThat(dude.getCPF()).isEqualTo("001234");
    }

    @Test
    public void validateIdFilled(){
        Dude dude = new Dude(1L, "Yan", "Diniz", new Date(), "001234", new Gender(1L), new Address(1L));
        assertThatThrownBy(() -> {
            dudeService.validateId(dude, false);
        })
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("dude.id.cannotBeFilled");
    }

    @Test
    public void addDudeException(){
        Dude dude = new Dude(0L, "Yan", "Diniz", new Date(), "001234", new Gender(1L), new Address(1L));

        doThrow(ConstraintViolationException.class).when(genericRepository).save(dude);

        assertThatThrownBy(() -> {
            dudeService.add(dude);
        })
                .isInstanceOf(InvalidEntityException.class);
    }

    @Test
    public void getAllDudesPaginated(){
        Page<Dude> dudes;
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L));

        when(genericRepository.findAll(new PageRequest(0, 1))).thenReturn(new PageImpl<>(Arrays.asList(dude)));

        dudes = dudeService.getAllPaginated(new PageRequest(0, 1));

        assertThat(dudes.getContent().size()).isEqualTo(1);
        assertThat(dudes.getContent().get(0).getName()).isEqualTo("Yan");
        assertThat(dudes.getContent().get(0).getLastName()).isEqualTo("Tapajós");
        assertThat(dudes.getContent().get(0).getCPF()).isEqualTo("001234");
    }

    @Test
    public void updateDude(){
        Dude dude = new Dude(1L, "Yan", "Diniz", new Date(), "001234", new Gender(1L), new Address(1L));

        when(genericRepository.save(Mockito.any())).thenReturn(new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L)));

        dude = dudeService.update(dude.getId(), dude);

        assertThat(dude.getId()).isEqualTo(1L);
        assertThat(dude.getName()).isEqualTo("Yan");
        assertThat(dude.getLastName()).isEqualTo("Tapajós");
        assertThat(dude.getCPF()).isEqualTo("001234");
    }

    @Test
    public void removeDudeByIdExceptionConstraintViolation(){
        Dude dude = new Dude(1L, "Yan", "Diniz", new Date(), "001234", new Gender(1L), new Address(1L));

        doThrow(ConstraintViolationException.class).when(genericRepository).deleteById(dude.getId());

        assertThatThrownBy(() -> {
            dudeService.removeById(dude.getId());
        })
                .isInstanceOf(InvalidEntityException.class)
                .hasMessage("dude.constraintViolationOnDelete");
    }

    @Test
    public void removeDudeById(){
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L));

        doNothing().when(genericRepository).deleteById(dude.getId());
        dudeService.removeById(dude.getId());
    }

    @Test
    public void getDude(){
        Dude dude;

        when(genericRepository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(new Dude(1L, "Yan", "Tapajós", new Date(), "001234", new Gender(1L), new Address(1L))));

        dude = dudeService.get(1L);

        assertThat(dude.getId()).isEqualTo(1L);
        assertThat(dude.getName()).isEqualTo("Yan");
        assertThat(dude.getLastName()).isEqualTo("Tapajós");
        assertThat(dude.getCPF()).isEqualTo("001234");
    }

    @Test
    public void checkTrimDude(){
        Dude dude = new Dude(1L, "Yan ", "Tapajós ", new Date(), "001234 ", new Gender(1L), new Address(1L));
        dudeService.trim(dude);
        assertThat(dude.getName()).isEqualTo("Yan");
        assertThat(dude.getLastName()).isEqualTo("Tapajós");
        assertThat(dude.getCPF()).isEqualTo("001234");
    }
}
