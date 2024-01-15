package com.utcn.employeeApplication.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.create(department);
    }
    @PutMapping("/{departmentID}")
    public Department updateDepartment(@PathVariable Integer departmentID, @RequestBody Department updatedDepartment) {

        return departmentService.updateDepartment(departmentID, updatedDepartment);
    }

    @DeleteMapping("/{departmentID}")
    public void deleteDepartment(@PathVariable Integer departmentID) {
        departmentService.deleteDepartment(departmentID);
    }

    @GetMapping("/{departmentID}")
    public Department getDepartment(@PathVariable Integer departmentID) {
        return departmentService.getDepartmentById(departmentID);
    }
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/byParentID")
    public List<Department> getDepartmentsByParentID(@RequestParam("parentID") Integer parentID) {
        return departmentService.getDepartmentsByParentID(parentID);
    }

}