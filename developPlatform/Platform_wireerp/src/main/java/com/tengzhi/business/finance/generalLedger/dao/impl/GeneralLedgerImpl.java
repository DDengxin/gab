package com.tengzhi.business.finance.generalLedger.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tengzhi.base.jpa.dao.impl.BasicsDaoImpl;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.generalLedger.dao.GeneralLedgerDao;
import com.tengzhi.business.system.user.model.SysUser;

import cn.hutool.core.util.ObjectUtil;

@Repository
public class GeneralLedgerImpl extends BasicsDaoImpl<Object, String> implements GeneralLedgerDao {

	@Override
	public Result find(BaseDto dto) {
		Map<String, String> map = dto.getParamsMap();
		String str = map.get("nickName");
		String where = SqlJoint.where(e -> {
			e.eq("nick_name", str);
		}, true);
		return super.QueryPageList("select * from sys_demo_test" + where, null, dto);
	}
	
	@Override
	public List findList(BaseDto dto) throws IOException {
		Map<String, String> map = dto.getParamsMap();
		String corpId = SessionUser.SessionUser().getCorpId();
		String sqlWhere = "  where cw_stype = '"+map.get("cwStype")+"'   ";
		if(ObjectUtil.isNotEmpty(map.get("cwDw"))) {
			sqlWhere+="  and cw_dw= '"+map.get("cwDw")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("cwBz"))) {
			sqlWhere+="  and cw_bz= '"+map.get("cwBz")+"' ";
		}
		String sql = " select cw_stype,cw_dw \"cwDw\",f_get_param_name('交易币种','RMB','"+   SessionUser.getCurrentCorpId()   +"','',false) \"cwBz\",qc,bqfs,bqsfk,qmje,qcfp,bqfpfs,bqfpsfk,qmfpje,qmfpwk,f_getname('GETDWEXP',cw_dw,'','"+corpId+"')dwname,f_getname('CUSTOMERBUYER',cw_dw,'','"+corpId+"')buyer from f_v_yfyszz_copy1('"+corpId+"','"+map.get("srchRq1")+"','"+map.get("srchRq2")+"')a  "+sqlWhere;
		return super.queryToMapTransformers(sql);
	}

}
