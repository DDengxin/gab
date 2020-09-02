package com.tengzhi.business.platform.evaluation.service.impl;

import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.platform.evaluation.dao.EvaluationDao;
import com.tengzhi.business.platform.evaluation.model.GEvalutaion;
import com.tengzhi.business.platform.evaluation.service.EvaluationService;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    EvaluationDao evaluationDao;
    @Autowired
    SysGenNoteService sysGenNoteService;


    @Override
    public Result addEvaluation(GEvalutaion gEvalutaion) {
        SessionUser sessionUser = SessionUser.SessionUser();
        if (sessionUser == null) {
            return Result.error("评价失败，请先登录");
        } else if (sessionUser.getgUserInfo() == null) {
            return Result.error("该账户不是哥爱帮平台账户");
        } else {
            String userId = sessionUser.getUserId();
            String evaluatuonId = sysGenNoteService.getNote("g_evaluation", "GAEVA", "yyyyMM", 4);
            gEvalutaion.setEvaluatuonId(evaluatuonId);
            gEvalutaion.setOwner(userId);
            evaluationDao.store(gEvalutaion);
            return Result.resultOkMsg("评价成功");
        }
    }

    @Override
    public List<Map<String, Object>> getEvaluation(String productNum) {
        SessionUser sessionUser = SessionUser.SessionUser();
        if (sessionUser.getgUserInfo() == null) {
            return new ArrayList<>(0);
        }

        String userId = sessionUser.getUserId();
        String sql = "select * from g_evaluation where product_num='" + productNum + "' and owner='" + userId + "'";
        return evaluationDao.QueryhumpMap(sql);
    }
}
