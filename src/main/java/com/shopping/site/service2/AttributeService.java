package com.shopping.site.service2;

import com.shopping.dao.IAttributeDAO;
import com.shopping.entity.Attribute;
import com.shopping.repository.AttributeRepository;
import com.shopping.service.IAttributeService;
import com.shopping.site.entity.Attribute;
import com.shopping.site.repository.AttributeRepository;
import com.shopping.site.util.ResponseModel;
import com.shopping.util.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

	public ResponseModel<Boolean> isNameExist(String name) {
		return new ResponseModel<Boolean>(attributeRepository.existsByName(name), HttpStatus.OK, "isNameExist");
	}

}
