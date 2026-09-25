# EmployeeManagement
Employee Management modular, well-structured Java-based console application that fulfills all requirements, utilizes standard object-oriented design, demonstrates modern Java 8+ features, and handles edge cases gracefully

## Features
- **Add Employee**: Validates against duplicate IDs.
- **Display All**: Displays complete records in a clean tabular view.
- **Search by ID**: Retrieves employee details using an exact ID, raising a custom `EmployeeNotFoundException` if unavailable.
- **Department Filter**: Uses Java Streams to query employees belonging to a specified department (case-insensitive).
- **Active Salary Threshold Filter**: Uses Stream pipelines with multiple filters (`filter(Employee::isActive)` and `filter(e -> e.getSalary() > threshold)`).

## Requirements
- Java Development Kit (JDK) 17 or higher (or JDK 8+ with traditional switch-case statements).

## Instructions to Run

### Option 1: Direct Command Line (Terminal)
1. Navigate to the root directory where the `src` folder is located:
   ```bash
   cd path/to/project