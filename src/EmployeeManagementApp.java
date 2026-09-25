import exception.EmployeeNotFoundException;
import model.Employee;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {
    private static final EmployeeService service = new EmployeeServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntInput("Enter choice (1-6): ");

            switch (choice) {
                case 1 -> handleAddEmployee();
                case 2 -> handleDisplayAll();
                case 3 -> handleSearchById();
                case 4 -> handleFilterByDepartment();
                case 5 -> handleFilterActiveHighSalary();
                case 6 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid selection. Please choose an option between 1 and 6.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void seedSampleData() {
        service.addEmployee(new Employee(101, "Alex Li", "Product Manager", 90000, true));
        service.addEmployee(new Employee(102, "Sheetal Jathar", "Sr Product Manager", 125000, true));
        service.addEmployee(new Employee(103, "Vincent", "Sr Devops", 140000, false));
        service.addEmployee(new Employee(104, "Jithin", "Sr Developer", 150000, true));
    }

    private static void displayMenu() {
        System.out.println("=========================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM        ");
        System.out.println("=========================================");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Filter Employees by Department");
        System.out.println("5. Filter Active Employees Above Salary");
        System.out.println("6. Exit");
        System.out.println("-----------------------------------------");
    }

    private static void handleAddEmployee() {
        System.out.println("\n--- Add New Employee ---");
        int id = readIntInput("Enter Employee ID: ");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        double salary = readDoubleInput("Enter Salary: ");
        boolean active = readBooleanInput("Is Active (true/false): ");

        try {
            service.addEmployee(new Employee(id, name, department, salary, active));
            System.out.println("Employee added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleDisplayAll() {
        System.out.println("\n--- All Employees ---");
        printTable(service.getAllEmployees());
    }

    private static void handleSearchById() {
        System.out.println("\n--- Search by ID ---");
        int id = readIntInput("Enter ID to search: ");
        try {
            Employee emp = service.findEmployeeById(id);
            printHeader();
            System.out.println(emp);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleFilterByDepartment() {
        System.out.println("\n--- Filter by Department ---");
        System.out.print("Enter Department Name: ");
        String dept = scanner.nextLine().trim();
        List results = service.findEmployeesByDepartment(dept);
        if (results.isEmpty()) {
            System.out.println("No employees found in department: " + dept);
        } else {
            printTable(results);
        }
    }

    private static void handleFilterActiveHighSalary() {
        System.out.println("\n--- Filter Active by Minimum Salary ---");
        double minSalary = readDoubleInput("Enter Minimum Salary Threshold: ");
        List results = service.findActiveEmployeesWithSalaryAbove(minSalary);
        if (results.isEmpty()) {
            System.out.println("No active employees found with salary above " + minSalary);
        } else {
            printTable(results);
        }
    }

    private static void printHeader() {
        System.out.printf("%-6s | %-12s | %-14s | %-10s | %s%n", "ID", "Name", "Department", "Salary", "Status");
        System.out.println("---------------------------------------------------------------");
    }

    private static void printTable(List employees) {
        if (employees.isEmpty()) {
            System.out.println("No records available.");
            return;
        }
        printHeader();
        employees.forEach(System.out::println);
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.nextLine();
            }
        }
    }

    private static boolean readBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("t") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("f") || input.equals("no") || input.equals("n")) {
                return false;
            }
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
        }
    }
}