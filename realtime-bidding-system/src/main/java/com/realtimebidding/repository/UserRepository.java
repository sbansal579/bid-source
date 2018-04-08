package com.realtimebidding.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.realtimebidding.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	// public interface UserRepository extends CrudRepository<User, String> {

	User findById(String id);

	User findByFirstName(String name);

	User findByEmail(String email);
}
