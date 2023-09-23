package com.personal.website.digitalPortofolio.auth;

import com.personal.website.digitalPortofolio.User.User;
import com.personal.website.digitalPortofolio.User.UserRepository;
import com.personal.website.digitalPortofolio.User.UserRole;
import com.personal.website.digitalPortofolio.config.JwtService;
import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;
	public AuthenticationResponse register(RegisterRequest request) {
		userRepository.findByEmail(request.email())
				.ifPresent(
						(val) -> {
							throw new ApiBadRequestException("An account is already registered with your email address. Please log in.");
						}
				);
		var user = User.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.password(passwordEncoder.encode(request.password()))
				.role(UserRole.AUTHOR)
				.build();
		userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);

		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.email(),
						request.password()
				)
		);
		var user = userRepository.findByEmail(request.email())
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("user with email %s not found", request.email())
				));
		var jwtToken = jwtService.generateToken(user);

		return new AuthenticationResponse(jwtToken);
	}
}
