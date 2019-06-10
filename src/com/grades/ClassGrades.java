package com.grades;

public class ClassGrades {
	private int id;
	private int studentId;
	private int teacherId;
	private int classId;
	private int grade1;
	private int grade2;
	private int finalGrade;
	private String finalLetterGrade;
	
	public ClassGrades(int id, int studentId, int techaerId, int classId, int grade1, int grade2, int finalGrade) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.teacherId = techaerId;
		this.classId = classId;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.finalGrade = finalGrade;
	}
	public ClassGrades(int studentId, int techaerId, int classId, int grade1, int grade2, int finalGrade) {
		super();
		this.studentId = studentId;
		this.teacherId = techaerId;
		this.classId = classId;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.finalGrade = finalGrade;
	}
	
	
	
	public ClassGrades(int studentId, int teacherId, int classId, int grade1, int grade2, int finalGrade,
			String finalLetterGrade) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.classId = classId;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.finalGrade = finalGrade;
		this.finalLetterGrade = finalLetterGrade;
	}
	public ClassGrades(int id, int studentId, int teacherId, int classId, int grade1, int grade2, int finalGrade,
			String finalLetterGrade) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.classId = classId;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.finalGrade = finalGrade;
		this.finalLetterGrade = finalLetterGrade;
	}
	
	
	public ClassGrades(int studentId, int grade1, int grade2, int finalGrade, String finalLetterGrade) {
		super();
		this.studentId = studentId;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.finalGrade = finalGrade;
		this.finalLetterGrade = finalLetterGrade;
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
	public String getFinalLetterGrade() {
		return finalLetterGrade;
	}
	public void setFinalLetterGrade(String finalLetterGrade) {
		this.finalLetterGrade = finalLetterGrade;
	}
	public int getTechaerId() {
		return teacherId;
	}
	public void setTechaerId(int techaerId) {
		this.teacherId = techaerId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getGrade1() {
		return grade1;
	}
	public void setGrade1(int grade1) {
		this.grade1 = grade1;
	}
	public int getGrade2() {
		return grade2;
	}
	public void setGrade2(int grade2) {
		this.grade2 = grade2;
	}
	public int getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}
	
	@Override
	public String toString() {
		return "StudentsAndClasses [id=" + id + ", studentId=" 
				+ studentId + ", classId=" + classId + ","
				+ " teacherId=" + teacherId + ", Grade1=" + grade1 + ","
				+ " Grade1=" + grade2 + ", FinalGrade =" + finalGrade + "]";
	}
}
