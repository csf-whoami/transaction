package com.csf.training.mybatis.transaction.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.csf.training.mybatis.transaction.model.Employee;

@Mapper
public interface EmployeeMapper {

	public Employee selectEmployeeById(String uuid);
	public void updateEmployeeInfo(Employee employee);
}
