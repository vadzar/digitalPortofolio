package com.personal.website.digitalPortofolio.User;

import com.personal.website.digitalPortofolio.config.ApiAuthRequired;
import com.personal.website.digitalPortofolio.config.JwtService;
import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/user")
public class UserController extends ApiAuthRequired {
	private final UserService userService;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/details")
	public UserDTO getDetails() {
		return new UserDTOMapper().apply(getCurrentUser());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO updateUser(@RequestBody User newUserData) {
		return userService.updateUser(newUserData, getCurrentUser());
	}

}
