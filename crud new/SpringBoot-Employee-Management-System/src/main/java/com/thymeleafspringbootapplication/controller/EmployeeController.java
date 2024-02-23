package com.thymeleafspringbootapplication.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymeleafspringbootapplication.model.Employee;
import com.thymeleafspringbootapplication.service.EmployeeService;
import com.thymeleafspringbootapplication.model.Departs;
import com.thymeleafspringbootapplication.service.DepartService;
@Controller
public class EmployeeController {
	
	@Autowired	
   private EmployeeService employeeService;
	
	@Autowired
    DepartService departService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("departs", employeeService.findAllDeparts());
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}
	

	@GetMapping("/search")
	public String search(Model model,@RequestParam("id") String departID) {
		model.addAttribute("departs", employeeService.findAllDeparts());
		model.addAttribute("selected", departID);
		model.addAttribute("listEmployees", departID.equals("0") ? employeeService.getAllEmployees() : employeeService.getAllEmployees().stream().filter(n ->n.getDeparts() != null && n.getDeparts().getId().equals( departID)).collect(Collectors.toList()));
		return "index";
	}
	
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		Employee emp = new Employee();
		String departID = employee.getDepartID();
		if(employee.getId() != null) {
			emp = employeeService.getEmployeeById(employee.getId());
		}
		if(departID == null) {
			departID = emp.getDeparts().getId();
		}
		Departs depart = departService.findById(departID).get();
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		emp.setDepartID(departID);
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@ModelAttribute(name = "DEPARTS")
	public List<Departs> getAllDeparts(){
		return employeeService.findAllDeparts();
	}

}
