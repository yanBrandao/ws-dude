package com.dudes.wsdude.test.service;

import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.StateService;
import com.dudes.wsdude.service.impl.StateServiceImpl;
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
public class StateServiceTest {
    private StateService stateService;

    @Mock
    EntityManager entityManager;

    @Mock
    JpaRepository<State, Long> repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        stateService = new StateServiceImpl();
        ReflectionTestUtils.setField(stateService, "entityManager", entityManager);
        ReflectionTestUtils.setField(stateService, "repository", repository);
    }

    @Test
    public void addState(){
        State state = new State(0L, "Amazonas", "AM", new Country(1L));

        when(repository.save(Mockito.any())).thenReturn(new State(1L, "Amazonas", "AM", new Country(1L)));

        state = stateService.add(state);

        assertThat(state.getId()).isEqualTo(1L);
        assertThat(state.getName()).isEqualTo("Amazonas");
        assertThat(state.getInitials()).isEqualTo("AM");
    }

    @Test
    public void checkTrimState(){
        State state = new State(0L, "Amazonas  ", "AM  ", new Country(1L));
        stateService.trim(state);
        assertThat(state.getName()).isEqualTo("Amazonas");
        assertThat(state.getInitials()).isEqualTo("AM");
    }
}
