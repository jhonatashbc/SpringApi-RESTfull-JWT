package com.springcourse.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springcourse.domain.Client;

public class ClientSpecification {

	public static Specification<Client> search(String text){
		return new Specification<Client>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(text == null || text.isEmpty()) {
					return null;
				}
				String likeTerm = "%"+text +"%";
				Predicate predicate = cb.or(cb.like(root.get("name"), likeTerm),
						cb.or(cb.like(root.get("address"), likeTerm),
								cb.like(root.get("phoneNumber").as(String.class), likeTerm)));
				return predicate;
			}
		};
	}
}
