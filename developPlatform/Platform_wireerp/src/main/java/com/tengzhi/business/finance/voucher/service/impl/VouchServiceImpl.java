package com.tengzhi.business.finance.voucher.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.*;
import com.tengzhi.business.finance.voucher.model.Account;
import com.tengzhi.business.finance.voucher.model.EFVoucher;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.model.EFVoucherentry;
import com.tengzhi.business.finance.voucher.service.VouchService;
import com.tengzhi.business.finance.voucher.vo.EFVouchVo;
import com.tengzhi.business.resouces.vo.SelectVo;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VouchServiceImpl implements VouchService {
	@Autowired
	private VouchDao vouchDao;
	@Autowired
	private AuxiliaryItemsDao aidao;
	@Autowired
	private VouchEntryDao vouchEntryDao;
	@Autowired
	private SysGenNoteService sysGenNoteService;
	@Autowired
	CertificateWordDao certificateWordDao;
	@Autowired
	AccountDao accountDao;
	@Override
	public BasePage<EFVoucher> findAll(BaseDto baseDto){
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();

		return vouchDao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.eq("dataCorp",securityUser.getCorpId());
		}));
	}
	@Override
	public ModelAndView addhtml(ModelAndView mv, String ht_no) {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select fvalue,fkey from e_f_voucher_systemprofile where  data_corp = '"+corpid+"' ";
		List<Map> table = vouchDao.QueryListMap(sql);
		for(Map map:table) {
			mv.addObject(map.get("fkey").toString(), map.get("fvalue"));
		}
		return mv;
	}
	@Override
	public List<Map> getCurrentMonthNextfvoucherNumber(BaseDto baseDto) {
		List<Map> list = new ArrayList<Map>();
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		Map<String, String> m=baseDto.getParamsMap();
		return vouchDao.QueryListMap("select *  from f_voucher_findnumber('"+m.get("fgroup")+"' , '"+m.get("fdate").substring(0,7)+"' , '"+corpid+"' )");

	}
	@Override
	public List<Map> getLeafSubjectList() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select (SELECT fname from e_f_voucher_acctgroup where fgroupid=a.fgroupid and data_corp='"+corpid+"'  limit 1 )fgroupidname,concat_ws('',a.fnumber,a.fname ) fnumbername,concat_ws('',a.fnumber,a.ffullname )  fnumberfullname,a.* from  e_f_voucher_ac as a  where a.data_corp='"
				+ corpid + "' and faccountid not in (select distinct fparentid from e_f_voucher_ac)";
		return vouchDao.QueryListMap(sql);
	}
	@Override
	public List<Map> getSubjectTreeList() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select (SELECT fname from e_f_voucher_acctgroup where fgroupid=a.fgroupid and data_corp='"+corpid+"'  limit 1 )fgroupidname,concat_ws('',a.fnumber,a.fname ) fnumbername,concat_ws('',a.fnumber,a.ffullname )  fnumberfullname,a.* from  e_f_voucher_ac as a  where a.data_corp='"
				+ corpid + "' ";
		return vouchDao.QueryListMap(sql);
	}

	@Override
	public List<Map> getEntryGridList(BigInteger Id) {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();

		String sqlx = "select * from e_f_voucher_auxiliaryitems  where data_corp='" +securityUser.getCorpId()+ "'  and fusebstable='Y' ";
		List<EFVoucherAuxiliaryitems> lla=aidao.QueryToVo(EFVoucherAuxiliaryitems.class,sqlx);
		String sqly ="";
		for(EFVoucherAuxiliaryitems efVoucherAuxiliaryitems:lla){
			sqly+=",(select "+efVoucherAuxiliaryitems.getFnamefield() +" from "+efVoucherAuxiliaryitems.getFtable()+" where "+efVoucherAuxiliaryitems.getFnumberfield()+"=b."+efVoucherAuxiliaryitems.getFitemclassid().toLowerCase()+"id )"+ "  "+efVoucherAuxiliaryitems.getFitemclassid().toLowerCase()+"idname ";
		}
//		String sql = "select * from e_f_voucherentry where  data_corp='"+corpid+"'   and fvoucherid="+Id;
		String sql = "select b.data_corp,b.fvoucherid,fentryid,b.fexplanation,b.faccountid,b.fcur,b.frate,b.fdc,b.famountfor,b.famount,b.fitemid,b.fcustomid,b.fdeptid,b.fsupplierid,b.fempid,b.finventoryid,b.fqty,b.fprice,b.fsettlecode,b.fsettleno,b.fsettledate,b.ftransdate,b.ftransno,b.fmatch,b.ftransbal,b.ftransbalfor,b.fid,b.fcontrol,b.faccountid2,b.funit,b.fprojectid,b.xjllb,b.kmfcur"
				+ ",(select cast(fnumber as varchar(100))||ffullname from e_f_voucher_ac where faccountid=b.faccountid )fnumberfullname"
//				+ ",(select fname from ba_cw_pz_customer where fid=b.fcustomid )fcustomidname"
//				+ ",(select fname from ba_cw_pz_department where  fid=b.fdeptid )fdeptidname"
//				+ ",(select fname from ba_cw_pz_employee where fid=b.fempid )fempidname"
//				+ ",(select fname from ba_cw_pz_inventory where fid=b.finventoryid )finventoryidname"
//				+ ",(select fname from ba_cw_pz_project where fid=b.fprojectid )fprojectidname"
//				+ ",(select fname from ba_cw_pz_supplier where fid=b.fsupplierid )fsupplieridname "
		        +sqly
//				+ ",(select fname from ba_cw_pz_qt where fid=b.fqtid ) fqtidname,b.fqtid"
				+ ",c.fnumber,c.fname,c.flevel,c.fdetail,c.fparentid,c.frootid,c.fhelpercode,c.fgroupid,c.fitemclassid,c.ffullname,c.fisdelete,c.fcustom,c.fsupplier,c.fdept,c.femp,c.fisrateadj,c.fisqtyaux,c.fiscash,c.fisbank,c.fistrans,c.finventory,c.fisjrnl,c.fisitem,c.fplaccountid,c.fproject,c.flimited "
				+ ",case b.fdc when 1 then b.famount else 0 end debit,case b.fdc when -1 then b.famount else 0 end credit,case b.fdc when 1 then '借' when -1 then '贷' else '平' end fdcm "
				+ " from e_f_voucher a,e_f_voucherentry b,e_f_voucher_ac c where a.data_corp= b.data_corp and a.data_corp=c.data_corp and b.faccountid=c.faccountid  and a.fvoucherid=b.fvoucherid and a.fvoucherid="
				+ Id + " and a.data_corp='" + corpid+ "'  order by fentryid ";
		return vouchEntryDao.QueryListMap(sql);
	}

	@Override
	public Result getSubjectById(String id) {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
//		String sql = "select (SELECT STUFF(( SELECT ','+FCUR+'|'+fname+'|'+CONVERT(nvarchar,cast(frate   as   numeric(20,4))) from ba_cw_pz_currency   where data_corp='"
//				+ corpid
//				+ "' and CHARINDEX(','+FCUR+',' ,','+a.FCUR+',')>0 FOR XML PATH('') ), 1, 1, '')) xzfcur,"
//				+ "  fcur,fcur kmfcur,data_corp,faccountid,fnumber,fname,flevel,fdetail,fparentid,frootid,fdc,fhelpercode,fgroupid,fitemclassid,ffullname,fisdelete,fcustom,fsupplier,fdept,femp,fisrateadj,fisqtyaux,funit,fiscash,fisbank,fistrans,finventory,fisjrnl,fisitem,fplaccountid,fproject,flimited,fqt from e_f_voucher_ac a where a.faccountid='"
//				+ id + "'";
		String sql = "select  fcur,fcur kmfcur,data_corp,faccountid,fnumber,fname,flevel,fdetail,fparentid,frootid,fdc,fhelpercode,fgroupid,fitemclassid,ffullname,fisdelete,fcustom,fsupplier,fdept,femp,fisrateadj,fisqtyaux,funit,fiscash,fisbank,fistrans,finventory,fisjrnl,fisitem,fplaccountid,fproject,flimited,fqt from e_f_voucher_ac a where a.faccountid='"
				+ id + "'  and data_corp='"+corpid+"'";


		return Result.resultOk(vouchDao.QueryToFirstMap(sql));
	}

	@Override
	public List<Map> getYearMonthList() {

		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String startyearperiod=vouchDao.QueryFirstString("select (select fvalue from e_f_voucher_systemprofile where fkey='StartYear' and data_corp='"+corpid+"')||(select fvalue from e_f_voucher_systemprofile where fkey='StartPeriod' and data_corp='"+corpid+"')");
		String currentyearperiod=vouchDao.QueryFirstString("select (select fvalue from e_f_voucher_systemprofile where fkey='CurrentYear' and data_corp='"+corpid+"')||(select fvalue from e_f_voucher_systemprofile where fkey='CurrentPeriod' and data_corp='"+corpid+"')");
		String sql = "select  to_char(zz, 'YYYYMM') as yearperiod from   generate_series(date_trunc('month',to_date('"+startyearperiod+"','yyyymmdd')),  date_trunc('month',to_date('"+currentyearperiod+"','yyyymmdd')),'1 month') as zz  ";
		return vouchDao.QueryListMap(sql);
	}


