package com.tengzhi.business.ck.yw.warehouseWarn.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.base.common.ProductType;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.ck.yw.warehouseWarn.service.WarehouseWarnService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class WarehouseWarnServiceImpl implements WarehouseWarnService {
    @Autowired
    private ECkInDao eCkInDao;

    @Override
    public BasePage<Map<String, Object>> QueryPageWarnList(BaseDto baseDto) throws Exception {
        Map<String,String> map = baseDto.getParamsMap();
        SessionUser sessionUser = SessionUser.SessionUser();
        String cpcodeType = map.get("cpcodeType");
        String where = SqlJoint.whereSuffixAnd((c) ->{

            c.eq("cpcode.cpcode_type",cpcodeType,true);
            c.eq("cpcode.data_corp",sessionUser.getCorpId(),true);

            //cpcodeName
            c.contains("cpcode.cpcode_size",map.get("cpcodeSize"));
            String cpcodeFl = map.get("cpcodeFl");
            if(StringUtils.isNotBlank(cpcodeFl)){
                c.in("cpcode.cpcode_fl", StrUtil.split(map.get("cpcodeFl"),',',true,true));
            }
        });
        ProductType productType = ProductType.valueOfParamId(cpcodeType);

//	+ "f_getparamname('GETBYCPCODEFL',cpcode.cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name , "
//+ "f_getparamname('GETCPCODESIZE',cpcode_size,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_name, 	"
        //        		+ "f_getparamname('GETCPCODESIZEEN',cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,"

        String sql = " select stock.dtype, stock.lib, cpcode.cpcode_id, cpcode.cpcode_fl, "
        		+ "f_getparamname('GETBCPCODENAME',cpcode.cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_name, "    	
        		+ "f_getparamname('GETCPCODEXP',cpcode.cpcode_xp,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_xp_name,"
        		+ "cpcode.cpcode_size, cpcode.cpcode_name_en, cpcode.cpcode_size_en, "
                +" coalesce( sum( stock.sl ),0) sl, cpcode.cpcode_dunit, cpcode.cpcode_lower, cpcode.cpcode_upper, "
                +" f_get_param_name('产品大类',cpcode.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') dtype_name, "
                +" f_get_param_name('库房档案',stock.lib,'"+   SessionUser.getCurrentCorpId()   +"') lib_name, "
                +" f_getparamname('GETBYCPCODEFL',cpcode.cpcode_fl,'','技术',cpcode.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl_name, "
                + (ProductType.CP.equals(cpcodeType) ? " cpcode.cpcode_size_en cpcode_size_en_name ," : " f_getparamname('GETCPCODESIZEEN',cpcode.cpcode_size_en,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_size_en_name,")
                //+ " f_get_param_name('"+ productType.getFieldParam3Value(ProductType.DynamicField.cpcodeName) +"',cpcode.cpcode_size_en,'cn') cpcode_size_en_name,"
                + " f_get_param_name('大包单位',cpcode.cpcode_dunit,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_dunit_name , "
                + "f_get_param_name('计量单位',cpcode.cpcode_bz,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcode_bz_name"
                + " from "
                + " e_js_cpcode cpcode left join  v_stock stock  "
                + " on stock.code = cpcode.cpcode_id "
                + "  where  cpcode.cpcode_stock_warn = true  "
                +  where
                + "  group by "
                + " stock.dtype, stock.lib,  cpcode.cpcode_id,cpcode.cpcode_fl, cpcode.cpcode_name, cpcode.cpcode_size, cpcode.cpcode_name_en, cpcode.cpcode_size_en, stock.sl,cpcode.cpcode_bz, cpcode.cpcode_dunit, cpcode.cpcode_lower, cpcode.cpcode_upper "
                + "  order by "
                + "  cpcode.cpcode_fl, cpcode.cpcode_name, cpcode.cpcode_size ";
        return eCkInDao.QueryMapPageList(baseDto,sql,true);
    }
}
