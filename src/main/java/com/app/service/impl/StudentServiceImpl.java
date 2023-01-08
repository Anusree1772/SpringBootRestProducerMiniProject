package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Student;
import com.app.exception.StudentNotFoundException;
import com.app.repository.StudentRepository;
import com.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepository repo;
	
	@Override
	public Integer saveStudent(Student s) {
		
		s=repo.save(s);
		
		return s.getStdId();
	}

	@Override
	public void updateStudent(Student s) throws StudentNotFoundException {
	
		if(s.getStdId()==null || !repo.existsById(s.getStdId()))
		{
			throw new StudentNotFoundException("STUDENT'"+s.getStdId()+"'DOES NOT EXISTS");
		}
		else
		{
			repo.save(s);
		}

	}

	@Override
	public void deleteStudent(Integer id) throws StudentNotFoundException {
	
		repo.delete(getOneStudent(id));
		
		//repo.deleteById(id);
	}

	@Override
	public Student getOneStudent(Integer id) throws StudentNotFoundException {
		
		return repo.findById(id)
				.orElseThrow(
						()->new StudentNotFoundException("STUDENT '"+id+"' NOT EXIST")
						);
	}

	@Override
	public List<Student> getAllStudents() {
		
		List<Student> list=repo.findAll();
		
		return list;
	}

}
