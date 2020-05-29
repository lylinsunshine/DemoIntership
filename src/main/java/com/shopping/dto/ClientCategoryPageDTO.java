package com.shopping.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCategoryPageDTO {
	List<ClientManufacturerDTO> manufacturerList;
	List<ClientCategoryDTO> categoryList;
	
}
