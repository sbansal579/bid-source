package com.realtimebidding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realtimebidding.dtos.Response;
import com.realtimebidding.dtos.ResponseTypeEnum;
import com.realtimebidding.model.User;
import com.realtimebidding.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public Response<?> addNewUser(@RequestBody User user) {

		try {
			return new Response<User>(ResponseTypeEnum.SUCCESS, userService.addNewUser(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	/* @RequestMapping(value = "/{userId}", method = RequestMethod.GET) */
	@GetMapping(value = "{userId}")
	public Response<?> getUserById(@PathVariable("userId") String userId) {
		try {

			return new Response<User>(ResponseTypeEnum.SUCCESS, userService.getById(userId));

		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	// @RequestMapping(value = "/email", method = RequestMethod.POST)
	@GetMapping(value = "/email")
	public Response<?> getUserByEmailId(@RequestParam("emailId") String emailId) {
		try {
			System.out.println(emailId);
			return new Response<User>(ResponseTypeEnum.SUCCESS, userService.getByEmail(emailId));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	// @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@PutMapping(value = "{userId}")
	public Response<?> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
		try {
			return new Response<User>(ResponseTypeEnum.SUCCESS, userService.updateUser(userId, user));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	// @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@DeleteMapping("{userId}")
	public Response<?> deleteUserById(@PathVariable("userId") String userId) {
		try {
			return new Response<User>(ResponseTypeEnum.SUCCESS, userService.deleteUser(userId));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}
	
	@GetMapping
	public Response<?> getAllUsers() {
		try {

			return new Response<List<User>>(ResponseTypeEnum.SUCCESS, userService.getAllUser());

		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

}
