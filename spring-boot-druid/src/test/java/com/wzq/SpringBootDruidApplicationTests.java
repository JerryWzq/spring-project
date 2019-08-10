package com.wzq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzq.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDruidApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootDruidApplicationTests.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGet(){
        try {
            MvcResult mrbird = mockMvc.perform(MockMvcRequestBuilders.get("/city/hello?name={name}", "mrbird")).andReturn();
            MockHttpServletResponse response = mrbird.getResponse();
            String contentAsString = response.getContentAsString();
            logger.info("==============result: {}", contentAsString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPost(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/city/{id}", 1))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.cityName").value("济南"))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testPostJSON(){
        try {
            String data = "{\n" +
                    "\t\"cityName\": \"上海\",\n" +
                    "\t\"pid\": 1,\n" +
                    "\t\"size\": \"SMALL\"\n" +
                    "}";

            mockMvc.perform(MockMvcRequestBuilders.post("/city/insert").contentType("application/json").content(data.getBytes()))
                    .andDo(MockMvcResultHandlers.print());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testPostJSON01(){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            City city = new City();
            city.setPid(1L);
            city.setCityName("上海");
            city.setSize("SMALL");
            byte[] valueAsBytes = objectMapper.writeValueAsBytes(city);

            mockMvc.perform(MockMvcRequestBuilders.post("/city/insert").contentType("application/json").content(valueAsBytes))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSessionAndCookie(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/city/echo").sessionAttr("root", "admin")).
                    andDo(MockMvcResultHandlers.print());
            mockMvc.perform(MockMvcRequestBuilders.get("/city/echo").cookie(new Cookie("root", "root")))
                    .andDo(MockMvcResultHandlers.print());
        }catch (Exception e){

        }
    }

}
