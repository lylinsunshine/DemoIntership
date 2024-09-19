package com.shopping.site.service;

import com.shopping.site.entity.Attribute;
import com.shopping.site.repository.AttributeRepository;
import com.shopping.site.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeService {

	private final AttributeRepository attributeRepository;

	public List<Attribute> getAttributes() {
		return attributeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Attribute addAttribute(Attribute attribute) {
		return attributeRepository.save(attribute);
	}

	public Response<Boolean> isNameExist(String name) {
		return new Response<>(attributeRepository.existsByName(name), HttpStatus.OK.value(), "isNameExist");
	}

}
