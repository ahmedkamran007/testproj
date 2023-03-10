package com.kami.teacher_restapi.teacher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kami.teacher_restapi.teacher.error.TeacherException;
import com.kami.teacher_restapi.teacher.model.Teacher;
import com.kami.teacher_restapi.teacher.repo.TeacherRepository;


@Service
public class TeacherService {

	@Autowired
	TeacherRepository repo;

	public Teacher findById(int id) {
		Optional<Teacher> t = repo.findById(id);
		if(t.isPresent()) {
			return t.get();
		}else {
			throw new TeacherException("No record found", HttpStatus.NOT_FOUND);
		}
	}

	public List<Teacher> findAll() {
		List<Teacher> list = new ArrayList<Teacher>();
		Iterable<Teacher> all = repo.findAll();
		all.forEach(list::add);
		return list;
	}

	public Teacher createOrUpdate(Teacher newTeacher) {
	
		Teacher t = repo.save(newTeacher);
		
		return t;
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}

		
	
	public boolean allAttributesReceived(Teacher t) {
		if (t.getfullName() != null && t.getfullName() != "" 
				&& t.getEmail() != null && t.getEmail() != "") {
			return true;
		} else {
			throw new TeacherException("Missing content", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteAll() {
		repo.deleteAll();
	}

	
	
	
}
