package com.example.keycloak.springbootkeycloak.controller;

import com.example.keycloak.springbootkeycloak.model.Employee;
import com.example.keycloak.springbootkeycloak.repository.EmployeeRepository;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    @RolesAllowed("user")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(HttpServletRequest httpServletRequest){
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) httpServletRequest.getUserPrincipal();
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/private")
    public String getPrivateResponse(HttpServletRequest httpServletRequest){
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) httpServletRequest.getUserPrincipal();

        return "This is a private page";
    }
}
