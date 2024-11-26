package vn.com.quanlynhanvien.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.com.quanlynhanvien.entity.*;
import vn.com.quanlynhanvien.exception.*;
import vn.com.quanlynhanvien.utils.*;

public class EmployeeImpl implements EmployeeDao {

	private List<Employee> employees;

	public EmployeeImpl() {
		this.employees = new ArrayList<>();
	}

	@Override
	public void getAllEmployeesFormFile(String filePath) {

		// Read data from the CSV file
		List<String[]> dataList = FileUtils.readDataFromCSVFile(filePath);
		Employee employee;
		int ID = 1; // Employee ID counter

		// Process each row in the CSV file
		for (String[] row : dataList) {
			try {
				// Validate and extract employee information from the CSV row
				String fullName = CommonUtils.fullNameValidator(row[0]);
				String birthDay = CommonUtils.dateOfBirthValidator(row[1]);
				String phoneNumber = CommonUtils.phoneNumberValidator(row[2]);
				String email = CommonUtils.emailValidator(row[3]);
				int empType = Integer.parseInt(row[4]); // Employee type (0: Experience, 1: Fresher, 2: Intern)

				// Create an employee object based on employee type
				switch (empType) {
				case 0: // Experience
					int expInYear = Integer.parseInt(row[5]);
					String proSkill = row[6];
					employee = new Experience(ID, fullName, birthDay, phoneNumber, email, expInYear, proSkill);
					employees.add(employee);
					break;

				case 1: // Fresher
					String graduationDate = CommonUtils.dateOfBirthValidator(row[7]);
					String graduationRank = row[8];
					String education = row[9];
					employee = new Fresher(ID, fullName, birthDay, phoneNumber, email, graduationDate, graduationRank,
							education);
					employees.add(employee);
					break;

				case 2: // Intern
					String majors = row[10];
					String semester = row[11];
					String universityName = row[12];
					employee = new Intern(ID, fullName, birthDay, phoneNumber, email, majors, semester, universityName);
					employees.add(employee);
					break;

				default:
					// Handle invalid employee type
					System.err.println("Invalid employee type: " + empType);
					break;
				}
				ID++; // Increment employee ID for the next record
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				// Handle exceptions related to number format and array bounds
				System.err.println("Skipping invalid row: " + String.join(",", row));
			} catch (BirthDayException | FullNameException | PhoneException | EmailException e) {
				// Catch and handle specific validation exceptions
				System.err.println(e.getMessage());
			}
		}

	}

	@Override
	public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
            System.out.println("Employee added successfully: " + employee.getId());
        } else {
            System.out.println("Cannot add null employee.");
        }
    }

	@Override
	public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
        return null;
    }

	@Override
	public List<Employee> getAllEmployees() {
		return employees;
	}

	@Override
	public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == employee.getId()) {
                employees.set(i, employee);
                System.out.println("Employee updated successfully: " + employee.getId());
                return;
            }
        }
        System.out.println("Employee with ID " + employee.getId() + " not found.");
    }

	@Override
	public void deleteEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employees.remove(i);
                System.out.println("Employee deleted successfully: " + id);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

	@Override
	public void updateDateToDatabaseFormList(List<Employee> employees) {
		// TODO Auto-generated method stub
		Connection connection = DataBaseUtils.connect();
		if (connection == null) {
			System.out.println("Failed to establish a database connection.");
			return;
		}

		try {
			// Loop through the list of employees
			for (Employee employee : employees) {
				int id = DataBaseUtils.fetchId("SELECT MAX(id) FROM employees") + 1;

				String employeeSQL = String.format(
						"INSERT INTO employees (id, full_name, birth_date, phone, email, employee_type) VALUES ('%d', '%s', '%s', '%s', '%s', '%d')",
						id, employee.getFullName(), employee.getBirthDay(), employee.getPhone(), employee.getEmail(),
						(employee instanceof Experience ? 0 : employee instanceof Fresher ? 1 : 2));

				if (DataBaseUtils.execute(employeeSQL) > 0) {
					if (employee instanceof Experience) {
						String experienceSQL = String.format(
								"INSERT INTO experience_employees (id, exp_in_year, pro_skill) VALUES ('%d', '%d', '%s')",
								id, ((Experience) employee).getExpInYear(), ((Experience) employee).getProSkill());
						DataBaseUtils.execute(experienceSQL);
					} else if (employee instanceof Fresher) {
						String fresherSQL = String.format(
								"INSERT INTO fresher_employees (id, graduation_date, graduation_rank, education) VALUES ('%d', '%s', '%s', '%s')",
								id, ((Fresher) employee).getGraduationDate(), ((Fresher) employee).getGraduationRank(),
								((Fresher) employee).getEducation());
						DataBaseUtils.execute(fresherSQL);
					} else if (employee instanceof Intern) {
						String internSQL = String.format(
								"INSERT INTO intern_employees (id, majors, semester, university_name) VALUES ('%d', '%s', '%s', '%s')",
								id, ((Intern) employee).getMajors(), ((Intern) employee).getSemester(),
								((Intern) employee).getUniversityName());
						DataBaseUtils.execute(internSQL);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.disconnect();
		}

		DataBaseUtils.disconnect();
	}

}
