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
import com.broker.cashonline.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		try {
			List<User> users = this.userRepository.findAll();
			
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(users,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id")long id ){
		
		Optional<User> optional = this.userRepository.findById(id);
		
		if(optional.isPresent()) {
			return new ResponseEntity<>(optional.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User userData;
		try {
			if(user.getLoans()==null) {
				userData = this.userRepository.save(new User(user.getEmail(), user.getFirstName(), user.getLastName()));
			} else {
				userData = new User(user.getEmail(), user.getFirstName(), user.getLastName());
				userData.setLoans(new ArrayList());
				for (Loan tmp : user.getLoans()) {
					Loan loan = new Loan();
		        	loan.setTotal(tmp.getTotal());
		        	loan.setUser(userData);
		        	userData.getLoans().add(loan);
				}
				userData = this.userRepository.save(userData);
			}
			return new ResponseEntity<>(userData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
		Optional<User> optional = this.userRepository.findById(id);
		if(optional.isPresent()){
			User userData = optional.get();
			userData.setEmail(user.getEmail());
			userData.setFirstName(user.getFirstName());
			userData.setLastName(user.getLastName());
			return new ResponseEntity<>(this.userRepository.save(userData),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
		try {
			this.userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUser(){
		try {
			this.userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	
}
