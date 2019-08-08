package com.dbs.springmvcapp.controller;

import com.dbs.springmvcapp.model.Employee;
import com.dbs.springmvcapp.repository.EmployeeDAO;
import com.dbs.springmvcapp.repository.EmployeeRepository;
import com.dbs.springmvcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeDAO dao;
//    @Autowired
//    private EmployeeRepository repo1;
    @GetMapping(value = "/login")
    public String login(Model model){
        System.out.println("Came inside the login method ");
        List<Employee> listOfAllEmployees  = employeeService.listAll();
        listOfAllEmployees.forEach(employee ->  System.out.println(employee));
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,Employee employee){
        System.out.println("Inside the POST method of login user");
        System.out.println("Username is "+username + " password is "+ password);
//        if(username.equalsIgnoreCase(password)){
//            model.addAttribute("user", username);
//            return "success";
//        }
        if(dao.findByName(username) != null&&dao.findByPassword(password)!=null
        		&&dao.findByName(username).getId()==dao.findByPassword(password).getId()) {
        	model.addAttribute("user", username);
            return "success";
        }
        return "register";
    }

    @GetMapping("/listAll")
    public String listAllEmployees(Model model){
        List<Employee> employees = this.employeeService.listAll();
        this.employeeService.listAll().forEach(System.out::println);
        model.addAttribute("employees", employees);
        return "list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("employee", new Employee());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser( @Valid @ModelAttribute("employee") Employee employee) {
        this.employeeService.saveEmployee(employee);
        return "acknowledge";
    }

}