package com.dudes.wsdude.test.validateUtils;

import com.dudes.wsdude.exception.InvalidValueException;
import com.dudes.wsdude.exception.NotFoundException;
import com.dudes.wsdude.utils.ValidateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
public class ValidateUtilsTest {

    @Test
    public void checkFoundExceptionTest(){
        ValidateUtils validateUtils = new ValidateUtils();

        assertThatThrownBy(() -> {
            validateUtils.checkFound(null, "dude.notFound");
        })
                .isInstanceOf(NotFoundException.class)
                .hasMessage("dude.notFound");
    }

    @Test
    public void checkBiggerThanZeroTest(){
        ValidateUtils validateUtils = new ValidateUtils();

        assertThatThrownBy(() -> {
            validateUtils.checkBiggerThanZero(-1, "dude.id.mustBeFilled");
        })
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("dude.id.mustBeFilled");
    }

    @Test
    public void checkMustBeNullOrZero(){
        ValidateUtils validateUtils = new ValidateUtils();

        assertThatThrownBy(() -> {
            validateUtils.checkMustBeNullOrZero(20L, "Value must be null or Zero");
        })
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("Value must be null or Zero");
    }
}
