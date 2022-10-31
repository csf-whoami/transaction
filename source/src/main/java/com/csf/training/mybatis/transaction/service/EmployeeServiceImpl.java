package com.csf.training.mybatis.transaction.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.csf.training.mybatis.transaction.mapper.EmployeeMapper;
import com.csf.training.mybatis.transaction.model.Employee;
import com.csf.training.mybatis.transaction.config.Constants;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public Boolean updateEmployee(String empId, Employee emp) throws Exception{
		System.out.println("============= Controller =============");
		System.out.println(emp);
		String timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
        	employeeTransaction(empId, emp);

            this.transactionManager.commit(status);
            return true;
        } catch(RuntimeException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
	}

	private synchronized void employeeTransaction(String empId, Employee emp) throws Exception {
		int numAttempts = 0;
		System.out.println("=============SERVICE-START=============");
		System.out.println(emp);
		String timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		// Select for update.
		Employee employee = employeeMapper.selectEmployeeById(empId);
		System.out.println("=============SERVICE-SELECT=============");
		System.out.println(emp);
		timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		Thread.sleep(500);
		do {
			// Number of times fail.
			numAttempts++;
			try {
				// TODO case throw Exception
//				if (FLAG_THROW == 1) {
//					throw new RuntimeException();
//				}
				if (employee == null) {
					return;
				} else {
					// TODO: Business logic and not related to transaction.
					if (StringUtils.hasText(emp.getName())) {
						emp.setStart(1);
					}
					if (StringUtils.hasText(emp.getDept())) {
						if (emp.getStart() == 0)
							emp.setStart(2);
					}
					if (StringUtils.hasText(emp.getPhone())) {
						if (emp.getStart() == 0)
							emp.setStart(3);
					}
					if (StringUtils.hasText(emp.getAddress())) {
						if (emp.getStart() == 0)
							emp.setStart(4);
					}
					System.out.println("=============SERVICE-UPDATE-START=============");
					System.out.println(emp);
					timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
					System.out.println(timeStamp);
					employeeMapper.updateEmployeeInfo(emp);
					System.out.println("=============SERVICE-UPDATE-END=============");
					System.out.println(emp);
					timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
					System.out.println(timeStamp);
				}
				return;
			} catch (RuntimeException ex) {
				System.out.println("=============SERVICE-THROW-EXCEPTION=============");
				System.out.println(emp);
				timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSSSSS").format(Calendar.getInstance().getTime());
				System.out.println(timeStamp);
				Thread.sleep(1000); // waiting in 1 second
				if (numAttempts > Constants.MAX_RETRIES) {
					Constants.FLAG_THROW++;
					throw new RuntimeException("LIMIT TIME");
				}
				// Callback again.
			}
		} while (numAttempts <= Constants.MAX_RETRIES);
	}
}
