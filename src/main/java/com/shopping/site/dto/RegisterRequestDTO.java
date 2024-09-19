package com.shopping.site.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
	private String username;
	private String password;
	private String name;
	private String phoneNumber;	
	private String role;
}
