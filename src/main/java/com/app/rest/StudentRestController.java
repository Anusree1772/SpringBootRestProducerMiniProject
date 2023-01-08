package com.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.exception.StudentNotFoundException;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/v1/api/student")
public class StudentRestController {

	@Autowired
	IStudentService service;

	@PostMapping("/create")
	public ResponseEntity<String> createStudent(@RequestBody Student student)
	{
		Integer id=service.saveStudent(student);
		String message="STUDENT'"+ id +"'CREATED";

		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		List<Student> list=service.getAllStudents();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable ("id") Integer id) throws StudentNotFoundException
	{
		ResponseEntity<Student> response=null;
		try {
			Student s = service.getOneStudent(id);

			response=ResponseEntity.ok(s);

		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") Integer id) throws StudentNotFoundException
	{
		ResponseEntity<String> response = null;
		try {
			service.deleteStudent(id);
			response = ResponseEntity.ok("STUDENT '"+id+"' REMOVED");
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modifyStudent(@RequestBody Student student) throws StudentNotFoundException
	{
		ResponseEntity<String> response=null;
		
		try {
			service.updateStudent(student);
			response=ResponseEntity.ok("STUDENT'"+student.getStdId()+"'HAS BEEN UPDATED");
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
