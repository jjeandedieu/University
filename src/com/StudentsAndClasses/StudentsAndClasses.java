package com.StudentsAndClasses;

public class StudentsAndClasses {
	private int id;
	private int studentId;
	private int classId;
	
	public StudentsAndClasses(int id, int studentId, int classId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.classId = classId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "StudentsAndClasses [id=" + id + ", studentId=" + studentId + ", classId=" + classId + "]";
	}
	
	

}
