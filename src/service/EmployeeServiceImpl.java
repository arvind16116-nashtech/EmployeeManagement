package service;

import exception.EmployeeNotFoundException;
import model.Employee;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    // Note the explicit  here
    private final Map<Integer, Employee> employeeMap = new LinkedHashMap<Integer, Employee>();

    @Override
    public void addEmployee(Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            throw new IllegalArgumentException("Employee with ID " + employee.getId() + " already exists.");
        }
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public List getAllEmployees() {
        return new ArrayList(employeeMap.values());
    }

    @Override
    public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeMap.get(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        return employee;
    }

    @Override
    public List findEmployeesByDepartment(String department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    @Override
    public List findActiveEmployeesWithSalaryAbove(double minSalary) {
        return employeeMap.values().stream()
                .filter(e -> e.isActive())
                .filter(e -> e.getSalary() > minSalary)
                .collect(Collectors.toList());
    }
}