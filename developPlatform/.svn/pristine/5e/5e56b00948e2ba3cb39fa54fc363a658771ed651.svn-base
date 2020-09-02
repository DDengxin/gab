package com.tengzhi.business.mesGz.gzck.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceipt.vo.ECkInVo;
import com.tengzhi.business.ck.yw.ckck.model.ECkOut;
import com.tengzhi.business.mesGz.gzck.dao.LyrkDao;
import com.tengzhi.business.mesGz.gzck.service.LyrkService;
import com.tengzhi.business.mesGz.gzck.vo.ECKOutVo;
import com.tengzhi.business.mesGz.gzck.vo.EckOut;
import com.tengzhi.business.mesGz.gzck.vo.EckOut.ECkOut_PK;
import com.tengzhi.business.mesGz.gzda.dao.GzcodeDao;
import com.tengzhi.business.mesGz.gzda.model.Gzda;
import com.tengzhi.business.mesGz.gzda1.model.MGzMjda;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class LyrkServiceImpl implements LyrkService {
	@Autowired
	private LyrkDao eCkOutDao;

	@Autowired
	private ECkInDao InDao;

	@Autowired
	private GzcodeDao gzDao;

	@Autowired
	private com.tengzhi.business.mesGz.gzda1.dao.MGzMjdaDao mGzMjdaDao;

	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Override
	public BasePage<EckOut> getSrchTopList(BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		Object srchRq1 = map.get("srchRq1");
		Object srchRq2 = map.get("srchRq2");
		Object outNote = map.get("srchoutNote");
		Object supplier = map.get("srchSupplier");
		Object cgStype = map.get("cgStype");
		Object outFlag = map.get("outFlag");
		
		String sqlWhere = " where out_act='GM31' and out_flag in('登记','确认','库审') ";

		if (!StringUtils.isBlank(srchRq1.toString())) {
		
			sqlWhere += " and out_rq >='" + srchRq1 + "'";
		}
		if (!StringUtils.isBlank(srchRq2.toString())) {
			
			sqlWhere += " and out_rq <='" + srchRq2 + "'";
		}

		if (!StringUtils.isBlank(outNote.toString())) {
			sqlWhere += " and out_note like'%" + outNote + "%'";
		}
		if (!StringUtils.isBlank(supplier.toString())) {
			sqlWhere += " and out_customer like'%" + supplier + "%'";
		}
		if (cgStype != null && cgStype.toString().trim().length() > 0) {
			sqlWhere += " and out_type ='"+ cgStype + "' ";
		}

		String sql = "select out_rq ,out_cklib,out_note ,f_getname('GETDWNAME',out_customer,'',data_corp)out_customer ,f_getname('GETPARAMNAME',out_lib,'GM','')out_lib,f_getname('GETPARAMNAME',out_act,'GMACT','')out_act,out_bz,out_tax ,sum(out_js) out_js,sum(out_sl) out_sl,sum(out_zl) out_zl,out_flag ,f_getname('GETUSERNAME',data_man,'',data_corp)data_man,f_getname('GETUSERNAME',out_lyr,'',data_corp) out_lyr ,MAX(data_date) data_date,f_getname('GETUSERNAME',out_man,'',data_corp)out_man,MAX(out_date) out_date,data_corp  "
				+ " from  e_ck_out " + sqlWhere
				+ "  group by out_rq,out_cklib,out_note,out_customer,out_act,out_flag,data_man,data_corp,out_lib,out_bz,out_tax,out_lyr,out_man  ";

		sql = "select * from (" + sql + ") n ";
		String countSql = "select count(*) cn from (" + sql + ")t ";
		return eCkOutDao.QueryPageLists(sql, countSql, baseDto);
	}

	@Override
	public BasePage<EckOut> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object outNote = map.get("outNote");
		Object outCklib = map.get("outCklib");
		String sql = "select a.*,c.*,b.cpcode_name,b.cpcode_size,b.cpcode_fl,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 from (select out_contract||'_'||out_Kfcode as id,out_code,out_js ,out_sl ,out_zl ,out_pack ,out_bpack ,out_price ,out_contract ,out_kfcontract ,out_Kfcode, out_checkflag , out_month ,out_type ,out_luono ,out_vnote,out_hs  from  e_ck_out where out_note='"
				+ outNote
				+ "')a left join e_js_cpcode b on a.out_code=b.cpcode_id left join (select code,sum(sl) sl from v_stock where lib='"
				+ outCklib + "'  GROUP BY code)c on a.out_code=c.code ";
		sql = "select * from (" + sql + ")n";
		return eCkOutDao.QueryNoPageLists(sql);
	}














	@Override
	public Result save(ECKOutVo eCkOutVo) throws Exception {
		List<MGzMjda> mGzMjdas=new ArrayList<>();
		List<EckOut> addECkOuts = new ArrayList<EckOut>();
		List<EckOut> modifyedECkIns = new ArrayList<EckOut>();
		List<EckOut> eCkOutList = eCkOutVo.getEckOutMx();

		SessionUser sessionUser=SessionUser.SessionUser();
		// 处理表头
		String note = sysGenNoteService.getyyMMNote4(ECkOut.class, "GM31");
		// 赋默认值
		for (int i = 0; i < eCkOutList.size(); i++) {
			EckOut s = eCkOutList.get(i);
			s.setOutFlag("库审");
			s.setDataMan(sessionUser.getUserId());
			s.setDataCorp(sessionUser.getCorpId());
			s.setOutAct("GM31");
			s.setOutRq(eCkOutVo.geteCkOut().getOutRq());
			s.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
			s.setOutLyr(eCkOutVo.geteCkOut().getOutLyr());
			s.setOutLib(eCkOutVo.geteCkOut().getOutLib());
			s.setOutCklib(eCkOutVo.geteCkOut().getOutCklib());
		}
		int size = eCkOutList.size();
		for (int i = 0; i < size; i++) {
			EckOut eck = eCkOutList.get(i);
			/*if (eck.getOutPack() == null) {
				try {
					String pack = sysGenNoteService.getyyyyMMddNote4(EckIn.class, "P");
					eck.setOutPack(pack);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else { // 判断保存的包装号有没有重复
				if (checkExists(eck, eCkOutVo.getAddedList())) {
					return Result.error("第" + (i + 1) + "行包装号重复，不能保存！");
				}
				// 判断数据库有没有该包装号
				int count = eCkOutDao.getAddInBack(eck.getOutPack());
				if (count == 1) {
					return Result.error("包装号：“" + eck.getOutPack() + "”在数据库中已存在，保存失败！");
				}
			}*/
			// 批次
			String pc = eck.getOutHs();
			if (eck.getOutSl().intValue() > 1 && "单件".equals(pc)) {
				double num = eck.getOutSl().intValue();
				System.out.println(eck.getOutPack() + "pack");
				String pack = eck.getOutPack();
				eck.setOutSl(BigDecimal.ONE);
				for (int j = 0; j < num - 1; j++) {
					EckOut tmp = new EckOut();
					BeanUtil.copyProperties(eck, tmp);
					tmp.setOutPack(pack + "-" + (j + 1));
					eCkOutList.add(tmp);
				}
			} else {
				eCkOutList.add(eck);
			}
		}
		for (int i = 0; i < eCkOutList.size(); i++) {
			EckOut model = eCkOutList.get(i);
			model.setOutNote(note);
			model.setOutRq(eCkOutList.get(i).getOutRq());
			model.setOutAct(eCkOutList.get(i).getOutAct());
			model.setOutCustomer(eCkOutList.get(i).getOutCustomer());
			model.setOutFlag(eCkOutList.get(i).getOutFlag());
			model.setDataMan(eCkOutList.get(i).getDataMan());
			model.setDataDate(new Date());
			model.setDataCorp(eCkOutList.get(i).getDataCorp());
			model.setOutCode(eCkOutList.get(i).getOutCode());
			model.setOutBz(eCkOutList.get(i).getOutBz());
			model.setOutTax(eCkOutList.get(i).getOutTax());
			model.setOutLib(eCkOutList.get(i).getOutLib());
			model.setOutLyr(eCkOutList.get(i).getOutLyr());
			model.setOutCklib(eCkOutList.get(i).getOutCklib());
			model.setOutType(eCkOutList.get(i).getOutType());




			// 默认大包装号和包装号相等，后续可能需要根据产品规则生成
			model.setOutBpack(eCkOutList.get(i).getOutPack());
			addECkOuts.add(model);
		}

		// 修改
		if (!eCkOutVo.getModifyedList().isEmpty()) {
			for (int i = 0; i < eCkOutVo.getModifyedList().size(); i++) {
				EckOut item = eCkOutVo.getModifyedList().get(i);
				item.setOutNote(note);
				item.setOutRq(eCkOutVo.geteCkOut().getOutRq());
				item.setOutAct(eCkOutVo.geteCkOut().getOutAct());
				item.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
				item.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
				item.setDataMan(sessionUser.getUserId());
				item.setDataDate(new Date());
				item.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
				item.setOutCode(item.getOutKfcode());
				item.setOutBz(eCkOutVo.geteCkOut().getOutBz());
				item.setOutTax(eCkOutVo.geteCkOut().getOutTax());
				item.setOutType(eCkOutVo.geteCkOut().getOutType());
				/* item.setOutHs(eCkOutVo.geteCkOut().getOutHs()); */
				// 判断保存的包装号有没有重复
				if (checkExists(item, eCkOutVo.getModifyedList())) {
					return Result.error("第" + (i + 1) + "行包装号重复，不能保存！");
				}
				// 判断数据库有没有该包装号
				/*EckOut oldCkIn = eCkOutDao.findById(new ECkOut_PK(note, item.getOutPack())).orElse(null);
				if (!oldCkIn.getOutPack().equals(item.getOutPack())) {
					int count = eCkOutDao.getAddInBack(item.getOutPack());
					if (count == 1) {
						return Result.error("包装号：“" + item.getOutPack() + "”在数据库中已存在，保存失败！");
					}
				}*/
				modifyedECkIns.add(item);
			}
		}
		// 业务逻辑判断end //开始保存到数据库
		if (!addECkOuts.isEmpty()) {
			addECkOuts.forEach(item -> {
				eCkOutDao.save(item);
			});
		}
		if (!mGzMjdas.isEmpty()) {
			mGzMjdas.forEach(item -> {
				mGzMjdaDao.save(item);
			});
		}
		if (!modifyedECkIns.isEmpty()) {
			modifyedECkIns.forEach(item -> {
				eCkOutDao.dynamicSave(item, eCkOutDao.findById(new ECkOut_PK(note, item.getOutPack())).orElse(null));
			});
		}
		if (!eCkOutVo.getDeletedList().isEmpty()) {
			eCkOutVo.getDeletedList().forEach(item -> {
				ECkOut_PK pk = new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), item.getOutPack());
				eCkOutDao.deleteById(pk);
			});
		} // end
		return Result.resultOk(eCkOutVo.geteCkOut());
	}


































	@Override
	public Result update(ECKOutVo eCkOutVo) throws Exception {
		List<EckOut> addECkIns = new ArrayList<EckOut>();
		List<EckOut> modifyedECkIns = new ArrayList<EckOut>();
		SessionUser securityUser=SessionUser.SessionUser();
		eCkOutVo.geteCkOut().setDataMan(securityUser.getUsername());
		eCkOutVo.geteCkOut().setDataDate(new Date());
		eCkOutVo.geteCkOut().setDataCorp(securityUser.getCorpId());
		eCkOutVo.geteCkOut().setOutFlag("库审");
		eCkOutVo.geteCkOut().setOutAct("GM71");
		// 业务逻辑判断start
		// 新增
		for (int i = 0; i < eCkOutVo.getEckOutMx().size(); i++) {
			EckOut model = eCkOutVo.getEckOutMx().get(i);
			model.setOutNote(eCkOutVo.geteCkOut().getOutNote());
			model.setOutRq(eCkOutVo.geteCkOut().getOutRq());
			model.setOutAct(eCkOutVo.geteCkOut().getOutAct());
			model.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
			model.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
			model.setDataMan(securityUser.getUsername());
			model.setDataDate(eCkOutVo.geteCkOut().getDataDate());
			model.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
			model.setOutCode(model.getOutCode());
			model.setOutBz(eCkOutVo.geteCkOut().getOutBz());
			model.setOutTax(eCkOutVo.geteCkOut().getOutTax());
			model.setOutLib(eCkOutVo.geteCkOut().getOutLib());
			model.setOutLyr(eCkOutVo.geteCkOut().getOutLyr());
			model.setOutCklib(eCkOutVo.geteCkOut().getOutCklib());
			model.setOutType(eCkOutVo.geteCkOut().getOutType());
			// 如果为空自动生成包装号
			if (model.getOutPack() == null) {
				try {
					String pack = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
					model.setOutPack(pack);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// 判断数据库有没有该包装号
				int count = eCkOutDao.getAddInBack(model.getOutPack());
				if (count == 1) {
					return Result.error("包装号：“" + model.getOutPack() + "”在数据库中已存在，保存失败！");
				}
			}
			// 默认大包装号和包装号相等，后续可能需要根据产品规则生成
			model.setOutBpack(model.getOutPack());
			addECkIns.add(model);
		}
		// 修改
		if (!eCkOutVo.getModifyedList().isEmpty()) {
			for (int i = 0; i < eCkOutVo.getModifyedList().size(); i++) {
				EckOut item = eCkOutVo.getModifyedList().get(i);
				item.setOutNote(eCkOutVo.geteCkOut().getOutNote());
				item.setOutRq(eCkOutVo.geteCkOut().getOutRq());
				item.setOutAct(eCkOutVo.geteCkOut().getOutAct());
				item.setOutCustomer(eCkOutVo.geteCkOut().getOutCustomer());
				item.setOutFlag(eCkOutVo.geteCkOut().getOutFlag());
				item.setDataMan(securityUser.getUsername());
				item.setDataDate(eCkOutVo.geteCkOut().getDataDate());
				item.setDataCorp(eCkOutVo.geteCkOut().getDataCorp());
				item.setOutCode(item.getOutKfcode());
				item.setOutBz(eCkOutVo.geteCkOut().getOutBz());
				item.setOutTax(eCkOutVo.geteCkOut().getOutTax());
				item.setOutLyr(eCkOutVo.geteCkOut().getOutLyr());
				item.setOutLib(eCkOutVo.geteCkOut().getOutLib());
				item.setOutLib(eCkOutVo.geteCkOut().getOutCklib());
				item.setOutType(eCkOutVo.geteCkOut().getOutType());
				// 判断保存的包装号有没有重复
				if (checkExists(item, eCkOutVo.getModifyedList())) {
					return Result.error("第" + (i + 1) + "行包装号重复，不能保存！");
				}
				// 判断数据库有没有该包装号
				EckOut oldCkIn = eCkOutDao.findById(new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), item.getOutPack()))
						.orElse(null);
				if (!oldCkIn.getOutPack().equals(item.getOutPack())) {
					int count = eCkOutDao.getAddInBack(item.getOutPack());
					if (count == 1) {
						return Result.error("包装号：“" + item.getOutPack() + "”在数据库中已存在，保存失败！");
					}
				}

				modifyedECkIns.add(item);
			}
		}
		// 业务逻辑判断end
		// 开始保存到数据库
		if (!addECkIns.isEmpty()) {
			addECkIns.forEach(item -> {
				System.out.println("走修改方法的新增");
				eCkOutDao.save(item);
			});
		}

		if (!modifyedECkIns.isEmpty()) {
			System.out.println(1111);
			modifyedECkIns.forEach(item -> {
				eCkOutDao.dynamicSave(item, eCkOutDao
						.findById(new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), item.getOutPack())).orElse(null));
			});
		}

		if (!eCkOutVo.getDeletedList().isEmpty()) {
			System.out.println("删除");
			eCkOutVo.getDeletedList().forEach(item -> {
				ECkOut_PK pk = new ECkOut_PK(eCkOutVo.geteCkOut().getOutNote(), item.getOutPack());
				eCkOutDao.deleteById(pk);
			});
		}
		// end
		return Result.resultOkMsg("修改成功");
	}

	@Override
	public void deleteById(String outNote) {
		eCkOutDao.deleteByInNote(outNote);
	}

	@Override
	public EckOut findByInNote(BaseDto baseDto) throws Exception {
		Map<String, String> map = baseDto.getParamsMap();
		Object outNote = map.get("outNote");
		EckOut in = new EckOut();
		String sqlString = "select * from (select out_rq,out_code,out_note,out_customer, f_getname('GETDEPTNAME',out_customer,'',data_corp)out_customers,out_lyr,f_getname('GETUSERNAME',out_lyr,'',data_corp)out_lyrs,out_hs,out_lib,out_act,out_bz,out_tax,out_flag,data_man,data_corp,out_type,out_luono,out_vnote,out_cklib  from e_ck_out where out_Note ='"
				+ outNote + "')a  ";
		BasePage<EckOut> list = eCkOutDao.QueryNoPageLists(sqlString);
		System.out.println(list.getSize());
		if (list.getContent().size() > 0) {
			in = list.getContent().get(0);
		}
		return in;
	}

	@Override
	public List<Map> getSlSelectList(String outNote) throws IOException {
		Map<Object, Object> map = new HashMap<Object, Object>();
		String sqlString = "select * from (select a.*,b.cpcode_name \"cpcodeName\",b.cpcode_size \"cpcodeSize\",b.cpcode_fl \"cpcodeFl\",b.cpcode_xp \"cpcodeXp\",b.cpcode_check \"cpcodeCheck\",b.cpcode01 ,b.cpcode02,b.cpcode03 from (select out_code , out_rq \"inRq\",out_flag \"inFlag\",out_code \"inCode\",out_js \"inJs\",out_hs \"inHs\",data_corp \"dataCorp\",out_zl \"inZl\",out_bpack \"inBpack\",out_pack \"gmCode\",out_pack \"inPack\",out_lib \"inLib\",out_lyr \"inLyr\",out_kfcode \"inKfcode\",data_man \"dataMan\",out_act \"inAct\",out_customer \"inCustomer\",out_sl \"inSl\",out_note \"inNote\",out_type \"inType\" from e_ck_out  where out_note ='"
				+ outNote + "')a left join e_js_cpcode b on a.out_code=b.cpcode_id)n";
		List<Map> list = eCkOutDao.QueryListMap(sqlString);
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			System.out.println(map + "..");
			double count = Double.parseDouble(map.get("inSl").toString());
			String pc = map.get("cpcode01").toString();
			System.out.println("单位" + pc);
			if (count > 1 && "单件".equals(pc)) {
				map.put("inSl", "1.000");
				for (int j = 0; j < count - 1; j++) {
					list.add(map);
				}
			}
		}
		return list;
	}

	@Override
	public Result getFlag(String outNote, String flag) {
		String flagString = eCkOutDao.getFlag(outNote);
		if (flag.equals(flagString)) {
			return Result.resultOk("操作成功！");
		}
		return Result.error("该单不是“" + flag + "”状态,不能操作！");
	}

	public Result setFlag(String inNote, String flag) {
		eCkOutDao.setFlag(inNote, flag);
		return Result.resultOk("操作成功！");
	}

	@SuppressWarnings("unused")
	private boolean checkExists(EckOut model, List<EckOut> eCkOuts) {
		boolean isflag = false;
		for (int i = 0; i < eCkOuts.size(); i++) {
			EckOut indexrow = eCkOuts.get(i);
			if (!indexrow.getId().equals(model.getId())) {
				if (indexrow.getOutPack().equals(model.getOutPack())) {
					isflag = true;
					break;
				}
			}
		}
		return isflag;
	}

	@Override
	public List<Map> getLib(String parent_id, String param_type) {
		Object[] val = { parent_id, param_type };
		String sql = "select param_id id,param_name as text  from sys_param  where parent_id=?  and param_type=? order by  param_ord ";
		return eCkOutDao.QueryListMap(sql, val);
	}

	@Override
	public Map<String, Object> getWlbmSelectList(BaseDto baseDto) throws IOException {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = baseDto.getParamsMap();
		Object srchCode = map.get("srchCode");
		Object srchName = map.get("srchName");
		Object srchSize = map.get("srchSize");
		Object cgStype = map.get("cgStype");
		Object lib = map.get("lib");
		String sqlWhere = "";

		if (srchCode != null && srchCode.toString().trim().length() > 0) {
			sqlWhere += " and  cpcode_id like '%" + srchCode + "%' ";
		}
		if (srchName != null && srchName.toString().trim().length() > 0) {
			sqlWhere += " and cpcode_name like '%" + srchName + "%' ";
		}
		if (srchSize != null && srchSize.toString().trim().length() > 0) {
			sqlWhere += " and cpcode_size like '%" + srchSize + "%' ";
		}

		String sql = "select v.code,v.dtype,v.sl,e.cpcode_name,f_getparamname('GETBYPARENTID',e.cpcode_bz,'计量单位','技术','','"+   SessionUser.getCurrentCorpId()   +"')  cpcode_size,e.cpcode_fl,e.cpcode_bz,e.cpcode_id cpcodeid,f_getparamname('GETBCPCODENAME',e.cpcode_name,'','技术',v.dtype,'"+   SessionUser.getCurrentCorpId()   +"') AS cpcodename,e.cpcode_name_en cpcodenameen,e.cpcode_size cpcodesize,e.cpcode_size_en cpcodesizeen,e.cpcode_fl cpcodefl,e.cpcode_bz cpcodebz,e.cpcode_xp cpcodexp,f_get_param_name('检验档案',e.cpcode_check,'"+   SessionUser.getCurrentCorpId()   +"','cn') cpcodecheck,e.cpcode01,e.cpcode02,e.cpcode03,e.cpcode_flag cpcodeflag,e.data_man dataman,e.data_rq datarq,e.data_corp datacorp,e.cpcode_flid cpcodeflid,e.cpcode_dunit cpcodedunit,e.cpcode_sm cpcodesm,e.cpcode_pb cpcodepb,pack   from (select code,  sl,pack,dtype from v_stock where lib='"
				+ lib + "' ) v , (select * from e_js_cpcode where cpcode_flag='Y' " + sqlWhere
				+ ") e where v.code=e.cpcode_id and e.cpcode_fl='GZMJ' ";
		rmap.put("data", eCkOutDao.QueryListMap(sql));
		rmap.put("status", true);
		return rmap;
	}

	@Override
	public Result gzmj(ECkInVo eCkInVo) throws Exception {
		List<ECkIn> addECkIns = new ArrayList<ECkIn>();
		List<Gzda> addGzda = new ArrayList<Gzda>();

		String note = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "GM");
		SessionUser securityUser=SessionUser.SessionUser();
		eCkInVo.geteCkIn().setInFlag("库审");
		eCkInVo.geteCkIn().setDataMan(securityUser.getUsername());
		eCkInVo.geteCkIn().setDataDate(new Date());
		eCkInVo.geteCkIn().setDataCorp(securityUser.getCorpId());
		eCkInVo.geteCkIn().setInAct("GM31");

		eCkInVo.getGzda().setGmStatus("登记");
		eCkInVo.getGzda().setMan(securityUser.getUsername());

		// 业务逻辑判断start
		// 新增
		for (int i = 0; i < eCkInVo.geteCkInMx().size(); i++) {
			ECkIn model = eCkInVo.geteCkInMx().get(i);
			model.setInNote(eCkInVo.geteCkInMx().get(i).getInNote());
			model.setInRq(eCkInVo.geteCkInMx().get(i).getInRq());
			model.setInAct(eCkInVo.geteCkInMx().get(i).getInAct());
			model.setInCustomer(eCkInVo.geteCkInMx().get(i).getInCustomer());
			model.setInFlag("库审");
			model.setDataMan(securityUser.getUsername());
			model.setDataDate(eCkInVo.geteCkInMx().get(i).getDataDate());
			model.setDataCorp(eCkInVo.geteCkInMx().get(i).getDataCorp());
			model.setInCode(eCkInVo.geteCkInMx().get(i).getInCode());
			model.setInBz(eCkInVo.geteCkInMx().get(i).getInBz());
			model.setInTax(eCkInVo.geteCkInMx().get(i).getInTax());
			model.setInType(eCkInVo.geteCkInMx().get(i).getInType());
			// 默认大包装号和包装号相等，后续可能需要根据产品规则生成
			model.setInBpack(eCkInVo.geteCkInMx().get(i).getInPack());
			addECkIns.add(model);
		}
		SessionUser sessionUser=SessionUser.SessionUser();
		for (int i = 0; i < eCkInVo.getGzdaMx().size(); i++) {
			Gzda model = eCkInVo.getGzdaMx().get(i);
			model.setGmCode(eCkInVo.getGzdaMx().get(i).getGmCode());
			model.setGmSc(eCkInVo.getGzdaMx().get(i).getGmSc());
			model.setGmCl(eCkInVo.getGzdaMx().get(i).getGmCl());
			model.setGmSl(eCkInVo.getGzdaMx().get(i).getGmSl());
			model.setGmRq(new Date());
			model.setGmStatus("登记");
			model.setMan(sessionUser.getUserId());
			model.setCorp(sessionUser.getCorpId());
			addGzda.add(model);
		}

		// 业务逻辑判断end
		// 开始保存到数据库
		if (!addECkIns.isEmpty()) {
			addECkIns.forEach(item -> {
				item.setInRq(new Date());
				InDao.save(item);
				System.out.println("单号:" + item.getInNote());
				gzDao.setFlag(item.getInNote());
			});
		}

		// 业务逻辑判断end //开始保存到数据库
		if (!addGzda.isEmpty()) {
			addGzda.forEach(item -> {
				System.out.println(item.getGmCode() + "产品编码test");
				gzDao.save(item);
			});
		}

		// end
		return Result.resultOk(eCkInVo.geteCkIn());
	}

}
