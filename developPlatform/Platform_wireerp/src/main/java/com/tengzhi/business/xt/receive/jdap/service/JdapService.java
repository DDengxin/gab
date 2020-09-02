package com.tengzhi.business.xt.receive.jdap.service;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.xt.receive.jdap.model.QyJdJdsq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface JdapService {

    BasePage<Map<String,Object>> getList(BaseDto baseDto);


    QyJdJdsq add(QyJdJdsq qyJdJdsq);

    QyJdJdsq getByNote(String note);

    QyJdJdsq edit(QyJdJdsq qyJdJdsq);

    void deleteByNote(String note);

    void change(String note);

    void changeFlage(String note, String state);

    void getFalge(String note, String state);

    void getExcle(HttpServletResponse response, HttpServletRequest request);
}