//	@Override
//	public Result getAccountCurrency(String id) {
//		SessionUser securityUser=SessionUser.SessionUser();
//		String corpid=securityUser.getCorpId();
////		String sql = "select (SELECT STUFF(( SELECT ','+FCUR+'|'+fname+'|'+CONVERT(nvarchar,cast(frate   as   numeric(20,4))) from ba_cw_pz_currency   where data_corp='"
////				+ corpid
////				+ "' and CHARINDEX(','+FCUR+',' ,','+a.FCUR+',')>0 FOR XML PATH('') ), 1, 1, '')) xzfcur,"
////				+ "  fcur,fcur kmfcur,data_corp,faccountid,fnumber,fname,flevel,fdetail,fparentid,frootid,fdc,fhelpercode,fgroupid,fitemclassid,ffullname,fisdelete,fcustom,fsupplier,fdept,femp,fisrateadj,fisqtyaux,funit,fiscash,fisbank,fistrans,finventory,fisjrnl,fisitem,fplaccountid,fproject,flimited,fqt from e_f_voucher_ac a where a.faccountid='"
////				+ id + "'";
//		String sql = "select  fcur,fcur kmfcur,data_corp,faccountid,fnumber,fname,flevel,fdetail,fparentid,frootid,fdc,fhelpercode,fgroupid,fitemclassid,ffullname,fisdelete,fcustom,fsupplier,fdept,femp,fisrateadj,fisqtyaux,funit,fiscash,fisbank,fistrans,finventory,fisjrnl,fisitem,fplaccountid,fproject,flimited,fqt from e_f_voucher_ac a where a.faccountid='"
//				+ id + "'  and data_corp='"+corpid+"'";
//
//
//		return Result.resultOk(vouchDao.QueryToFirstMap(sql));
//	}

	@Override
	public List<SelectVo> getFGROUPID() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
