package com.tengzhi.business.xt.reception.visitors.dao;

import com.tengzhi.base.jpa.dao.BasedaoRepository;
import com.tengzhi.business.xt.reception.visitors.model.Visitors;

public interface VisitorsDao extends BasedaoRepository<Visitors, String> {

    Visitors findByNote(String note);

}
