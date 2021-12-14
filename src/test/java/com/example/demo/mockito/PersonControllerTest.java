package com.example.demo.mockito;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static com.example.demo.service.util.DataSetUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public  class PersonControllerTest {

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext wc;

  private ObjectMapper MAPPER;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    MAPPER = new ObjectMapper();
  }

  @Test
  public void savePersonTest() throws JsonProcessingException, Exception {
    MvcResult result =
        mockMvc
            .perform(
                post("/person")
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(MAPPER.writeValueAsString(PERSON)))
            .andExpect(status().isAccepted())
            .andReturn();

    assertEquals("The person was successfully saved", result.getResponse().getContentAsString());
  }

  @Test
  public void getPersonById() throws Exception {
    MvcResult result =
        mockMvc
            .perform(get("/person/findPerson?personId=1"))
            .andExpect(status().isOk())
            .andReturn();
    System.out.println(result);
    assertEquals(
        MAPPER.writeValueAsString(EXTRACTED_PERSON), result.getResponse().getContentAsString());
  }

  @Test
  public void getAllGraphQL () throws Exception {
    MvcResult result = mockMvc.perform(post("/person/getPerson")
        .content(GRAPHQL_BODY))
        .andExpect(status().isOk())
        .andReturn();

      assertEquals(GRAPHQL_RESPONSE,result.getResponse().getContentAsString());
  }
}
