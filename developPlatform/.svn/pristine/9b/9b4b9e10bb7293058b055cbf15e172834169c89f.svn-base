package com.tengzhi.business.finance.voucher.service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.jpa.service.BaseService;
import com.tengzhi.business.codeGeneration.vo.TableVo;
import com.tengzhi.business.finance.voucher.model.EFVoucherBscategory;
import com.tengzhi.business.finance.voucher.vo.BsCategoryVo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface BusinessCategoryService extends BaseService<EFVoucherBscategory, BigInteger> {


    BasePage<EFVoucherBscategory> getList(BaseDto baseDto) throws IOException;

    List<Map> getList();

    List<Map>  getReportList();
    List<Map> findTableFieldDistinctValue(String tableName,String fieldName);
    List<Map> findTableField(String tableName);
    Map<String,Object> getById(String fevidbusid);
    List<TableVo> findAllTables();
    Result saveData(BsCategoryVo vo );
    Result deleteById(String Id);
    List<Map> findAllBsCategoryTypes();
    List<Map> findAllBsCategoryTypeNames(String typeName);
    List<Map> findAllCashFlowItems();

}
