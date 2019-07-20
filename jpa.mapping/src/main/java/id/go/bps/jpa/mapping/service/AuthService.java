package id.go.bps.jpa.mapping.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.go.bps.jpa.mapping.model.User;
import id.go.bps.jpa.mapping.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		id.go.bps.jpa.mapping.model.User user = userRepository.findByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(),
					Collections.emptyList()
				);
	}

}
