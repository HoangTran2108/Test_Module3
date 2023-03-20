package com.example.test_module3.controller;

import com.example.test_module3.connection.ConnectionEmployee;
import com.example.test_module3.model.Department;
import com.example.test_module3.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeControllerIpm implements EmployeeController{
    private static final Connection connection;
    static {
        try {
            connection = new ConnectionEmployee().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String INSERT_EMPLOYEE_SQL = "   INSERT INTO employee (eName, email, address, phoneNumber, salary, dId) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id =?";
    private static final String SELECT_DEPARTMENT_BY_ID = "select * from department where id =?";
    private static final String SELECT_EMPLOYEE_BY_NAME = "   select * from employee where eName like ?;";
    private static final String DELETE_EMPLOYEE_SQL = "delete from employee where id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "update employee set eName = ?,email= ?, address =?, " +
            "phoneNumber = ?, salary = ?, dId = ? where id = ?;";
    private final String SELECT_ALL = "select * from employeemanager.employee join department on employee.dId = department.dId";
    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setObject(6, employee.getDepartment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee selectEmployee(int id) {
        Employee employee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String eName = rs.getString("eName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                double salary = rs.getDouble("salary");
                int dId = rs.getInt("dId");
                Department department = selectDepartment(dId);
                employee = new Employee(id, eName, email,address, phoneNumber, salary, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> selectAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        if(connection != null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("id");
                    String eName = rs.getString("eName");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phoneNumber");
                    double salary = rs.getDouble("salary");
                    int dId = rs.getInt("dId");
                    Department department = selectDepartment(dId);
                    employees.add(new Employee(id, eName, email,address, phoneNumber, salary, department));
                }
                return employees;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try ( PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getAddress());
            statement.setString(4, employee.getPhoneNumber());
            statement.setDouble(5, employee.getSalary());
            statement.setObject(6, employee.getDepartment());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public Department selectDepartment(int dId) {
        Department department = null;
        if (connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID);
                preparedStatement.setInt(1, dId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("dName");
                    department = new Department(dId, name);
                }
                return department;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Employee> find(String name) {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_NAME)) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String eName = rs.getString("eName");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                double salary = rs.getDouble("salary");
                int dId = rs.getInt("dId");
                Department department = selectDepartment(dId);
                employees.add(new Employee(id, eName, email,address, phoneNumber, salary, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
