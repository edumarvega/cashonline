package com.broker.cashonline.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.broker.cashonline.entity.Loan;
import com.broker.cashonline.entity.User;
import com.broker.cashonline.exception.ResourceNotFoundException;
import com.broker.cashonline.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {

		logger.debug("inside UserController.getAllUsers() method");
		List<User> users = this.userRepository.findAll();

		if (users.isEmpty()) {
			logger.warn("No se encontro resultado.");
			throw new ResourceNotFoundException("No se encontro resultado.");
		}

		return new ResponseEntity<>(users, HttpStatus.OK);

	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id")long id ){
		logger.debug("inside UserController.getUserById() method");
		Optional<User> optional = this.userRepository.findById(id);
		
		if(optional.isPresent()) {
			return new ResponseEntity<>(optional.get(),HttpStatus.OK);
		} else {
			logger.warn("No se encontro resultado con el id: "+id);
			throw new ResourceNotFoundException("No se encontro resultado con el id: "+id);
		}
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
			logger.debug("inside UserController.createUser() method");
			User userData;
			if(user.getLoans()==null) {
				userData = new User(user.getEmail(), user.getFirstName(), user.getLastName());
				logger.debug("{}", userData);
				userData = this.userRepository.save(userData);
			} else {
				userData = new User(user.getEmail(), user.getFirstName(), user.getLastName());
				userData.setLoans(new ArrayList());
				for (Loan tmp : user.getLoans()) {
					Loan loan = new Loan();
		        	loan.setTotal(tmp.getTotal());
		        	loan.setUser(userData);
		        	userData.getLoans().add(loan);
				}
				logger.debug("{}", userData);
				userData = this.userRepository.save(userData);
			}
			return new ResponseEntity<>(userData, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
		logger.debug("inside UserController.updateUser() method");
		Optional<User> optional = this.userRepository.findById(id);
		if(optional.isPresent()){
			User userData = optional.get();
			userData.setEmail(user.getEmail());
			userData.setFirstName(user.getFirstName());
			userData.setLastName(user.getLastName());
			logger.debug("{}", userData);
			return new ResponseEntity<>(this.userRepository.save(userData),HttpStatus.OK);
		} else {
			logger.warn("No se encontro resultado con el id: "+id);
			throw new ResourceNotFoundException("No se encontro resultado con el id: "+id);
		}
	}
	
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {

		logger.debug("inside UserController.deleteUser() method");
		this.userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUser() {
		logger.debug("inside UserController.deleteAllUser() method");
		this.userRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	
	
}
