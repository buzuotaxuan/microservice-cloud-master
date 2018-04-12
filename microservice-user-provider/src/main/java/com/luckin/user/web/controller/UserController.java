package com.luckin.user.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.luckin.user.mode.TestPo;
import com.luckin.user.service.TestService;
import com.luckin.user.web.vo.BaseRsp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(produces = { "application/json;charset=UTF-8" })
@Api(value = "test-api", tags = "测试CURD操作")
@RestController
public class UserController {
	
	@Autowired
	TestService testService;

	
	 /**
     * 分页查询获取对象
     */
    @RequestMapping(value ="/v1/findByPage/{pageNum}/{pageSize}" ,method = RequestMethod.GET)
    @ApiOperation(value ="分页查询获取对象" ,response = TestPo.class, notes = "分页查询获取对象")
    @ApiResponses(value ={
                    @ApiResponse (code = 302, message = "success" ,response=TestPo.class),
                    @ApiResponse (code = 500, message = "内部报错" ,response=BaseRsp.class)}
                 )
    public ResponseEntity<PageInfo<TestPo>> findByPage(@ApiParam(name="pageNum" ,value="当前页", required=true) @PathVariable("pageNum") int pageNum,
    		@ApiParam(name="pageSize" ,value="每页条数", required=true) @PathVariable("pageSize") int pageSize) {
    	
         return new ResponseEntity<PageInfo<TestPo>>(this.testService.queryListPage(null, pageNum, pageSize), HttpStatus.OK);
    }
    
    
    /**
     * 分页查询获取对象
     */
    @RequestMapping(value ="/v1/findAll" ,method = RequestMethod.GET)
    @ApiOperation(value ="分页查询获取对象" ,response = TestPo.class, notes = "分页查询获取对象")
    @ApiResponses(value ={
                    @ApiResponse (code = 302, message = "success" ,response=TestPo.class),
                    @ApiResponse (code = 500, message = "内部报错" ,response=BaseRsp.class)}
                 )
    public ResponseEntity<List<TestPo>> findAll() {
    	
         return new ResponseEntity<List<TestPo>>(this.testService.queryList(null), HttpStatus.OK);
    }
    
    
    /**
     * 根据name分页查询获取对象
     */
    @RequestMapping(value ="/v1/findByNamePage/{name}/{pageNum}/{pageSize}" ,method = RequestMethod.GET)
    @ApiOperation(value ="分页查询获取对象" ,response = TestPo.class, notes = "分页查询获取对象")
    @ApiResponses(value ={
                    @ApiResponse (code = 302, message = "success" ,response=TestPo.class),
                    @ApiResponse (code = 500, message = "内部报错" ,response=BaseRsp.class)}
                 )
    public ResponseEntity<PageInfo<TestPo>> findByNamePage(@ApiParam(name="pageNum" ,value="当前页", required=true) @PathVariable("pageNum") int pageNum,
    		@ApiParam(name="pageSize" ,value="每页条数", required=true) @PathVariable("pageSize") int pageSize,@PathVariable("name") String name) {
    	
         return new ResponseEntity<PageInfo<TestPo>>(this.testService.findAllByName(name, pageNum, pageSize), HttpStatus.OK);
    }
	
	/**
	 * 根据ID获取对象
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v1/findById/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "测试查询", response = TestPo.class, notes = "query test")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "找不到页面"),
			@ApiResponse(code = 500, message = "内部报错", response = TestPo.class) })
	@ResponseBody
	public ResponseEntity<TestPo> getTestById(
			@ApiParam(name = "id", value = "测试号id", required = true) @PathVariable("id") Long id) {
		// DynamicDataSourceHolder.putDataSourceKey("2f6d32ff8d99364498a653fae21711f7");

		TestPo po = this.testService.queryById("1380223");
		return new ResponseEntity<TestPo>(po, HttpStatus.FOUND);
	}

	/**
	 * 根据ID获取对象
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v1/findByName/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "测试查询", response = TestPo.class, notes = "query test")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "找不到页面"),
			@ApiResponse(code = 500, message = "内部报错", response = TestPo.class) })
	@ResponseBody
	public ResponseEntity<TestPo> getTestName(
			@ApiParam(name = "name", value = "姓名", required = true) @PathVariable("name") String name) {
		// DynamicDataSourceHolder.putDataSourceKey(name);
		TestPo po = this.testService.findDesc(name);
		return new ResponseEntity<TestPo>(po, HttpStatus.FOUND);
	}
	
	
	/**
	 * 根据ID获取对象
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v1/findByName2", method = RequestMethod.GET)
	@ApiOperation(value = "测试查询", response = TestPo.class, notes = "query test")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "找不到页面"),
			@ApiResponse(code = 500, message = "内部报错", response = TestPo.class) })
	@ResponseBody
	public ResponseEntity<TestPo> getTestNameV2(
			@ApiParam(name = "name", value = "姓名", required = true) @RequestParam("name") String name) {
		TestPo po = this.testService.findDesc(name);
		return new ResponseEntity<TestPo>(po, HttpStatus.FOUND);
	}

	/**
	 * 保存对象
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1/save", method = RequestMethod.POST)
	@ApiOperation(value = "测试保存", response = BaseRsp.class, notes = "save test")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "success"),
			@ApiResponse(code = 500, message = "内部报错", response = BaseRsp.class) })
	@ResponseBody
	public ResponseEntity<BaseRsp> save(@RequestBody TestPo t) {
		int count = this.testService.save(t);
		BaseRsp base = new BaseRsp();
		if (count > 0) {
			base.setCode(HttpStatus.OK.value());
			base.setMsg("success");
			return new ResponseEntity<BaseRsp>(base, HttpStatus.CREATED);
		} else {
			base.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			base.setMsg("error");
			return new ResponseEntity<BaseRsp>(base, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
