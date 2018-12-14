package com.nus.iss.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nus.iss.exception.UserNotFoundException;
import com.nus.iss.model.CapsUser;
import com.nus.iss.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public CapsUser authenticate(String uid, String pwd) throws UserNotFoundException {

		CapsUser u1 = userRepository.findUserByNamePwd(uid, pwd);

		if (u1 == null) {
			throw new UserNotFoundException("User not found.");
		}
		return u1;
	}

	@Override
	public CapsUser getUser(String id) throws UserNotFoundException {
		CapsUser u1 = null;
		try {
			u1 = userRepository.findById(id).get();
		} catch (Exception e) {
			throw new UserNotFoundException(e.toString());
		}
		return u1;
	}
}
