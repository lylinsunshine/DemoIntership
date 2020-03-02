package com.shopping.util;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> {
	private List<T> responseData;
	private Pageable pageable;
	private int currentPage;
	private int totalPage;
		
}
