package com.utcn.employeeApplication.department;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer departmentID) {
        return departmentRepository.findById(departmentID)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + departmentID));
    }

    @Transactional
    public Department updateDepartment(Integer departmentID, Department updatedDepartment) {
        Optional<Department> existingDepartment = departmentRepository.findById(departmentID);

        if (existingDepartment.isPresent()) {
            Department department = existingDepartment.get();

            // Verificăm dacă în cerere a fost furnizată o nouă descriere și, dacă da, o setăm.
            if (updatedDepartment.getDescription() != null) {
                department.setDescription(updatedDepartment.getDescription());
            }

            // Verificăm dacă în cerere a fost furnizat un nou parentID și, dacă da, îl setăm.
            if (updatedDepartment.getParentID() != null) {
                department.setParentID(updatedDepartment.getParentID());
            }

            // Verificăm dacă în cerere a fost furnizat un nou managerID și, dacă da, îl setăm.
            if (updatedDepartment.getManagerID() != null) {
                department.setManagerID(updatedDepartment.getManagerID());
            }

            return departmentRepository.save(department);
        } else {
            return null; // Departamentul cu ID-ul specificat nu a fost găsit.
        }
    }

    @Transactional
    public void deleteDepartment(Integer departmentID) {
        departmentRepository.deleteById(departmentID);
    }

    public List<Department> getDepartmentsByParentID(Integer parentID) {
        return departmentRepository.findByParentID(parentID);
    }
}
