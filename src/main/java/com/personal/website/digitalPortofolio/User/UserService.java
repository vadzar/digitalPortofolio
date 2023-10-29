package com.personal.website.digitalPortofolio.User;

import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@AllArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserDTOMapper userDTOMapper;
	private final PasswordEncoder passwordEncoder;

	public UserDTO getDetails(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return userDTOMapper.apply(user);
	}

	public UserDTO updateUser(User newUserData, User currentUser) {
		if(!newUserData.getEmail().equals(currentUser.getEmail())) {
			String email = newUserData.getEmail();
			if(userRepository.findByEmail(email).isPresent()) {
				throw new ApiBadRequestException("User new email is used");
			} else {
				currentUser.setEmail(email);
			}
		}

		if(!newUserData.getPassword().isEmpty()) {
			currentUser.setPassword(passwordEncoder.encode(newUserData.getPassword()));
		}
		currentUser.setFirstName(newUserData.getFirstName());
		currentUser.setLastName(newUserData.getLastName());
		currentUser.setRole(newUserData.getRole());
		currentUser.setAboutMe(newUserData.getAboutMe());

		return userDTOMapper.apply(userRepository.save(currentUser));
	}
}
