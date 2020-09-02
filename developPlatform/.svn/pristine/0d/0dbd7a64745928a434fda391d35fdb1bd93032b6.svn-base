package com.tengzhi.base.jpa.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.base.jpa.service.CommunalService;

public class CommunalServiceImpl<T, ID extends Serializable> implements CommunalService<T, ID> {
	@Autowired
	BasedaoRepository<T, ID> BaseDao;

	@Override
	public T findById(ID id) {
		return BaseDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(ID id) {
		BaseDao.deleteById(id);
	}
}
