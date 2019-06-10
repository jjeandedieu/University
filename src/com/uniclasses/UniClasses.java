package com.uniclasses;

public class UniClasses {
	private int id;
	private String className;
	private String classTime;
	private String Professor;
	private String classLevel;
	private int seatsAvailable;
	private String classDay;
	private int professorId;

	private int[] studentPlusClasses;
	
	public UniClasses(int professorId) {
		super();
		this.professorId = professorId;
	}
	
	public UniClasses(int id, String className, String classTime, String professor, String classLevel, int seatsAvailable) {
		super();
		this.id = id;
		this.className = className;
		this.classTime = classTime;
		this.Professor = professor;
		this.seatsAvailable = seatsAvailable;
		this.classLevel = classLevel;
	}
	public UniClasses(String className, String classTime, String professor, String classLevel, int seatsAvailable) {
		super();
		this.className = className;
		this.classTime = classTime;
		this.Professor = professor;
		this.classLevel = classLevel;
		this.seatsAvailable = seatsAvailable;
	}
	
	
	
	public UniClasses(int id, String className, String classTime, String professor, String classLevel,
			int seatsAvailable, String classDay, int[] studentPlusClasses) {
		super();
		this.id = id;
		this.className = className;
		this.classTime = classTime;
		Professor = professor;
		this.classLevel = classLevel;
		this.seatsAvailable = seatsAvailable;
		this.classDay = classDay;
		this.studentPlusClasses = studentPlusClasses;
	}
	public UniClasses() {
		super();
	}

	public UniClasses(int id, String className, String classTime, String classLevel, int seatsAvailable, String classDay,
			int professorId) {
		super();
		this.id = id;
		this.className = className;
		this.classTime = classTime;
		this.classLevel = classLevel;
		this.seatsAvailable = seatsAvailable;
		this.classDay = classDay;
		this.professorId = professorId;
	}

	public UniClasses(int id2, String class_name, String class_time, String class_level, int seats_avaialable) {
		// TODO Auto-generated constructor stub
	}

	public String getClassDay() {
		return classDay;
	}
	public void setClassDay(String classDay) {
		this.classDay = classDay;
	}
	public int[] getStudentPlusClasses() {
		return studentPlusClasses;
	}
	public void setStudentPlusClasses(int[] studentPlusClasses) {
		this.studentPlusClasses = studentPlusClasses;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassLevel() {
		return classLevel;
	}
	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getProfessor() {
		return Professor;
	}
	public void setProfessor(String professor) {
		Professor = professor;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	
	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", className=" + className + ", classTime=" + classTime + ", professor=" + Professor + ", classLevel=" + classLevel + ", seatsAvailable=" + seatsAvailable + "]";
	}
	
	
	
	

}
