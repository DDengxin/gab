package com.tengzhi.base.jpa.extension;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author lqy 复杂条件拼接
 */
public class Specifications {

	/** 条件组装 and
	 * @param <T>
	 * @param action
	 * @return
	 */
	public static <T> Specification<T> where(Consumer<HsqlUtils<T>> action) {
		return where(true, action);
	}

	/** 条件组装
	 * @param <T>
	 * @param isConjunction 为true and false or
	 * @param action
	 * @return
	 */
	public static <T> Specification<T> where(boolean isConjunction, Consumer<HsqlUtils<T>> action) {
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
			HsqlUtils<T> specification = new HsqlUtils<T>(root, query, builder);
			action.accept(specification);
			List<Predicate> predicates = specification.getPredicates();
			Predicate[] arr = predicates.toArray(new Predicate[predicates.size()]);
			return isConjunction ? builder.and(arr) : builder.or(arr);
		};
	}
}
