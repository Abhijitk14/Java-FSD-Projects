package com.wipro.expense_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.expense_management.entity.User;
import com.wipro.expense_management.repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    /**
	     * User service method is use to save user data
	     * @param user
	     * @return
	     */
	    public String addUser(User user) {
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	userRepository.save(user);
	    	return "user added to the database";
	    }
}