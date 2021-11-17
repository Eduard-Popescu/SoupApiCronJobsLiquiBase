package com.example.demo.service.implementation;


import com.example.demo.domain.dto.output.NumberToDollarsRestResponse;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles({"unit-testing"})
@TestExecutionListeners({
    DbUnitTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class
})
public class WsdlServiceTest {

    @Autowired
     private WsdlServiceImpl wsdlService;


    @Test
    public void given_an_big_decimal_value_when_i_call_numberToDollars_return_NumberToDollarsRestResponse(){

        NumberToDollarsRestResponse response = wsdlService.numberToDollars(BigDecimal.valueOf(1));
        assertEquals("one dollar",response.getNumberToDollarsResult());
    }


}
