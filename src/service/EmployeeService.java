package service;

import exception.EmployeeNotFoundException;
import model.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    List getAllEmployees();
    Employee findEmployeeById(int id) throws EmployeeNotFoundException;
    List findEmployeesByDepartment(String department);
    List findActiveEmployeesWithSalaryAbove(double minSalary);
}