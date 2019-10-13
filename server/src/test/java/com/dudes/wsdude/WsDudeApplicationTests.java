package com.dudes.wsdude;

import com.dudes.wsdude.domain.Dude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsDudeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
