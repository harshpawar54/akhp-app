package com.akhp.app.api.users.ui.controllers;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.akhp.app.api.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);

}
