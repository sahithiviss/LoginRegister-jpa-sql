package com.dbs.springmvcapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.springmvcapp.model.Employee;

public interface EmployeeDAO extends CrudRepository<Employee, String> {
	Employee findByName(String name);
	Employee findByPassword(String password);
}