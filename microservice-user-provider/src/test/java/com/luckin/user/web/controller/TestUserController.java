package com.luckin.user.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

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
import com.github.pagehelper.PageInfo;
import com.luckin.UserServiceApplication;
import com.luckin.user.mode.TestPo;
import com.luckin.user.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = UserServiceApplication.class)  
@WebAppConfiguration  
public class TestUserController {
	
	 	MockMvc mvc;  
	 	
	    @Autowired  
	    WebApplicationContext webApplicationConnect;  
	  
	    @Autowired  
	    TestService testServices;  
	  
	    String expectedJson;  
	  
	    @Before  
	    public void setUp() throws JsonProcessingException {  
	        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();  
	  
	    }  
	    
	    @Test  
	    public void save() throws Exception {  
	        String uri = "/test/v1/save";  
	        TestPo t=new TestPo();
	        t.setName("测试2");
	        MvcResult mvcResult = mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(Obj2Json(t)))  
	                .andReturn();  
	        int status = mvcResult.getResponse().getStatus();  
	        String content = mvcResult.getResponse().getContentAsString();  
	        System.out.println(content);
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedResult.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedResult.equals(content));  */
	    }  
	    
	  
	    @Test  
	    public void findByName() throws Exception {  
	        String uri = "/test/v1/findByName/测试";  
	      
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).characterEncoding("UTF-8")
	        		.accept(MediaType.APPLICATION_JSON))  
	                .andReturn();  
	        int status = mvcResult.getResponse().getStatus();  
	        String content = mvcResult.getResponse().getContentAsString();  
	        System.out.println(content);
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedResult.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedResult.equals(content));  */
	    }  
	  
	    
	    @Test  
	    public void findById() throws Exception {  
	        String uri = "/test/v1/findById/1380223";  
	      
	        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).characterEncoding("UTF-8")
	        		.accept(MediaType.APPLICATION_JSON))  
	                .andReturn();  
	        int status = mvcResult.getResponse().getStatus();  
	        String content = mvcResult.getResponse().getContentAsString();  
	        System.out.println(content);
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedResult.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedResult.equals(content));  */
	    }  
	  
	    
	    protected String Obj2Json(Object obj) throws JsonProcessingException {  
	        ObjectMapper mapper=new ObjectMapper();  
	        return mapper.writeValueAsString(obj);  
	    }  
	  
	    @Test  
	    public void findByPage() throws Exception {  
	    	
	    	PageInfo<TestPo> testPOJOList = testServices.queryListPage(null, 0, 3);
	        expectedJson = Obj2Json(testPOJOList);  
	      
	        System.out.println(expectedJson);
	        
	        String uri="/v1/findByPage/0/3";  
	        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();  
	        int status=mvcResult.getResponse().getStatus();  
	        String content=mvcResult.getResponse().getContentAsString();  
	          
	      /*  Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedJson.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedJson.equals(content));  */
	    }  
	    
	    @Test  
	    public void findByNamePage() throws Exception {  
	    	
	    	PageInfo<TestPo> testPOJOList = testServices.findAllByName("测试", 0, 3);
	        expectedJson = Obj2Json(testPOJOList);  
	      
	        System.out.println(expectedJson);
	        
	       /* String uri="/v1/findByNamePage/测试/0/3";  
	        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON)).andReturn();  
	        int status=mvcResult.getResponse().getStatus();  
	        String content=mvcResult.getResponse().getContentAsString();  
	          System.out.println(content);*/
	        
	        
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedJson.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedJson.equals(content));  
	    */}  
	    
	    @Test  
	    public void findAll() throws Exception {  
	    	
	    	List<TestPo> testPOJOList = testServices.queryList(null);
	        expectedJson = Obj2Json(testPOJOList);  
	      
	        System.out.println(expectedJson);
	        
	       /* String uri="/v1/findByNamePage/测试/0/3";  
	        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON)).andReturn();  
	        int status=mvcResult.getResponse().getStatus();  
	        String content=mvcResult.getResponse().getContentAsString();  
	          System.out.println(content);*/
	        
	        
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedJson.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedJson.equals(content));  
	    */}  
	    
	    @Test  
	    public void findByIds() throws Exception {  
	    	
	    	List<Long> idList=new ArrayList<Long>();
	    	idList.add(1380223l);
	    	idList.add(1380224l);
	    	idList.add(1380225l);
	    	idList.add(1380226l);
	    	List<TestPo> testPOJOList = testServices.findIdList(idList);
	        expectedJson = Obj2Json(testPOJOList);  
	      
	        System.out.println(expectedJson);
	        
	       /* String uri="/v1/findByNamePage/测试/0/3";  
	        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON)).andReturn();  
	        int status=mvcResult.getResponse().getStatus();  
	        String content=mvcResult.getResponse().getContentAsString();  
	          System.out.println(content);*/
	        
	        
	       /* Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedJson.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedJson.equals(content));  
	    */}  
	  
	    @Test  
	    public void testShowDaoString() throws Exception {  
	       /* List<TestPo> hotelDtoList=testServices.findByCountry("US");  
	        expectedJson = Obj2Json(hotelDtoList);  
	          
	        String uri="/country/US";  
	        MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();  
	        int status=mvcResult.getResponse().getStatus();  
	        String content=mvcResult.getResponse().getContentAsString();  
	          
	        Assert.assertTrue("错误，正确的返回值为200", status == 200);  
	        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
	        Assert.assertTrue("数据一致", expectedJson.equals(content));  
	        Assert.assertFalse("数据不一致", !expectedJson.equals(content)); */ 
	    }  
}
