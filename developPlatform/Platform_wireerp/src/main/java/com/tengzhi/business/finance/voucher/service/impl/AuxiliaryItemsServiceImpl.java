package com.tengzhi.business.finance.voucher.service.impl;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.Specifications;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.page.impl.BasePageImpl;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.finance.voucher.dao.AuxiliaryItemsDao;
import com.tengzhi.business.finance.voucher.model.EFVoucherAuxiliaryitems;
import com.tengzhi.business.finance.voucher.service.AuxiliaryItemsService;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AuxiliaryItemsServiceImpl implements AuxiliaryItemsService {
	@Autowired
	private AuxiliaryItemsDao aidao;

	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Override
	public BasePage<EFVoucherAuxiliaryitems> findAll(BaseDto baseDto,String fusebstable){
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();

		return aidao.QueryPageList(baseDto, Specifications.where((c) -> {
			c.eq("fitemclassid", map.get("srchfitemclassid"));
			c.eq("fusebstable", fusebstable);
			c.eq("dataCorp",securityUser.getCorpId());
		}));
	}

	@Override
	public BasePage<EFVoucherAuxiliaryitems> getAssistingGridlist(BaseDto baseDto) {
		Map<String, String> map = baseDto.getParamsMap();
		SessionUser securityUser=SessionUser.SessionUser();
		String fitemclassid=map.get("fitemclassid");
		String fname=map.get("fname");
		String sql = "select * from e_f_voucher_auxiliaryitems  where data_corp='" +securityUser.getCorpId()+ "'  and fitemclassid='" + map.get("fitemclassid").toUpperCase() + "' ";
		EFVoucherAuxiliaryitems efVoucherAuxiliaryitems= aidao.QueryToFirstBean(sql);
		if(null!=efVoucherAuxiliaryitems){
			if(efVoucherAuxiliaryitems.getFusebstable().equals("N")){
				return aidao.QueryPageList(baseDto, Specifications.where((c) -> {
					c.eq("fitemclassid", fitemclassid);
					c.contains("fname", fname);
					c.eq("fusebstable", "N");
					c.eq("dataCorp",securityUser.getCorpId());
				}));
			}else{
				sql="select "+efVoucherAuxiliaryitems.getFnumberfield()+" fnumber,"+efVoucherAuxiliaryitems.getFnamefield()+" fname from "+efVoucherAuxiliaryitems.getFtable()
						+SqlJoint.whereSuffixWhere((c)->{
					c.contains(efVoucherAuxiliaryitems.getFnamefield(), fname);
					c.eq(efVoucherAuxiliaryitems.getFwherefield(), efVoucherAuxiliaryitems.getFwherevalue());

				});
				return (BasePage<EFVoucherAuxiliaryitems> )aidao.QueryPageList(EFVoucherAuxiliaryitems.class,baseDto,sql);



			}
		}else{
			List<EFVoucherAuxiliaryitems>  ea= new ArrayList<EFVoucherAuxiliaryitems> ();
			return new BasePageImpl(ea,  0);
		}


	}

	@Override
	public EFVoucherAuxiliaryitems findById(String Id) {
		return aidao.findById(Id).orElse(null);
	}

	@Override
	public EFVoucherAuxiliaryitems save(EFVoucherAuxiliaryitems eFVoucherAuxiliaryitems)throws Exception   {
		SessionUser securityUser=SessionUser.SessionUser();
		eFVoucherAuxiliaryitems.setDataCorp(securityUser.getCorpId());
		boolean x=eFVoucherAuxiliaryitems.getFusebstable().equals("Y")?judgeUniqueset(eFVoucherAuxiliaryitems):judgeUniqueother(eFVoucherAuxiliaryitems);
		if(x){
			eFVoucherAuxiliaryitems.setFitemid(sysGenNoteService.getNote("auxiliaryitems","1","",4));
			return aidao.save(eFVoucherAuxiliaryitems);
		} else {
			throw new Exception("项目名称已存在");
		}

	}

	@Override
	public void update(EFVoucherAuxiliaryitems EFVoucherAuxiliaryitems) {
		aidao.dynamicSave(EFVoucherAuxiliaryitems, aidao.findById(EFVoucherAuxiliaryitems.getFitemid()).orElse(null));
	}

	@Override
	public boolean judgeUniqueset(EFVoucherAuxiliaryitems efva) {
		return aidao.findAll(Specifications.where((c) -> {
			SessionUser securityUser=SessionUser.SessionUser();
			c.eq("dataCorp",securityUser.getCorpId());
			c.eq("fitemclassid", efva.getFitemclassid());
		})).size() <= 0;
	}

	@Override
	public boolean judgeUniqueother(EFVoucherAuxiliaryitems efva) {
		return aidao.findAll(Specifications.where((c) -> {
			SessionUser securityUser=SessionUser.SessionUser();
			c.eq("dataCorp",securityUser.getCorpId());
			c.eq("fitemclassid", efva.getFitemclassid());
			c.eq("fnumber", efva.getFnumber());
			c.eq("fname", efva.getFname());
		})).size() <= 0;
	}

	@Override
	public Result deleteById(String Id) {

		aidao.deleteById(Id);
			return Result.resultOkMsg("删除成功");

	}

	@Override
	public List<EFVoucherAuxiliaryitems> findAll() {
		return aidao.findAll();
	}
}