//		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class, "select fgenerateid id , fname \"text\" from e_f_voucher_certificateword a  where finternalind='t_Voucher'  and data_corp='"+securityUser.getCorpId()+"'");
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class, "select fgenerateid id , fname \"text\" from e_f_voucher_certificateword a  where finternalind='t_Voucher'  and data_corp='"+securityUser.getCorpId()+"'");
//		List<SelectVo> list = new ArrayList<SelectVo>();
//		sysRight.forEach(item -> {
//			list.add(new SelectVo(item.getRightName(), item.getRightName()));
//		});
		return list;
	}

	@Override
	public EFVoucher findById(BigInteger Id) {
		return vouchDao.findById(Id).orElse(null);
	}

//	@Override
//	public EFVoucher findById(Long Id) {
//		return vouchDao.findById(Id).orElse(null);
//	}

//	@Override
//	public EFVoucher save(EFVoucher eFVoucherAuxiliaryitems)throws Exception   {
//		SessionUser securityUser=SessionUser.SessionUser();
//		eFVoucherAuxiliaryitems.setDataCorp(securityUser.getCorpId());
//		boolean x=eFVoucherAuxiliaryitems.getFusebstable().equals("Y")?judgeUniqueset(eFVoucherAuxiliaryitems):judgeUniqueother(eFVoucherAuxiliaryitems);
//		if(x){
//			eFVoucherAuxiliaryitems.setFitemid(sysGenNoteService.getNote("auxiliaryitems","1","",4));
//			return vouchDao.save(eFVoucherAuxiliaryitems);
//		} else {
//			throw new Exception("项目名称已存在");
//		}
//
//	}
//
//	@Override
//	public void update(EFVoucher EFVoucher) {
//		vouchDao.dynamicSave(EFVoucher, vouchDao.findById(EFVoucher.getFvoucherid()).orElse(null));
//	}

