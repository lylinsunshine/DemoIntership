package com.shopping.site.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageModel<T> {
	private List<T> list;
	private int currentPage;
	private int totalPage;		
}
