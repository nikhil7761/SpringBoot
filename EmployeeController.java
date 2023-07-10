package in.niks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.niks.entity.Employee;
import in.niks.repository.EmployeeRepository;


@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@PostMapping("/employees")
	public String CreateNewEmployee(@RequestBody Employee employee) {
		
		employeerepository.save(employee);
		return "Employee create sucessfully";
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
	List<Employee>	empList=new ArrayList<>();
	employeerepository.findAll().forEach(empList::add);
	return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmplyeeById(@PathVariable int empid){
	Optional<Employee> emp=	employeerepository.findById(empid);
	if(emp.isPresent()) {
		return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);  
	}
	return new  ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable int empid,@RequestBody Employee employee ) {
		
		Optional<Employee> emp=employeerepository.findById(empid);
        if(emp.isPresent())
        {
        	Employee existEmployee=emp.get();
        	existEmployee.setEmp_age(employee.getEmp_age());
        	existEmployee.setEmp_city(employee.getEmp_city());
        	existEmployee.setEmp_name(employee.getEmp_name());
        	existEmployee.setEmp_salary(employee.getEmp_salary());

        	employeerepository.save(existEmployee);
        	
        	return "Employee sucessfully updated"+empid+"updated";
        	}else
        	return "employee details does not found"+empid;
		
	     }
	
	
	
	@DeleteMapping("/employees/{empid}")
	public String deleteEmplyeeById(@PathVariable int empid) {
		
		employeerepository.deleteById(empid);
		
		return "Employee Delete is sucessfully";
	}
	
	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		
		employeerepository.deleteAll();
		
		return "Employee delete sucessfully";
		
	}
	
}
