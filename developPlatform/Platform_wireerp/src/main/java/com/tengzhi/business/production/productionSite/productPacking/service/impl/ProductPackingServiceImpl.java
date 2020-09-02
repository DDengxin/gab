package com.tengzhi.business.production.productionSite.productPacking.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.base.security.common.model.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.production.productionSite.productPacking.dao.MScPrintPackTempDao;
import com.tengzhi.business.production.productionSite.productPacking.model.MScPrintPackTemp;
import com.tengzhi.business.production.productionSite.productPacking.service.ProductPackingService;
import com.tengzhi.business.production.productionSite.productPacking.vo.ProductPackingVo;
import com.tengzhi.business.production.productionSite.siteTask.dao.MScGclistDao;
import com.tengzhi.business.production.productionSite.siteTask.dao.MScGclistSlDao;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclistSl;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import cn.hutool.core.util.ObjectUtil;
@Service
@Transactional
public class ProductPackingServiceImpl implements ProductPackingService{

	@Autowired
	private MScGclistDao mScGclistDao;
	
	@Autowired
	private MScGclistSlDao mScGclistSlDao;
	
	@Autowired
	private MScPrintPackTempDao mScPrintPackTempDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;
	
	
	@Override
	public BasePage<MScGclist> getSrchTopList(BaseDto baseDto) throws Exception {
		String where="";
		Map<String, String> map = baseDto.getParamsMap();
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
			where+=" and a.ht_date >='"+map.get("srchRq1")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
			where+=" and a.ht_date <='"+map.get("srchRq2")+"' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchNo"))) {
			where+=" and a.ht_no like '%"+map.get("srchNo")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCustomer"))) {
			where+=" and a.ht_customer = '"+map.get("srchCustomer")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtType"))) {
			where+=" and a.ht_type = '"+map.get("srchHtType")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchHtItemType"))) {
			where+=" and a.ht_item_type = '"+map.get("srchHtItemType")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociated"))) {
			where+=" and b.ht_customer_associated = '"+map.get("htCustomerAssociated")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htCustomerAssociatedRemarks"))) {
			where+=" and b.ht_customer_associated_remarks = '"+map.get("htCustomerAssociatedRemarks")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htStype"))) {
			where+=" and a.ht_stype = '"+map.get("htStype")+"'";
		}
		if(ObjectUtil.isNotEmpty(map.get("htFlag"))) {
			if("审核中".equals(map.get("htFlag"))) {
				where += " and a.ht_flag not in  ('登记','确认','核准','核销')";
			}else {
				where+=" and a.ht_flag = '"+map.get("htFlag")+"'";
			}
			
		}
		
		String sqlString=" select a.ht_no,a.ht_date,f_get_param_name('订单类型',a.ht_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_type,f_get_param_name('订单类型',a.ht_item_type,'"+   SessionUser.getCurrentCorpId()   +"','cn')ht_item_type,f_getname('GETDWEXP',a.ht_customer,'',a.data_corp)ht_customer,f_get_param_name('业务员',c.customer_buyer,'"+   SessionUser.getCurrentCorpId()   +"','')data_man,sum(b.ht_sl)ht_sl,sum(b.ht_je)ht_je,a.ht_flag from e_xs_contract a ,e_xs_contract_detailed b, sys_customer c where a.ht_customer= c.customer_id and a.ht_no=b.ht_no "+ where 
				+ " group by a.ht_no,a.ht_date,a.ht_type,a.ht_item_type,a.ht_customer,c.customer_buyer,a.ht_flag,a.data_corp order by ht_date desc,ht_no desc";
		return mScGclistDao.QueryPageLists(baseDto,sqlString);
	}

	@Override
	public BasePage<MScGclist> getSrchBottomList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlString=" select a.ht_no,a.ht_mo,a.ht_id,a.ht_code,a.ht_sl,a.ht_price,a.ht_je,a.ht_jq,a.ht_sm,a.ht_flag,a.data_man,a.data_rq,a.data_corp,a.ht_requirements,(select string_agg( param_name,',') from sys_param c where strpos(a.ht_standard,c.param_id)>0 and  c.param_mod='技术'  )ht_standard_name,ht_standard ,ht_customer_associated_remarks,ht_customer_associated " + 
				" ,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,  b.cpcode_size ,b.cpcode_size_en,f_getparamname('GETBYCPCODEFL',b.cpcode_fl,'','技术',c.ht_stype,'"+   SessionUser.getCurrentCorpId()   +"')cpcode_fl,b.cpcode_bz,b.cpcode_xp,b.cpcode_check,b.cpcode01,b.cpcode02,b.cpcode03 from e_xs_contract_detailed a,e_js_cpcode b,e_xs_contract c "
				+ "  where a.ht_no=c.ht_no and a.data_corp=c.data_corp and a.ht_code=b.cpcode_id and a.ht_no= '"+map.get("htNo")+"'";
		return mScGclistDao.QueryPageLists(baseDto,sqlString);
	}

	@Override
	public MScGclist findByNote(String gch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result putIn(String gch) throws Exception {


		return null;
	}

	@Override
	public BasePage<MScGclist> getPrePackList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere = " ";
		if(ObjectUtil.isNotEmpty(map.get("gcQw"))) {
			sqlWhere+=" and gc_qw in ( '"+ map.get("gcQw").replace(",", "','")+"') ";
		}
		if(ObjectUtil.isNotEmpty(map.get("bgc"))) {
			sqlWhere+=" and bgc in ( '"+ map.get("bgc").replace(",", "','")+"') ";
		}
		
		String sql = "select sc_mo,tname,tsize,tsl ,gc_qw,gc_flag from  m_sc_gclist where gc_flag='N' "+sqlWhere+"  ";
		return mScGclistDao.QueryPageLists(baseDto,sql +"  order by tname");
	}

	
	@Override
	public BasePage<MScGclist> getPackList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String sqlWhere = " ";
		if(ObjectUtil.isNotEmpty(map.get("packNo"))) {
			sqlWhere+=" and pack_no = '"+map.get("packNo")+"' ";
		}
		
		String sql = "select sc_mo,tname,tsize,tsl ,gc_qw,gc_flag from  m_sc_gclist where gx='BZ' "+sqlWhere+"  ";
		return mScGclistDao.QueryPageLists(baseDto,sql +"  order by tname ");
	}

	@Override
	public Result getConsistent(String inMode,String scanDatas) throws IOException {
		
		String where = "";
		if("料架号".equals(inMode)) {
			where = " and  gc_qw in ('"+scanDatas.replace(",", "','")+"')";
		}else {
			where = " and  bgc in ('"+scanDatas.replace(",", "','")+"')";
		}
		
		List<MScGclist> gclist = mScGclistDao.QueryListModel(MScGclist.class, "select distinct a.code,a.tname,a.tsize,a.tsl,ht_mo itemmo from m_sc_gclist a, m_sc_scrw b  where a.sc_mo= b.sc_mo "+where);
		if(gclist.size()==1){
			return Result.resultOk(gclist.get(0));
		}else if(gclist.size()<1){
			return  Result.error("该扫描"+inMode+"有误！");
		}else {
			return  Result.error("该号中的材质规格与已入的数据不同！");
		}
	}

	@Override
	public Result addPacks(ProductPackingVo productPackingVo) throws IOException {
		
		List<MScGclist> addGcList = new ArrayList<MScGclist>();//添加数据
		List<MScGclistSl> addGcListSl = new ArrayList<MScGclistSl>();
		
		SessionUser securityUser=SessionUser.SessionUser();
		
		productPackingVo.getAddedList().forEach(model -> {
			//明细
			MScGclistSl modelSl = new MScGclistSl();
			
			
			model.setDoDate(new Date());
			model.setDoMan(securityUser.getUserId());
			try {
				String pack = sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
				model.setBgc(pack);
				modelSl.setBgc(pack);
			} catch (Exception e) {
				e.printStackTrace();
			}
			addGcList.add(model);
			
			
			modelSl.setSgc(model.getSgc());
			modelSl.setSgcSl(model.getTsl());
			modelSl.setBgcGx(model.getGx());
			modelSl.setDoMan(securityUser.getUserId());
			modelSl.setDoDate(new Date());
			modelSl.setWlType("CP");
			
			
			addGcListSl.add(modelSl);
			
		});
		
		//数据处理
		//更新上工程号状态
		//插入明细记录
		//插入新工程号
		if (!addGcList.isEmpty()){
			addGcList.forEach(model -> {
				
				mScGclistDao.save(model);
			//	mScGclistSlDao.insert(model.getSgc(),model.getBgc());
			});
		}
		return Result.resultOk(productPackingVo.getmScGclist());
	}

	@Override
	public Result deletePacks(ProductPackingVo productPackingVo) throws IOException {
		String note = productPackingVo.getmScGclist().getPackNo();
		
		List<MScGclist> deleteGcList = new ArrayList<MScGclist>();
		productPackingVo.getDeletedList().forEach(model -> {
			
			
			deleteGcList.add(model);
		});
		
		//数据处理
		//更新上工程号状态
		//删除明细表记录
		//删除本工程号
		return Result.resultOk(note);
	}

	@Override
	public Result deleteAllPacks(String note) throws IOException {
		//数据处理
		//更新上工程号状态
		//删除明细表记录
		//删除本工程号
		return Result.resultOk(note);
	}

	@Override
	public Result printPack(String printType,String packType, String inputValue) throws IOException {
		SessionUser securityUser=SessionUser.SessionUser();
		
		//删除上次记录
		mScPrintPackTempDao.deleteAllPacks();
		
		if("工程号".equals(printType)){
			
			if("大包装号".equals(printType)){
				List<MScPrintPackTemp> printPacks = mScPrintPackTempDao.QueryListModel(MScPrintPackTemp.class, "select a.code wl_code,tname wl_name,tsize wl_size,tsl wl_sl,ht_standard wl_standard ,bgc wl_pack,pack_no wl_bpack,a.rq wl_scrq,now() data_rq ,:userid data_man from m_sc_gclist a,m_sc_scrw b,e_xs_contract_detailed c where a.sc_mo=b.sc_mo and b.ht_mo=c.ht_mo and a.pack_no =:bpack ",securityUser.getUserId(),inputValue);
				
				printPacks.forEach(model -> {
					mScPrintPackTempDao.save(model);
				});
			}else {
				List<MScPrintPackTemp> printPacks = mScPrintPackTempDao.QueryListModel(MScPrintPackTemp.class, "select a.code wl_code,tname wl_name,tsize wl_size,tsl wl_sl,ht_standard wl_standard ,bgc wl_pack,pack_no wl_bpack,a.rq wl_scrq,now() data_rq ,'"+securityUser.getUserId()+"' data_man from m_sc_gclist a,m_sc_scrw b,e_xs_contract_detailed c where a.sc_mo=b.sc_mo and b.ht_mo=c.ht_mo and a.bgc in ('"+inputValue.replace(",", "','")+"') ");
				printPacks.forEach(model -> {
					mScPrintPackTempDao.save(model);
				});
			}
			
			
		}else {
			if("大包装号".equals(printType)){
				List<MScPrintPackTemp> printPacks = mScPrintPackTempDao.QueryListModel(MScPrintPackTemp.class, "select in_code wl_code,cpcode_name wl_name,cpcode_size wl_size,sum(in_sl) wl_sl,'' wl_standard ,in_bpack wl_pack,in_bpack wl_bpack,in_rq wl_scrq,now() data_rq ,a.data_man  from e_ck_in a,e_js_cpcode b  where a.in_code=b.cpcode_id and bpack =:bpack  group by in_code ,cpcode_name ,cpcode_size ,in_bpack,in_rq,a.data_man ",inputValue);
				printPacks.forEach(model -> {
					mScPrintPackTempDao.save(model);
				});
			}else {
				List<MScPrintPackTemp> printPacks = mScPrintPackTempDao.QueryListModel(MScPrintPackTemp.class, "select in_code wl_code,cpcode_name wl_name,cpcode_size wl_size,in_sl wl_sl,ht_standard wl_standard ,in_bpack wl_pack,in_bpack wl_bpack,in_rq wl_scrq,now() data_rq ,a.data_man  from e_ck_in a,e_js_cpcode b,e_xs_contract_detailed c  where a.in_contract = c.ht_no and a.in_code = c.ht_code and a.in_code=b.cpcode_id and in_pack in ('"+inputValue.replace(",", "','")+"') ");
				printPacks.forEach(model -> {
					mScPrintPackTempDao.save(model);
				});
			}
		}
		
		return Result.resultOk(inputValue);
	}

	
}
