package com.shopping.site.dto;

import java.util.Set;

import com.shopping.site.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientUserInfoDTO {
	private String username;
	private String name;
	private String phoneNumber;
	private Set<Address> addressSet;
}
