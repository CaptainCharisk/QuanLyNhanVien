package vn.com.quanlynhanvien.entity;

public class Intern extends Employee {
    private String majors;
    private String semester;
    private String universityName;

    public Intern(int id, String fullName, String birthDay, String phone, String email, String majors, String semester, String universityName) {
        super(id, fullName, birthDay, phone, email, 2);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }
    
    

    public String getMajors() {
		return majors;
	}



	public void setMajors(String majors) {
		this.majors = majors;
	}



	public String getSemester() {
		return semester;
	}



	public void setSemester(String semester) {
		this.semester = semester;
	}



	public String getUniversityName() {
		return universityName;
	}



	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}



	@Override
    public void showInfo() {
        System.out.println("Intern Employee:");
        System.out.println("ID: " + id + ", Name: " + fullName + ", Majors: " + majors + ", Semester: " + semester + ", University: " + universityName);
    }
}