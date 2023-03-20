package com.example.test_module3.controller;

import com.example.test_module3.model.Department;
import com.example.test_module3.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeController {
    void insertEmployee(Employee employee) throws SQLException;

    Employee selectEmployee(int id);

    List<Employee> selectAllEmployee();

    boolean deleteEmployee(int id) throws SQLException;

    boolean updateEmployee(Employee employee) throws SQLException;
    Department selectDepartment(int dId);
    List<Employee> find(String name);

}
