package com.tengzhi.business.finance.voucher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.AccountDao;
import com.tengzhi.business.finance.voucher.dao.AuxiliaryItemsDao;
import com.tengzhi.business.finance.voucher.model.Account;
import com.tengzhi.business.finance.voucher.service.AccountService;
import com.tengzhi.business.project.projectArchives.projectTreams.model.EXmXmxz;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.business.system.params.dao.SysParamDao;
import com.tengzhi.business.system.params.model.SysParams;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDao accoutDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
    @Autowired
    private SysParamDao sysParamDao;



	@Override
	public Account save(Account accout) throws Exception {
	
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Account accout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean judgeUniqueset(Account accout) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean judgeUniqueother(Account afva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Result deleteById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取种类
	 */
	@Override
	public List<Map> findACTGroup() {

	String sql="SELECT a.fname as combtext,a.fclassid as combid  FROM e_f_voucher_acctgroup a WHERE  a.fgroupid=0 ";

	return accoutDao.QueryListMap(sql);
	}

	@Override
	public BasePage<Map<String,Object>> findAll(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String corpId = SessionUser.getCurrentCorpId();
        StringBuffer sqlWhere = SqlJoint.where(e -> {
        //  e.and(el->{el.contains("faccoutId",Long.valueOf(map.get("faccoutId").toString());});
            e.and(el->{el.contains("ac.data_corp",corpId);});
        });
        String sql = "SELECT  "
        		+ " (case when fisdelete='1' then '启用' else '禁用' end) as fisdelete_n, "
        		+ " (case when fdc='1' then '借' else '贷' end) as fdc_n, "
        		+ " (case when fisbank='1' then '是' else '' end) fisbank_n, "
        		+ " (case when fiscash='1' then '是' else '' end) fiscash_n, "
        		+ " ((CASE WHEN ac.fcustom = '1' THEN '客户' ELSE '' END)|| "
        		+ " (CASE WHEN ac.fsupplier = '1' THEN ' 供应商' ELSE '' END)||"
        		+ " (CASE WHEN ac.femp = '1' THEN ' 职员' ELSE '' END)||"
        		+ " (CASE WHEN ac.fproject = '1' THEN ' 项目' ELSE '' END) ||"
        		+ " (CASE WHEN ac.fdept = '1' THEN ' 部门' ELSE '' END)||"
        		+ " (CASE WHEN ac.fqt = '1' THEN '其他' ELSE '' END)||"
        		+ " (CASE WHEN ac.finventory = '1' THEN ' 存货' ELSE '' END)) fzks,"
        		+ " (SELECT  fname FROM e_f_voucher_acctgroup WHERE fgroupid = ac.fgroupid limit 1) fgroupidname,"
        		+ "	 CAST (ac.fnumber AS varchar(100))||  fname fnumbername,ac.*"
        		+ "  from e_f_voucher_ac ac where 1=1 "
                + "  AND fgroupid IN (SELECT fgroupid FROM e_f_voucher_acctgroup  WHERE fclassid = (SELECT DISTINCT fclassid FROM e_f_voucher_acctgroup WHERE fname ='"+
                map.get("tabname").toString()
                +"')) "     
                + sqlWhere
        		+" order by  fnumber ";
         return accoutDao.QueryToMapPage(baseDto,sql);

	}
	@Override
	public Result findById(Long id) {
		Account account = accoutDao.findByFaccountid(id);
		if (account.getFparentid().toString().equals("0")) {
			account.setFparentid(0L);
		} else {
			account.setFparentid(Long.valueOf((account.getFnumber())));
		}
		return Result.resultOk(account, "查询成功");
	}
	/**
	 * 交易币种
	 */
	@Override
	public List<Map> findCurrency(BaseDto baseDto) {
        Map<String, String> map = baseDto.getParamsMap();
        String corpId = SessionUser.getCurrentCorpId();
        StringBuffer sqlWhere = SqlJoint.where(e -> {
        	  e.and(el->{el.contains("sys.org_id",corpId);});
            e.and(el->{el.contains("sys.param_type","交易币种");});
            e.and(el->{el.contains("sys.parent_id","JYBZ");});
        });
        String sql = "SELECT sys.param_name AS  combtext,sys.param_id AS combid " +
                " from sys_param sys where 1=1 " + sqlWhere;
        return sysParamDao.QueryListMap(sql);
	}
	
	
	public Map<String, Object> saveData(Account ac) {
		String corpId = SessionUser.getCurrentCorpId();
		Map<String, Object> rmap = new HashMap<String, Object>();
		String sql = "select count(*) cn  from e_f_voucher_ac where fnumber = '" + ac.getFnumber() + "' and corp_id= '" + corpId + "'";
		String num = accoutDao.QueryFirstString(sql);
		if (Integer.parseInt(num) > 0) {
			rmap.put("status", false);
			rmap.put("message", "所设编码已存在");
			return rmap;
		}
		if (ac.getFparentid() >0) {
			ac.setFfullname(ac.getFfullname() + "_" + ac.getFname());
		} else {
			ac.setFrootid(ac.getFaccountid());
			ac.setFfullname(ac.getFname());
		}
		if (ac.getFcur() == null || ac.getFcur() == "") {
			ac.setFcur("RMB");
		}
		if (ac.getFcur().toUpperCase().equals("RMB")) {
			ac.setFisrateadj((int) 0);
		} else {
			ac.setFisrateadj((int) 1);
		}

		if (ac.getFunit() == null || ac.getFunit().trim() == "" || ac.getFunit().toString().trim().length() == 0) {
			ac.setFunit("");
			ac.setFisqtyaux(0);
		} else {
			ac.setFisqtyaux(1);
		}
		if ((ac.getFcustom() == 0) && (ac.getFsupplier() == 0) && (ac.getFemp() == 0) && (ac.getFproject() == 0) && (ac.getFdept() == 0) && (ac.getFinventory() == 0)) {
			ac.setFisitem(0);
		} else {
			ac.setFisitem(1);
		}

		ac.setDataCorp(corpId);
		ac.setFintrate((float) 0);// 利息率
		ac.setFacnttype((int) 0);// 账目类型，0普通科目，1表外科目
		ac.setFcontrol((int) 0);
		ac.setFisbank((int) 0);
		ac.setFisbusi((int) 0);
		ac.setFisjrnl((int) 0);
		ac.setFisrateadj((int) 0);
		ac.setFistrans((int) 0);
		ac.setFsystemtype((int) 0);// 系统标示
		ac.setFdetail(1);
		Account account = accoutDao.findByFparentid(ac.getFparentid());
		if (account.getFparentid()==0) {
			account.setFdetail(0);
			accoutDao.update(account);
		}
		accoutDao.save(ac);
		rmap.put("status", true);
		rmap.put("message", "操作成功！");
		System.out.println("科目设置"+ ac.getFaccountid().toString()+"科目设置新增");
		return rmap;
	}


}
