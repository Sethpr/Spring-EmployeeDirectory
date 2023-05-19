package io.zipcoder.persistenceapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/employees")
public class EmployeeController {
    List<Employee> employees = new ArrayList<>();

    public Employee getById(Integer id){
        for (Employee employee : employees) {
            if(employee.getNumber() == id){
                return employee;
            }
        }
        return null;
    }
    
    @PostMapping("")
    public void create(@RequestBody Employee employee){
        employees.add(employee);
    }

    @GetMapping(value = "/manager/{managerId}")
    public List<Employee> getByManager(@PathVariable("managerId") Integer managerId){
        return employees.stream().filter((e)->e.getManager().getNumber() == managerId).collect(Collectors.toList());
    }

    @GetMapping("/manager")
    public List<Employee> getNoManager(){
        return employees.stream().filter((e)->e.getManager() == null).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}/department")
    public Integer getDepartment(@PathVariable("id") Integer id){
        return getById(id).getDepartment();
    }

    @GetMapping(value = "/{id}/title")
    public String getTitle(@PathVariable("id") Integer id){
        return getById(id).getTitle();
    }

    @GetMapping(value = "/{id}/name")
    public String getName(@PathVariable("id") Integer id){
        return getById(id).getFirstName() + " " + getById(id).getLastName();
    }

    @GetMapping(value = "/{id}/firstname")
    public String getFirstName(@PathVariable("id") Integer id){
        return getById(id).getFirstName();
    }

    @GetMapping(value = "/{id}/lastname")
    public String getLastName(@PathVariable("id") Integer id){
        return getById(id).getLastName();
    }

    @GetMapping(value = "/{id}/phone")
    public String getPhoneNumber(@PathVariable("id") Integer id){
        return getById(id).getPhoneNumber();
    }

    @GetMapping(value = "/{id}/email")
    public String getEmail(@PathVariable("id") Integer id){
        return getById(id).getEmail();
    }

    @DeleteMapping(value = "/{id}")
    public void removeEmployee(@PathVariable("id") Integer id){
        employees.remove(getById(id));
    }
    
    @DeleteMapping(value = "manager/{id}")
    public void removeEmployeesUnderManager(@PathVariable("id") Integer id){
        employees = employees.stream().filter(e->e.getManager().getNumber() != id).collect(Collectors.toList());
    }
}
