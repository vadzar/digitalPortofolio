package com.personal.website.digitalPortofolio.User;

import com.personal.website.digitalPortofolio.config.ApiAuthRequired;
import com.personal.website.digitalPortofolio.config.JwtService;
import com.personal.website.digitalPortofolio.exception.ApiBadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/user")
public class UserController extends ApiAuthRequired {
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/details")
	public UserDTO getDetails() {
		return new UserDTOMapper().apply(getCurrentUser());
	}

}
