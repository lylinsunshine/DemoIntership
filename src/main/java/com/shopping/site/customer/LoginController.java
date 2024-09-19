package com.shopping.site.customer;

import com.shopping.site.service.UserDetailsService;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

	private final UserDetailsService userService;

	@GetMapping
	public Response<String> login(@RequestParam String username, @RequestParam String password) {
		String token = userService.login(username, password);
		return new Response<String>(token, 200, "Token");
	}

}
