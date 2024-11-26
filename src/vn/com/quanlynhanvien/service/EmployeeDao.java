package vn.com.quanlynhanvien.service;

import java.util.List;

import vn.com.quanlynhanvien.entity.Employee;

public interface EmployeeDao {

	/**
	 * Converts data from a CSV file into a list of Employee objects. Each row in
	 * the CSV is processed to create either an Experience, Fresher, or Intern
	 * object based on the employee type.
	 *
	 * @param filePath the path to the CSV file containing employee data
	 */
	void getAllEmployeesFormFile(String filePath);

	/**
	 * Adds a new employee to the data source.
	 *
	 * @param employee the Employee object to be added
	 */
	void addEmployee(Employee employee);

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id the unique identifier of the employee
	 * @return the Employee object with the specified ID
	 */
	Employee getEmployee(int id);

	/**
	 * Retrieves all employees from the data source.
	 *
	 * @return a list of all Employee objects
	 */
	List<Employee> getAllEmployees();

	/**
	 * Updates an existing employee's information.
	 *
	 * @param employee the Employee object with updated information
	 */
	void updateEmployee(Employee employee);

	/**
	 * Deletes an employee by their ID.
	 *
	 * @param id the unique identifier of the employee to be deleted
	 */
	void deleteEmployee(int id);
	
	/**
	 * Updates date to the database form list
	 *
	 * @param id the unique identifier of the employee to be deleted
	 */
	void updateDateToDatabaseFormList(List<Employee> employees);
}
