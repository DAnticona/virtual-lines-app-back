package com.line.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.line.store.dao.UserDao;
import com.line.store.entity.User;

@Service("userDetailsService")
public class UserService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userDao.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		if("N".equals(user.getActiveFg())) {
			throw new DisabledException(email);
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}

}
