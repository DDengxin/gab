package com.tengzhi.business.finance.constituent.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.finance.constituent.vo.EPzJyListLhVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.params.model.SysParams;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EPzJyListLhService extends BaseService  {


    Result findAll(BaseDto baseDto)  throws IOException;

    /**
     * 通过参数值1获取匹配的的参数
     * @param codeType
     * @return
     */
    Set<SelectVo> getPH(String codeType);

    List<SysParams> paramsAll(String name);

    Result save(EPzJyListLhVo EPzJyListLhVo) throws Exception;

    void update(EPzJyListLhVo EPzJyListLhVo);

    void deleteByPch(String luhao);

    boolean judgeUnique(String luhao);

    Result findbyid(String luhao);

    List<Map<String,String>> getPhTypeList(String paramUid);


    BasePage<Map<String,Object>> getProductSelectionList(BaseDto dto) throws IOException;

    /**
     * 通过批次号获取单号
     * @param pack
     */
    List<SelectVo> getNote4Pack(String pack);

    /**
     * 获取标准值
     * @codeType 产品类型
     * @param phType 牌号
     * @param code 品名
     * @return
     */
    List<Map> getStandardValue(String codeType, String phType, String code);
}
