package src.main.java.com.challenge.api.controller;

import src.main.java.com.challenge.api.model.Employee;
import src.main.java.com.challenge.api.request.EmployeeRequest;
import src.main.java.com.challenge.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    // Add the missing service layer dependency
    @Autowired
    private EmployeeService employeeService;

    /**
     * @return One or more Employees.
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee models as necessary.
     */
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employeeList = employeeService.getAllEmployees();
            if (employeeList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(employeeList);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<Employee> getEmployeeByUuid(@PathVariable UUID uuid) {
        try {
            Employee employee = employeeService.getEmployeeByUuid(uuid);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(employee);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * @implNote Need not be concerned with an actual persistence layer.
     * @param employeeRequest Request body to create a new employee
     * @return Newly created Employee
     */
    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        try {
            Employee employee = employeeService.createEmployee(employeeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
