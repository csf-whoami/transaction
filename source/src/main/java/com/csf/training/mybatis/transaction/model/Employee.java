package com.csf.training.mybatis.transaction.model;

public class Employee {

	private String uuid;
	private String name;
	private String dept;
	private String phone;
	private String address;
	private int start = 0;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Employee [uuid=" + uuid + ", name=" + name + ", dept=" + dept + ", phone=" + phone + ", address="
				+ address + ", start=" + start + "]";
	}
}
