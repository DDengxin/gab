package com.tengzhi.business.sale.domesticTrade.domesticTradeContract.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.sale.saleProduct.saleContract.model.EXsContract;
import com.tengzhi.business.sale.saleProduct.saleContract.vo.EXsContractVo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DomesticTradeService extends BaseService<EXsContract, String> {

    BasePage<Map<String, Object>> findAll(BaseDto basedto);

    BasePage<Map<String, Object>> getSrchBottomList(BaseDto baseDto) throws IOException;

    EXsContract save(EXsContractVo EXsContractvo) throws Exception;


    EXsContract findByNote(String htNote);

    void update(EXsContractVo eXsContractVo);

    void deleteByNote(String htNo);

    Result confirm(String htNote );

    Result cancel(String htNote );

    Result getFlag(String htNo,String flag);

    Result getFlags(String htNo,String flag);

    BasePage<Map<String, Object>> getContractDetailed(BaseDto baseDto) throws IOException;

    ModelAndView table(ModelAndView mv, String htNo);

    BasePage<Map<String, Object>> getScddList(BaseDto basedto) throws IOException;

    Map<Object,String> getBySplitId(String htMo);

    Map<Object,String> getByHpId(String htMo);
    /**
     * 核销
     * @param htNo
     * @return
     */
    Result hx(String htNo );
    Result qxhx(String htNo );

    /**
     * 可用的合同明细
     * @param baseDto
     * @return
     * @throws IOException
     */
    BasePage<Map<String, Object>> getUsableContractDetailed(BaseDto baseDto) throws IOException;

    void exportExcelScdd(HttpServletResponse response, HttpServletRequest request) throws IOException;

    /**
     * 获取项目
     * @return
     * @throws IOException
     */
    List<Map<String,Object>> getItemList(String htStype ) ;

    /**
     * 获取明细及拓展项目
     * @param baseDto
     * @return
     * @throws IOException
     */
    List<Map<String, Object>> getDetailedList(BaseDto baseDto) throws IOException;


    Result updateTerms(EXsContract eXsContract);
}