//	@Override
//	public boolean judgeUniqueset(EFVoucher efva) {
//		return vouchDao.findAll(Specifications.where((c) -> {
//			SessionUser securityUser=SessionUser.SessionUser();
//			c.eq("dataCorp",securityUser.getCorpId());
//			c.eq("fitemclassid", efva.getFvoucherid());
//		})).size() <= 0;
//	}

//	@Override
//	public boolean judgeUniqueother(EFVoucher efva) {
//		return vouchDao.findAll(Specifications.where((c) -> {
//			SessionUser securityUser=SessionUser.SessionUser();
//			c.eq("dataCorp",securityUser.getCorpId());
//			c.eq("fitemclassid", efva.getFvoucherid());
//			c.eq("fnumber", efva.getFnumber());
////			c.eq("fname", efva.getFname());
//		})).size() <= 0;
//	}

//	@Override
//	public Result deleteById(Long Id) {
//
//		vouchDao.deleteById(Id);
//			return Result.resultOkMsg("删除成功");
//
//	}




//	@Override
//	public List<EFVoucher> findAll() {
//		return vouchDao.findAll();
//	}



	@Override
	public List<Map>  getExplanationList() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();

		String sql = "select * from e_f_voucher_summary a   "+ "order by fid desc" ;

		return vouchDao.QueryListMap(sql);

	}

	@Override
	public BasePage<Map<String, Object>> getSubjectList(BaseDto baseDto) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select (SELECT top 1 fname from e_f_voucher_acctgroup where fgroupid=a.fgroupid )fgroupidname,cast(a.fnumber as nvarchar(100))+fname fnumbername,cast(a.fnumber as nvarchar(100))+ffullname fnumberfullname,a.* from  e_f_voucher_ac a  where a.data_corp='"
				+ corpid + "'";

		return vouchDao.QueryMapPageList(baseDto, sql, true);
	}





