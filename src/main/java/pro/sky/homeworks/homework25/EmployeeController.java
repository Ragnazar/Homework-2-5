package pro.sky.homeworks.homework25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homeworks.homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.homeworks.homework25.exceptions.EmployeeNotFoundException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.setEmployee(firstName, lastName);
            return "{ \"firstName\": " + "\"" + firstName + "\"," + " \"lastName\": " + "\"" + lastName + "\"" + " }";
        } catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.deleteEmployee(firstName, lastName);
            return "{ \"firstName\": " + "\"" + firstName + "\"," + " \"lastName\": " + "\"" + lastName + "\"" + " }";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.getEmployee(firstName, lastName);
            return "{ \"firstName\": " + "\"" + firstName + "\"," + " \"lastName\": " + "\"" + lastName + "\"" + " }";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/list")
    public String getEmployees() {
        return employeeService.getEmployees();
    }
}