package vn.com.quanlynhanvien.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
	
	private static int employeeCount = 0;
    protected int id;
    protected String fullName;
    protected String birthDay;
    protected String phone;
    protected String email;
    protected int employeeType;
    protected List<Certificate> certificates;

    public Employee() {}
    public Employee(int id, String fullName, String birthDay, String phone, String email, int employeeType) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.employeeType = employeeType;
        this.certificates = new ArrayList<>();
        employeeCount++;
    }
	
    

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}
	public static void setEmployeeCount(int employeeCount) {
		Employee.employeeCount = employeeCount;
	}
	public static int getEmployeeCount() {
        return employeeCount;
    }

    public abstract void showInfo();

    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
    }
}
