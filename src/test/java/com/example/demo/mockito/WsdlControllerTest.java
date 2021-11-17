package com.example.demo.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WsdlControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wc;


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    public void numberToDollars () throws Exception {
        MvcResult result = mockMvc.perform(get("/Wsdl/NumberToDollars?valueToConvert=100")).andExpect(status().isOk()).andReturn();
        assertEquals("{\"numberToDollarsResult\":\"one hundred dollars\"}", result.getResponse().getContentAsString());
    }

    @Test
    public void numberToWords () throws  Exception{
        MvcResult result = mockMvc.perform(get("/Wsdl/NumberToWords?valueToConvert=100")).andExpect(status().isOk()).andReturn();
        assertEquals("{\"numberToWordsResult\":\"one hundred \"}",result.getResponse().getContentAsString());
    }
}
