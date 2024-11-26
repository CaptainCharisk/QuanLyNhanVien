package vn.com.quanlynhanvien.entity;

public class Fresher extends Employee {
    private String graduationDate;
    private String graduationRank;
    private String education;

    public Fresher(int id, String fullName, String birthDay, String phone, String email, String graduationDate, String graduationRank, String education) {
        super(id, fullName, birthDay, phone, email, 1);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    
    
    public String getGraduationDate() {
		return graduationDate;
	}



	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}



	public String getGraduationRank() {
		return graduationRank;
	}



	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}



	public String getEducation() {
		return education;
	}



	public void setEducation(String education) {
		this.education = education;
	}



	@Override
    public void showInfo() {
        System.out.println("Fresher Employee:");
        System.out.println("ID: " + id + ", Name: " + fullName + ", Graduation Date: " + graduationDate + ", Rank: " + graduationRank + ", Education: " + education);
    }
}
