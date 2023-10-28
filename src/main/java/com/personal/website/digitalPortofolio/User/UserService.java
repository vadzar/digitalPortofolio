package com.personal.website.digitalPortofolio.User;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@AllArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserDTOMapper userDTOMapper;

	public UserDTO getDetails(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return userDTOMapper.apply(user);
	}
}
