package com.csf.training.mybatis.transaction.service;

import com.csf.training.mybatis.transaction.model.Employee;

public interface EmployeeService {

	/**
	 * Update employee information.
	 * 
	 * @param empId
	 * @param emp
	 * @return
	 */
	Boolean updateEmployee(String empId, Employee emp) throws Exception;

}
