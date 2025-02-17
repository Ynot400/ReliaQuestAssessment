package src.main.java.com.challenge.api.service;

import src.main.java.com.challenge.api.model.Employee;
// import src.main.java.com.challenge.api.repository.EmployeeRepository; // Assumes database setup
import src.main.java.com.challenge.api.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.NullType;
import java.time.Instant;
import java.util.*;

@Service
public class EmployeeService {

    /** The following repository is commented out because the database is not set up in this challenge.
     * @Autowired
     * private EmployeeRepository employeeRepository; // Assumes database setup
     *
     * If database were to be set up, the repository would be used to interact with the database.
     * The following methods would be used to interact with the database:
     * - employeeRepository.findAll()
     * - employeeRepository.findByUuid(uuid)
     * - employeeRepository.save(employee)
     */

    /** To avoid a non called variable error, a mock/useless variable is created */
    record employeeRepository = NullType;

    /**
     * Fetch all employees. If no DB, return mock data.
     */
    public List<Employee> getAllEmployees() {
        try {
            // The following try block would be attempted if the database was set up
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                throw new NoSuchElementException("No employees found in the database.");
            }
            return employees;
        } catch (Exception e) {
            // Fallback to mock data
            return Arrays.asList(
                    new Employee(UUID.randomUUID(), "John", "Doe", "John Doe", 100000, 30, "Software Engineer", "john.doe@example.com", Instant.now(), null),
                    new Employee(UUID.randomUUID(), "Jane", "Smith", "Jane Smith", 95000, 28, "Data Analyst", "jane.smith@example.com", Instant.now(), null)
            );
        }
    }

    /**
     * Fetch employee by UUID. If DB is unavailable, return mock data.
     */
    public Employee getEmployeeByUuid(UUID uuid) {
        try {
            return employeeRepository.findByUuid(uuid)
                    .orElseThrow(() -> new NoSuchElementException("Employee not found with UUID: " + uuid));
        } catch (Exception e) {
            // Return a mock employee
            return new Employee(uuid, "Fake", "Employee", "Fake Employee", 80000, 35, "Mock Position", "fake.employee@example.com", Instant.now(), null);
        }
    }

    /**
     * Create and save new employee.
     */
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(
                UUID.randomUUID(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getFullName(),
                employeeRequest.getSalary(),
                employeeRequest.getAge(),
                employeeRequest.getJobTitle(),
                employeeRequest.getEmail(),
                employeeRequest.getContractHireDate(),
                employeeRequest.getContractTerminationDate()
        );

        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            // If the database is unavailable, return the created employee without saving
            return employee;
        }
    }
}
