
package com.akhp.app.api.users.service;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.akhp.app.api.users.entity.UserEntity;
import com.akhp.app.api.users.shared.UserDto;
import com.akhp.app.api.users.ui.controllers.UserRepository;
import com.akhp.app.api.users.ui.model.CreateUserResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public CreateUserResponseModel createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(STRICT);

		UserEntity userEntity = mapper.map(userDetails, UserEntity.class);
		userEntity.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()) );
		UserEntity saveUserEntity = userRepository.save(userEntity);
		
		return mapper.map(saveUserEntity, CreateUserResponseModel.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> userEntity = userRepository.findByEmail(username);
		
		if(!userEntity.isPresent())
			throw new UsernameNotFoundException(username);
		
		return new User(userEntity.get().getEmail(), userEntity.get().getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailByEmail(String username) {
		Optional<UserEntity> userEntity = userRepository.findByEmail(username);
		if(!userEntity.isPresent())
			throw new UsernameNotFoundException(username);

		return new ModelMapper().map(userEntity.get(), UserDto.class);
	}
}
