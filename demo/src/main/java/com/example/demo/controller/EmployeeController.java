package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.domain.Employee;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
    //查
    @ApiOperation(value = "雇员信息查询",notes = "查询所有雇员的所有信息")
	@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ResponseBody
	
	public List<Employee> queryAll(){
		List<Employee> list = new ArrayList<Employee>();
		list = employeeRepository.findAll();
		return list;
	}
	
    //增
    @ApiOperation(value = "增加新雇员", notes = "增加新雇员的所有信息")
    @ApiImplicitParam(name = "employee", value = "用户详细信息", required = true, dataType = "Employee")
    @RequestMapping(value = "/newEm", method = RequestMethod.POST)
    public String newEm(@RequestBody Employee employee) {
    	Employee e = new Employee();
    	e.setAge(employee.getAge());
    	e.setGender(employee.getGender());
    	e.setId(employee.getId());
    	e.setName(employee.getName());
    	employeeRepository.save(e);
    	return "Success!";
    }
    
    //查
    @ApiOperation(value ="查找雇员信息", notes = "根据雇员id查找雇员信息")
    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "雇员ID", required = true, dataType = "String")
    //@ResponseBody
    public Employee searchById(@PathVariable String id) {
    	return employeeRepository.getOne(id);
    }
    
    //改
    @ApiOperation(value = "修改雇员信息", notes = "根据雇员id查找雇员并根据传过来的雇员信息进行更新")
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "id", value = "雇员ID", required = true, dataType = "String"),
    		@ApiImplicitParam(name = "employee", value = "雇员详细实体", required =true, dataType = "Employee")
    })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)

    public String update(@PathVariable String id, @RequestBody Employee employee) {
    	Employee e = employeeRepository.getOne(id);
    	e.setAge(employee.getAge());
    	e.setGender(employee.getGender());
    	e.setId(employee.getId());
    	e.setName(employee.getName());
    	employeeRepository.saveAndFlush(e);
    	return "success！";
    }
    
    //删
    @ApiOperation(value = "删除雇员", notes = "根据雇员ID删除雇员")
    @ApiImplicitParam(name = "id", value = "雇员ID", required = true, dataType = "String")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)

    public String delete(@PathVariable("id") String id) {
    	employeeRepository.deleteById(id);;
    	return "Success!";
    }
    
	@Autowired
	private EmployeeRepository employeeRepository;
	
}
