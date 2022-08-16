package com.akhp.app.api.users.ui.controllers;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhp.app.api.users.service.UserService;
import com.akhp.app.api.users.shared.UserDto;
import com.akhp.app.api.users.ui.model.CreateUserRequestModel;
import com.akhp.app.api.users.ui.model.CreateUserResponseModel;
import com.akhp.app.api.users.ui.model.LoginRequestModel;
import com.akhp.app.api.users.ui.model.LoginResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port - " + environment.getProperty("local.server.port");
	}

	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody @Valid CreateUserRequestModel userDetails) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(STRICT);

		CreateUserResponseModel responseModel = userService.createUser(mapper.map(userDetails, UserDto.class));

		return ResponseEntity.status(HttpStatus.CREATED).body(responseModel) ;
	}
	
}
