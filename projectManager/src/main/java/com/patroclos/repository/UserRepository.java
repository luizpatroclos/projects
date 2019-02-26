package com.patroclos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patroclos.entity.User;


//This annotation is used on Java classes which directly access the database. 
//The @Repository annotation works as marker for any class that fulfills the role of repository or Data Access Object.
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	

}
