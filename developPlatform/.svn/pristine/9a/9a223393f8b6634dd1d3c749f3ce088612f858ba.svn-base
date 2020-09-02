package com.tengzhi.business.codeGeneration.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.page.impl.BasePageImpl;
import com.tengzhi.base.tools.reflect.SelfResultTransformer;
import com.tengzhi.business.codeGeneration.dao.CodeGenerationdao;
import com.tengzhi.business.codeGeneration.vo.Structure;
import com.tengzhi.business.codeGeneration.vo.TableVo;

@Component
public class CodeGenerationdaoImpl implements CodeGenerationdao {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * bug 模式只能写死，没有找到解决方案，不写死会查询整个数据库所有表
	 *
	 */
	@Override
	public List<TableVo> findTable(String tablename) {
		String[] tables = tablename.split(",");
		String sqlwhere = "";
		for (int i = 0; i < tables.length; i++) {
			if (StringUtils.isNotBlank(tables[i])) {
                sqlwhere += "'" + tables[i] + "',";
            }
		}
		if (!"".equals(sqlwhere)) {
			sqlwhere = "and relname in (" + sqlwhere.substring(0, sqlwhere.length() - 1) + ")";
		}
		String sql = "select relname as tabname,cast(obj_description(relfilenode,'pg_class') as varchar) as comment from pg_class LEFT JOIN pg_namespace dd ON dd.oid = pg_class.relnamespace"
				+ " where relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%' and dd.nspname = current_schema() and relname not like 'act_%' " + sqlwhere
				+ "  order by relname";
		Query q = entityManager.createNativeQuery(sql);
		q.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(TableVo.class));
		@SuppressWarnings("unchecked")
		List<TableVo> resultList = q.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BasePage<TableVo> findAllTable(BaseDto dto) throws IOException {
		Map<String, String> map = dto.getParamsMap();
		String tablename = String.valueOf(map.get("tabname"));
		String sqlWhere = "";
		if (!StringUtils.isBlank(tablename)) {
			sqlWhere = " and pg_class.relname like'%" + tablename + "%'";
		}
		// 查询表结构
		String sql = "select pg_class.relname as tabname,cast(obj_description(pg_class.relfilenode,'pg_class') as varchar) as comment,pg_constraint.conname as pk_name,pg_attribute.attname as colname,pg_type.typname as typename from "
				+ "pg_constraint  inner join pg_class on pg_constraint.conrelid = pg_class.oid inner join pg_attribute on pg_attribute.attrelid = pg_class.oid and  pg_attribute.attnum = pg_constraint.conkey[1]"
				+ "inner join pg_type on pg_type.oid = pg_attribute.atttypid "
				+ " where pg_constraint.contype='p' and pg_class.relkind = 'r' and pg_class.relname not like 'pg_%' and pg_class.relname not like 'sql_%' and pg_class.relname not like 'act_%'"
				+ sqlWhere + " order by pg_class.relname";

		String countSql = "select count(1) from "
				+ "pg_constraint  inner join pg_class on pg_constraint.conrelid = pg_class.oid inner join pg_attribute on pg_attribute.attrelid = pg_class.oid and  pg_attribute.attnum = pg_constraint.conkey[1]"
				+ "inner join pg_type on pg_type.oid = pg_attribute.atttypid "
				+ " where pg_constraint.contype='p' and pg_class.relkind = 'r' and pg_class.relname not like 'pg_%' and pg_class.relname not like 'sql_%' and pg_class.relname not like 'act_%'"
				+ sqlWhere;
		Sort sort = null;
		if (dto.getSortField() != null) {
			if (Direction.DESC.name().equals(String.valueOf(dto.getSortOrder()).toUpperCase())) {
				sort = Sort.by(Direction.DESC, dto.getSortField());
			} else {
				sort = Sort.by(Direction.ASC, dto.getSortField());
			}
		}
		Pageable pageable = PageRequest.of(dto.getPageIndex(), dto.getPageSize(), sort);
		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(TableVo.class));
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<TableVo> rankEntities = query.getResultList();
		Query countQuery = entityManager.createNativeQuery(countSql);
		BigInteger totalCount = (BigInteger) countQuery.getSingleResult();
		Page<TableVo> page = new PageImpl<>(rankEntities, pageable, totalCount.longValue());
		return new BasePageImpl<TableVo>(page.getContent(), page.getPageable(), page.getTotalElements());
	}

	@Override
	public List<Structure> findStructure(String tablename) {
		String sql = "SELECT  a.attnum, a.attname AS field,  t.typname AS type, a.attlen AS length,  a.atttypmod AS lengthvar, case when a.attnotnull='f' then '是' else '否' end AS notnull,  b.description AS comment  FROM pg_class c left join pg_attribute a  on \r\n" + 
				"a.attrelid = c.oid  left join  pg_namespace dd ON dd.oid = c.relnamespace\r\n" + 
				"LEFT  JOIN pg_description b ON a.attrelid=b.objoid AND a.attnum = b.objsubid ,  pg_type t\r\n" + 
				"WHERE c.relname = '"+tablename+"'  and a.attnum > 0 and dd.nspname = current_schema() and a.atttypid = t.oid  ORDER BY a.attnum";
		Query q = entityManager.createNativeQuery(sql);
		q.unwrap(NativeQueryImpl.class).setResultTransformer(new SelfResultTransformer(Structure.class)).addScalar("attnum", StandardBasicTypes.STRING)
				.addScalar("field", StandardBasicTypes.STRING).addScalar("type", StandardBasicTypes.STRING).addScalar("length", StandardBasicTypes.STRING)
				.addScalar("lengthvar", StandardBasicTypes.STRING).addScalar("notnull", StandardBasicTypes.STRING).addScalar("comment", StandardBasicTypes.STRING);
		@SuppressWarnings("unchecked")
		List<Structure> resultList = q.getResultList();
		return resultList;
	}

}
