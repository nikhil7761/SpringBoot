package in.niks.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.niks.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {

}

