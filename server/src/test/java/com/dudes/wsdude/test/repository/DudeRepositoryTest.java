package com.dudes.wsdude.test.repository;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.repository.DudeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
public class DudeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DudeRepository repository;

    @Test
    public void findByCPFTest(){
        //Arrange
        Dude dude = new Dude();
        dude.setCPF("999999");
        entityManager.persist(dude);
        entityManager.flush();

        //Act
        Dude dudeFound = repository.findFirstByCPF(dude.getCPF());

        //Assert
        assertThat(dudeFound.getCPF()).isEqualTo(dude.getCPF());
    }
}
