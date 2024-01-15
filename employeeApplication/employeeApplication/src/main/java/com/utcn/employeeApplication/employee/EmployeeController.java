package com.utcn.employeeApplication.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/byName")
    public List<Employee> getEmployeesByName(@RequestParam("name") String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/employeesByDepartment")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam Integer departmentID) {
        return employeeService.getAllEmployeesPerDepartment(departmentID);
    }

    @GetMapping("/managersByDepartment")
    public List<Employee> getAllManagersByDepartment(@RequestParam Integer departmentID) {
        return employeeService.getAllManagersPerDepartment(departmentID);
    }
}
