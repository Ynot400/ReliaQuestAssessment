package src.main.java.com.challenge.api.request;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter // Lombok library creates all the getters for EmployeeRequest class
@AllArgsConstructor // Lombok library creates a constructor with all the arguments for EmployeeRequest class
@EqualsAndHashCode // Lombok library creates equals and hashCode methods for EmployeeRequest class
@ToString // Lombok library creates a toString method for EmployeeRequest class if you call it
public class EmployeeRequest {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

}
