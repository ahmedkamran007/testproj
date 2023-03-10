package com.kami.teacher_restapi.teacher.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kami.teacher_restapi.teacher.error.TeacherException;
import com.kami.teacher_restapi.teacher.model.Teacher;
import com.kami.teacher_restapi.teacher.service.TeacherService;


@RestController
public class TeacherRestController {

	@Autowired
	TeacherService service;

	// POST - Create new teacher
	// http://localhost:8080/teachers
	@PostMapping(path = "/teachers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Teacher newTeacher) {
		System.out.println("saving  a teacher object ");

		ResponseEntity<?> resp = null;
		Teacher t = null;
		try {
			t = service.createOrUpdate(newTeacher);
			if (t != null) {
				resp = new ResponseEntity<Teacher>(t, HttpStatus.CREATED); // 201
			} else {
				resp = new ResponseEntity<String>("Could not create new teacher.", HttpStatus.BAD_REQUEST); // 400
			}
		} catch (Exception se) {
			se.printStackTrace();
			throw new TeacherException("Ops, something went wrong", HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		return resp;
	}
	
	// GET - ALL TEACHERS
		// http://localhost:8080/teachers
		@GetMapping("/teachers")
		public ResponseEntity<?> findAll() {
			ResponseEntity<?> resp = null;
			try {
				List<Teacher> list = new ArrayList<Teacher>();
				list = service.findAll();
				if (list != null && list.size() > 0) {
					resp = new ResponseEntity<List<Teacher>>(list, HttpStatus.FOUND);
				} else {
					resp = new ResponseEntity<String>("No record(s) found.", HttpStatus.NOT_FOUND);
				
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				
				throw new TeacherException("Oops,something went wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return resp;
		}
		
		// Get A TEACHER - http://localhost:8080/teachers/2
		@GetMapping(value = "teachers/{id}")
		public ResponseEntity<?> getTeacher(@PathVariable("id") int id) {
			ResponseEntity<?> response = null;
			try {
				Teacher t = service.findById(id);
				response = new ResponseEntity<Teacher>(t, HttpStatus.FOUND); // 302
			} catch (TeacherException te) {
				throw te;
			} catch (Exception se) {
				se.printStackTrace();
				response = new ResponseEntity<String>("Opps,something went wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return response;
		}
		
		// PUT - MODIFY EXISTING RESOURCE
		// http://localhost:8080/teachers/2
		@PutMapping(value = "/teachers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Teacher> update(@PathVariable("id") int id, @RequestBody Teacher te) {
			Teacher t = new Teacher();
			try {
				if (service.findById(id) != null) { // if the resource exists
					t.setId(id);
					if (service.allAttributesReceived(te)) {
						t.setfullName(te.getfullName());
						t.setEmail(te.getEmail());
						t.setCourse(te.getCourse());
						t = service.createOrUpdate(t);
					}
				}
				
		     // Resource Id does not exists - Create a new Resource.
				
			} catch (TeacherException texp) {
				throw texp;
			}
			return new ResponseEntity<Teacher>(t, HttpStatus.OK); // 200
		}
		
		// DELETE - A RESOURCE
		// http://localhost:8080/teachers/2
		@DeleteMapping(value = "/teachers/{id}")
		public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
			ResponseEntity<String> resp = null;
			try {
				if (service.findById(id) != null) {
					service.deleteById(id);
					resp = new ResponseEntity<String>("Teacher '" + id + "' deleted", HttpStatus.OK);
				}
			} catch (TeacherException t) {
				throw t;
			} catch (Exception e) {
				e.printStackTrace();
				resp = new ResponseEntity<String>("Unable to delete teacher id = " + id, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return resp;

		}

		// Delete- ALL TEACHERS
		// http://localhost:8080/teachers
		@DeleteMapping("/teachers")
		public ResponseEntity<String> deleteAll() {
			ResponseEntity<String> resp = null;
			try {
				service.deleteAll();
				resp = new ResponseEntity<String>("All Record deleted.", HttpStatus.OK);
			} catch (Exception ex) {
				ex.printStackTrace();
				resp = new ResponseEntity<String>("Unable to delete record(s)", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return resp;
		}

}
