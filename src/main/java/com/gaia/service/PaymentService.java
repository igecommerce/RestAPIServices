package com.gaia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gaia.domain.Brand;
import com.gaia.repository.BrandRepo;

@Service
public class PaymentService {

	@Autowired
	private BrandRepo brandRepo;

	public List<Brand> filterBrand(String name) {
		Specification<Brand> spec = new Specification<Brand>() {

			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<Predicate>();

				predicate.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));

				return criteriaBuilder.and(predicate.stream().toArray(Predicate[]::new));
			}

		};
		return brandRepo.findAll(spec);
	}

}
