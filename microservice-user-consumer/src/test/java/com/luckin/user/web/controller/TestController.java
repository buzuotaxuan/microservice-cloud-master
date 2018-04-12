package com.luckin.user.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckin.ConsumerServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = ConsumerServiceApplication.class)  
@WebAppConfiguration  
public class TestController {

 	MockMvc mvc;  
 	
    @Autowired  
    WebApplicationContext webApplicationConnect;  
  
    String expectedJson;  
  
    @Before  
    public void setUp() throws JsonProcessingException {  
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();  
  
    }  
    protected String Obj2Json(Object obj) throws JsonProcessingException {  
        ObjectMapper mapper=new ObjectMapper();  
        return mapper.writeValueAsString(obj);  
    }  
  
    @Test  
    public void findByPage() throws Exception {  
        
        String uri="/v1/findByPage/0/3";  
        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();  
        int status=mvcResult.getResponse().getStatus();  
        String content=mvcResult.getResponse().getContentAsString();  
        System.out.println("status is" +status+"==="+content);
    }  

}
