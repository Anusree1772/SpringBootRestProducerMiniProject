package com.app.service;

import java.util.List;

import com.app.entity.Student;
import com.app.exception.StudentNotFoundException;

public interface IStudentService {

	Integer saveStudent(Student s);
	void updateStudent(Student s) throws StudentNotFoundException;
	void deleteStudent(Integer id) throws StudentNotFoundException;
	Student getOneStudent(Integer id) throws StudentNotFoundException;
	List<Student> getAllStudents();
	
}
