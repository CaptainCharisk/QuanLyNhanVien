package vn.com.quanlynhanvien;

import java.util.List;
import java.util.Scanner;

import vn.com.quanlynhanvien.entity.*;
import vn.com.quanlynhanvien.exception.BirthDayException;
import vn.com.quanlynhanvien.exception.EmailException;
import vn.com.quanlynhanvien.exception.FullNameException;
import vn.com.quanlynhanvien.exception.PhoneException;
import vn.com.quanlynhanvien.service.*;
import vn.com.quanlynhanvien.utils.*;

public class QuanLyNhanVienApplication {

	public static void main(String[] args) {
		
		// Create an instance of EmployeeDao to interact with employee data
        EmployeeDao emp = new EmployeeImpl();

		// Load employees from a CSV file into the data source
		emp.getAllEmployeesFormFile("danh_sach_nhan_vien_new.csv");

		// Retrieve the list of all employees and display their information
		/*List<Employee> empList = emp.getAllEmployees();
		for (Employee employee : empList) {
			employee.showInfo(); // Show information of each employee
		}*/

		// emp.updateDateToDatabaseFormList(empList);
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========== Employee Management ==========");
            System.out.println("1. Add a new Employee");
            System.out.println("2. Update Employee by ID");
            System.out.println("3. Delete Employee by ID");
            System.out.println("4. Get Employee by ID");
            System.out.println("5. Show all Employees");
            System.out.println("6. Update to Database from List");
            System.out.println("7. Exit");
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addNewEmployee(emp, scanner);
                    break;
                case 2:
                    updateEmployee(emp, scanner);
                    break;
                case 3:
                    deleteEmployee(emp, scanner);
                    break;
                case 4:
                    getEmployeeById(emp, scanner);
                    break;
                case 5:
                    showAllEmployees(emp);
                    break;
                case 6:
                	List<Employee> empList = emp.getAllEmployees();
                	emp.updateDateToDatabaseFormList(empList);
                	break;
                case 7:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

	private static void addNewEmployee(EmployeeDao emp, Scanner scanner) {
        System.out.println("Choose Employee type:");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Intern");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        List<Employee> empList = emp.getAllEmployees();
        int maxId = empList.stream().mapToInt(Employee::getId).max().orElse(0);
        int newId = maxId + 1;
   
		try {
			System.out.print("Enter Full Name: ");
	        String fullName = CommonUtils.fullNameValidator(scanner.nextLine());
	        System.out.print("Enter Birth Day: ");
	        String birthDay = CommonUtils.dateOfBirthValidator(scanner.nextLine());
	        System.out.print("Enter Phone: ");
	        String phone = CommonUtils.phoneNumberValidator(scanner.nextLine());
	        System.out.print("Enter Email: ");
	        String email = CommonUtils.emailValidator(scanner.nextLine());

	        switch (type) {
	            case 1:
	                System.out.print("Enter Years of Experience: ");
	                int expInYear = scanner.nextInt();
	                scanner.nextLine(); // Consume newline character
	                System.out.print("Enter Professional Skill: ");
	                String proSkill = scanner.nextLine();

	                Employee experience = new Experience(newId, fullName, birthDay, phone, email, expInYear, proSkill);
	                emp.addEmployee(experience);
	                break;

				case 2:
					System.out.print("Enter Graduation Date: ");
					String gradDate = CommonUtils.dateOfBirthValidator(scanner.nextLine()); // Placeholder, parse the input if needed
					System.out.print("Enter Graduation Rank: ");
					String gradRank = scanner.nextLine();
					System.out.print("Enter Education: ");
					String education = scanner.nextLine();

					Employee fresher = new Fresher(newId, fullName, birthDay, phone, email, gradDate, gradRank, education);
					emp.addEmployee(fresher);
					break;

	            case 3:
	                System.out.print("Enter Majors: ");
	                String majors = scanner.nextLine();
	                System.out.print("Enter Semester: ");
	                String semester = scanner.nextLine();
	                System.out.print("Enter University Name: ");
	                String universityName = scanner.nextLine();

	                Employee intern = new Intern(newId, fullName, birthDay, phone, email, majors, semester, universityName);
	                emp.addEmployee(intern);
	                break;

	            default:
	                System.out.println("Invalid employee type.");
	        }
		} catch (BirthDayException | FullNameException | PhoneException | EmailException e) {
			// Catch and handle specific validation exceptions
			System.err.println(e.getMessage());
		}
    }

    private static void updateEmployee(EmployeeDao emp, Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        String id = scanner.nextLine();
        Employee employee = emp.getEmployee(Integer.parseInt(id));
        try {
        	if (employee != null) {
                System.out.print("Enter Full Name: ");
                String fullName = CommonUtils.fullNameValidator(scanner.nextLine());
                System.out.print("Enter Phone: ");
                String phone = CommonUtils.phoneNumberValidator(scanner.nextLine());
                System.out.print("Enter Email: ");
                String email = CommonUtils.emailValidator(scanner.nextLine());

                employee.setFullName(fullName);
                employee.setPhone(phone);
                employee.setEmail(email);

                emp.updateEmployee(employee);
            } else {
                System.out.println("Employee not found.");
            }
        }catch ( FullNameException | PhoneException | EmailException e) {
			// Catch and handle specific validation exceptions
			System.err.println(e.getMessage());
		}
    }

    private static void deleteEmployee(EmployeeDao emp, Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        emp.deleteEmployee(id);
    }

    private static void getEmployeeById(EmployeeDao emp, Scanner scanner) {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();
        Employee employee = emp.getEmployee(id);
        if (employee != null) {
            employee.showInfo();
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void showAllEmployees(EmployeeDao emp) {
    	List<Employee> empList = emp.getAllEmployees();
		for (Employee employee : empList) {
			employee.showInfo(); // Show information of each employee
		}
    }
}
