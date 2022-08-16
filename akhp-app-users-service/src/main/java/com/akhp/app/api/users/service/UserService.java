package com.akhp.app.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.akhp.app.api.users.shared.UserDto;
import com.akhp.app.api.users.ui.model.CreateUserResponseModel;

public interface UserService extends UserDetailsService{


	CreateUserResponseModel createUser(UserDto userDetails);

	UserDto getUserDetailByEmail(String username);

}
