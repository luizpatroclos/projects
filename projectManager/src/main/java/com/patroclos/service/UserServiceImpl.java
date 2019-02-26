package com.patroclos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patroclos.entity.User;
import com.patroclos.repository.UserRepository;


//The @Service marks a Java class that performs some service, 
//such as execute business logic, perform calculations and call external APIs. 
@Service

// The mere presence of @Transactional is not enough to activate the transactional behaviour. 
//The @Transactional is simply a metadata that can be consumed by some runtime infrastructure. 
//This infrastructure uses the metadata to configure the appropriate beans with transactional behaviour.
@Transactional
public class UserServiceImpl implements UserService {

	
	// Implementing Constructor based DI
			private UserRepository repository;
			
			public UserServiceImpl() {
				
			}
			
			@Autowired
			public UserServiceImpl(UserRepository repository) {
				super();
				this.repository = repository;
			}
			
		@Override
		public List getAllUsers() {
			List list = new ArrayList();
			repository.findAll().forEach(e -> list.add(e));
			return list;
		}

		@Override
		public User getUserById(Long id) {
			User user = repository.findById(id).get();
			return user;
		}

		@Override
		public boolean saveUser(User user) {
			try {
				repository.save(user);
				return true;
			}catch(Exception ex) {
				return false;
			}
		}

		@Override
		public boolean deleteUserById(Long id) {
			try {
				repository.deleteById(id);
				return true;
			}catch(Exception ex) {
				return false;
			}
			
		}
	
}
