package com.shopping.site.staff;

import com.shopping.site.dto.LoginRequestDTO;
import com.shopping.site.service.UserServiceImpl;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/clients/login")
@RequiredArgsConstructor
public class ClientLoginController {

	private final UserServiceImpl userService;
	
	@PostMapping
	public Response<String> login(@RequestBody LoginRequestDTO user) {
		return userService.login(user);
	}
}
