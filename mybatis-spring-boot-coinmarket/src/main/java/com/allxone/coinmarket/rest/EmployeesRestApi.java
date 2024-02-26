package com.allxone.coinmarket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allxone.coinmarket.dto.EmployeesDto;
import com.allxone.coinmarket.exception.common.ParamInvalidException;
import com.allxone.coinmarket.model.Employees;
import com.allxone.coinmarket.service.EmployeesService;

@RestController
@RequestMapping("api/v1/employees")
@CrossOrigin("*")
public class EmployeesRestApi {
	
	@Autowired
	EmployeesService employeesService;
	
	@PostMapping
	public ResponseEntity<?> addToEmployees(@RequestBody EmployeesDto employees) throws ParamInvalidException{
		employeesService.addToEmployee(employees);
		
		return ResponseEntity.ok("Save to employees succes!");
		
	}

	
	@PutMapping("{id}")
	public ResponseEntity<?> updateEmployees(@PathVariable Long id, @RequestBody EmployeesDto employees) throws ParamInvalidException{
		
		employees.setId(id);
		
		employeesService.updateEmployee(employees);
		
		return ResponseEntity.ok("Update to employees succes!");
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteEmployees(@PathVariable Long id) throws ParamInvalidException{
		employeesService.deleteEmployee(id);
		
		return ResponseEntity.ok("Delete to employees succes!");
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws ParamInvalidException{
		return ResponseEntity.ok(employeesService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<?> findAll(@RequestParam(name = "page",defaultValue = "1")int page
			,@RequestParam(name = "size",defaultValue = "5") int size){
		   int offset = (page - 1) * size;
		return ResponseEntity.ok(employeesService.findAll(offset, size));
	}
	
	@GetMapping("get-departments")
	public ResponseEntity<?> getDePartments(){
		return ResponseEntity.ok(employeesService.getAllDepartments());
	}
}
