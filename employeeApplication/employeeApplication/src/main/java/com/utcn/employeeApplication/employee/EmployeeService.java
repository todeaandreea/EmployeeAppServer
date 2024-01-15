package com.utcn.employeeApplication.employee;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    @Transactional
    public Employee updateEmployee(Integer employeeID, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeID);

        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();

            // Verificăm dacă în cerere a fost furnizat un nou nume și, dacă da, îl setăm.
            if (updatedEmployee.getName() != null) {
                employee.setName(updatedEmployee.getName());
            }

            // Verificăm dacă în cerere a fost furnizat un nou departmentID și, dacă da, îl setăm.
            if (updatedEmployee.getDepartmentID() != null) {
                employee.setDepartmentID(updatedEmployee.getDepartmentID());
            }

            // Verificăm dacă în cerere a fost furnizat un nou managerID și, dacă da, îl setăm.
            if (updatedEmployee.getManagerID() != null) {
                employee.setManagerID(updatedEmployee.getManagerID());
            }

            // Verificăm dacă în cerere a fost furnizată o nouă adresă de email și, dacă da, o setăm.
            if (updatedEmployee.getEmail() != null) {
                employee.setEmail(updatedEmployee.getEmail());
            }

            return employeeRepository.save(employee);
        } else {
            return null; // Angajatul cu ID-ul specificat nu a fost găsit.
        }
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> getAllEmployeesPerDepartment(Integer departmentID) {
        return employeeRepository.findByDepartmentID(departmentID);
    }

    public List<Employee> getAllManagersPerDepartment(Integer departmentID) {
        List<Employee> allManagers = employeeRepository.findByDepartmentIDAndManagerIDIsNotNull(departmentID);

        List<Employee> filteredManagers = allManagers.stream()
                .filter(manager -> !manager.getManagerID().equals(0))
                .collect(Collectors.toList());

        return filteredManagers;
    }
}
