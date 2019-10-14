package com.dudes.wsdude.test.mapper;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.dto.DudeDTO;
import com.dudes.wsdude.mapper.DudeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DudeMapperTest {

    @Test
    public void convertToEntityWhenNull(){
        //Arrange
        DudeDTO dudeDTO = new DudeDTO(1L, "Yan", "Tapajós", new Date(), "01365201201", null, null);
        Dude dude = null;

        //Act
        dude = new DudeMapper().convertToEntity(dudeDTO);

        //Assert
        assertThat(dude.getName()).isEqualTo(dudeDTO.getName());
        assertThat(dude.getLastName()).isEqualTo(dudeDTO.getLastName());
        assertThat(dude.getCPF()).isEqualTo(dudeDTO.getCPF());
        assertThat(dude.getBornDate()).isEqualTo(dudeDTO.getBornDate());
        assertThat(dude.getAddress()).isEqualTo(dudeDTO.getAddress());
        assertThat(dude.getGender()).isEqualTo(dudeDTO.getGender());
    }

    @Test
    public void convertToDTOWhenNull(){
        //Arrange
        Dude dude = new Dude(1L, "Yan", "Tapajós", new Date(), "01365201201", null, null);
        DudeDTO dudeDTO = null;

        //Act
        dudeDTO = new DudeMapper().convertToDTO(dude);

        //Assert
        assertThat(dudeDTO.getName()).isEqualTo(dude.getName());
        assertThat(dudeDTO.getLastName()).isEqualTo(dude.getLastName());
        assertThat(dudeDTO.getCPF()).isEqualTo(dude.getCPF());
        assertThat(dudeDTO.getBornDate()).isEqualTo(dude.getBornDate());
        assertThat(dudeDTO.getAddress()).isEqualTo(dude.getAddress());
        assertThat(dudeDTO.getGender()).isEqualTo(dude.getGender());
    }
}
