package com.csf.training.mybatis.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csf.training.mybatis.transaction.model.Employee;
import com.csf.training.mybatis.transaction.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping(path = "/api/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") String empId, @RequestBody Employee emp){

		try {
			Boolean status = service.updateEmployee(empId, emp);
			if(status) {
				return ResponseEntity.ok("Update Employee success.");
			} else {
				return ResponseEntity.badRequest().body("Update Employee fail.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body("Update Employee fail.");
    }
}
