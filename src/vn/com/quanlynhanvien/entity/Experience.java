package vn.com.quanlynhanvien.entity;

public class Experience extends Employee {
    private int expInYear;
    private String proSkill;

    public Experience(int id, String fullName, String birthDay, String phone, String email, int expInYear, String proSkill) {
        super(id, fullName, birthDay, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }
    
    

    public int getExpInYear() {
		return expInYear;
	}



	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}



	public String getProSkill() {
		return proSkill;
	}



	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}



	@Override
    public void showInfo() {
        System.out.println("Experience Employee:");
        System.out.println("ID: " + id + ", Name: " + fullName + ", Experience: " + expInYear + " years, Skill: " + proSkill);
    }
}