//	public List getSubjectTreeList() {
//		SessionUser securityUser=SessionUser.SessionUser();
//		String corpid=securityUser.getCorpId();
//		String sql = "select  (SELECT top 1 fname from e_f_voucher_acctgroup where fgroupid=a.fgroupid )fgroupidname,cast(a.fnumber as nvarchar(100))+'|'+fname fnumbername,a.* from  e_f_voucher_ac a   where a.data_corp='"
//				+ corpid + "' ";
//		return vouchDao.QueryListMap(sql + " order by fnumber");
//
//	}



	@Override
	public BasePage<Map<String, Object>>  getAssistingGridlist(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();

		String qtable = map.get("qtable").toString();
		String sql = "select * ,'" + qtable + "' itemclassid " + (qtable.equalsIgnoreCase("ba_cw_pz_inventory") ? " " : ",case fisdelete when 1 then '禁用' else '启用' end flag ") + " from " + qtable
				+ " a  " ;

		return vouchDao.QueryMapPageList(baseDto, sql, true);

	}







	@Override
	public List<SelectVo> getAccountCurrency(String code) {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select distinct param_id as id ,param_name  as text from sys_param where param_mod='财务' and param_type='交易币种' " +
				" and  position(param_id in (select fcur from e_f_voucher_ac where faccountid='" + code + "'))>0 order by param_id  ";
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class,sql);
		
		return list;
	}
	@Override
	public List<SelectVo> getCurrency() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select param_id as id ,param_name as text  from sys_param  where param_mod='财务' and param_type='交易币种'   order by FCUR  ";
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class,sql);
		return list;
	}
	@Override
	public Result getCurrencyRate(String code) {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "select param_value1  from sys_param  where param_mod='财务' and param_type='交易币种'  and param_id='" + code + "' ";
		return Result.resultOk(vouchDao.QueryFirstString(sql));
	}

	public List<SelectVo> getVoucherWord(String code) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "SELECT cast(a.fgenerateid as varchar(30)) combid ,a.fname combtext from T_bs_generatecode a  where finternalind='t_Voucher' and data_corp='"
				+ corpid + "'  order by fisdefault desc";
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class,sql);
		return list;
	}

	public List<SelectVo> getYearPeriod() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();

		String sql = " declare @begin datetime,@end datetime " + " set @begin=(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'startyear' and data_corp = '" + corpid
				+ "')+right('000000000'+(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'startperiod' and data_corp = '" + corpid + "'),2)+'01' "
				+ " set @end=(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'CurrentYear' and data_corp = '" + corpid
				+ "')+right('000000000'+(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'CurrentPeriod' and data_corp = '" + corpid + "'),2)+'01' " + " declare @months int "
				+ " set @months=DATEDIFF(month,@begin,@end) " + " select convert(varchar(6),DATEADD(month,number,@begin) ,112) AS combid, "
				+ " convert(varchar(4),DATEADD(month,number,@begin) ,112)+'第'+cast(DATEPART(mm,DATEADD(month,number,@begin)) as varchar(2))+'期' AS combtext " + " from master.dbo.spt_values "
				+ " where type='p' AND number<=@months  order by combid desc";
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class,sql);
		return list;
	}

	public List<SelectVo> getYearQuarter() {
		SessionUser securityUser=SessionUser.SessionUser();
		String corpid=securityUser.getCorpId();
		String sql = "declare @begin datetime,@end datetime "
				+ " set @begin=(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'startyear' and data_corp = '"
				+ corpid
				+ "')+right('000000000'+(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'startperiod' and data_corp = '"
				+ corpid
				+ "'),2)+'01' "
				+ " set @end=(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'CurrentYear' and data_corp = '"
				+ corpid
				+ "')+right('000000000'+(select fvalue from T_BS_SYSTEMPROFILE where fkey = 'CurrentPeriod' and data_corp = '"
				+ corpid
				+ "'),2)+'01' "
				+ " declare @quarters int "
				+ " set @quarters=DATEDIFF(qq,@begin,@end) "
				+ " select convert(varchar(6),DATEADD(qq,number,@begin) ,112) +','+convert(varchar(6),DATEADD(mm,DATEPART(Quarter,DATEADD(qq,number,@begin))*3-Month(DATEADD(qq,number,@begin)),DATEADD(qq,number,@begin)),112) AS combid, "
				+ " convert(varchar(4),DATEADD(qq,number,@begin) ,112)+'第'+cast(DATEPART(qq,DATEADD(qq,number,@begin)) as varchar(2))+'季度' AS combtext " + " from master.dbo.spt_values "
				+ " where type='p' AND number<=@quarters ";
		List<SelectVo> list = vouchDao.QueryToVo(SelectVo.class,sql);
		return list;
	}





	@Override
	public EFVoucher saveData(EFVouchVo efVouchVo)throws Exception {
		BigInteger note=vouchDao.getVoucherid();
		SessionUser securityUser=SessionUser.SessionUser();
		if(null==efVouchVo.getEfVoucher().getFvoucherid()) efVouchVo.getEfVoucher().setFvoucherid(note);
		efVouchVo.getEfVoucher().setFischecked(0);
		efVouchVo.getEfVoucher().setFuserno(securityUser.getUserId());
		efVouchVo.getEfVoucher().setFusername(securityUser.getUsername());
		efVouchVo.getEfVoucher().setFcreatetime(new Date());
		efVouchVo.getEfVoucher().setFtranstype("自增");
		efVouchVo.getEfVoucher().setDataCorp(securityUser.getCorpId());
		certificateWordDao.findByFgenerateid(efVouchVo.getEfVoucher().getFgroupid()).getFname();
		efVouchVo.getEfVoucher().getFnumber().toString();
		efVouchVo.getEfVoucher().setFvoucherno(certificateWordDao.findById(efVouchVo.getEfVoucher().getFgroupid()).orElse(null).getFname()+"-"+efVouchVo.getEfVoucher().getFnumber().toString());

		if (!efVouchVo.getAddedList().isEmpty()){
			efVouchVo.getAddedList().forEach( item -> {
				Account acc = accountDao.findById(item.getFaccountid()).orElse(null);

				item.setFvoucherid(efVouchVo.getEfVoucher().getFvoucherid());
				item.setFid(vouchEntryDao.getEntryid());
				item.setDataCorp(securityUser.getCorpId());

				if (null == acc.getFcur()) {
					item.setFcur("RMB");
				}
				item.setFcontrol(0);
				// -1贷方,1 借方

				if(item.getFdc()>0){
					item.setFamount(item.getDebit());
				}else{
					item.setFamount(item.getCredit());
				}
				if (acc.getFisrateadj() == 0) {
					item.setFamountfor(item.getFamount());
					item.setFrate(new BigDecimal(1));
				}

				if (acc.getFisrateadj() == 0) {
					item.setFamountfor(item.getFamount());
					item.setFrate(new BigDecimal(1));
				}

//				if (x == 0) {
//					// 对方科目就是现在所提及的会计科目的对应科目。是指同一经济业务中借方或者贷方对应的贷方或者借方的科目
//					item.setFaccountid2(item.getFaccountid());
//				} else {
//					// model.setFexplanation(modelx.getFexplanation());
//					item.setFaccountid2(modelx.getFaccountid());
//				}
				item.setFtransbal(item.getFamount());
				item.setFtransbalfor(item.getFamountfor());
				item.setDataCorp(securityUser.getCorpId());
				vouchEntryDao.save(item);

			});
		}
		if (!efVouchVo.getDeletedList().isEmpty()) {
			vouchEntryDao.deleteAll(efVouchVo.getDeletedList());
		}
		if(!efVouchVo.getModifyedList().isEmpty()){
			efVouchVo.getModifyedList().forEach( item ->{
				Account acc = accountDao.findById(item.getFaccountid()).orElse(null);

				item.setFvoucherid(efVouchVo.getEfVoucher().getFvoucherid());
				if(null==item.getFid()) item.setFid(vouchEntryDao.getEntryid());


				if (null == acc.getFcur()) {
					item.setFcur("RMB");
				}
				item.setFcontrol(0);
				// -1贷方,1 借方

				if(item.getFdc()>0){
					item.setFamount(item.getDebit());
				}else{
					item.setFamount(item.getCredit());
				}
				if (acc.getFisrateadj() == 0) {
					item.setFamountfor(item.getFamount());
					item.setFrate(new BigDecimal(1));
				}

//				if (x == 0) {
//					// 对方科目就是现在所提及的会计科目的对应科目。是指同一经济业务中借方或者贷方对应的贷方或者借方的科目
//					item.setFaccountid2(item.getFaccountid());
//				} else {
//					// model.setFexplanation(modelx.getFexplanation());
//					item.setFaccountid2(modelx.getFaccountid());
//				}
				item.setFtransbal(item.getFamount());
				item.setFtransbalfor(item.getFamountfor());
				item.setDataCorp(securityUser.getCorpId());
				vouchEntryDao.dynamicSave(item,vouchEntryDao.findById(item.getFid()).orElse(null));
			});
		}
		EFVoucher   voucher=vouchDao.dynamicSave(efVouchVo.getEfVoucher(),vouchDao.findById(efVouchVo.getEfVoucher().getFvoucherid()).orElse(null));

		return  voucher;
	}



}