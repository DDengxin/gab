package com.tengzhi.app.rk.service.impl;


import com.tengzhi.app.rk.dao.RKFlagDao;
import com.tengzhi.app.rk.model.ECkInLsFlag;
import com.tengzhi.app.rk.model.EckInLs;
import com.tengzhi.app.rk.service.RKService;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.cg.yw.purchaseReceipt.dao.ECkInDao;
import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.cg.yw.purchaseReceiptNotice.dao.ECkReceivingNoticeDao;
import com.tengzhi.business.cg.yw.purchaseSettle.dao.ECwYsyfDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;
import com.tengzhi.tools.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class RKServiceImpl implements RKService {

    @Autowired
    ECkInDao eCkInDao;
    
    @Autowired
    RKFlagDao rKFlagDao;
    
    @Autowired
    ECwYsyfDao eCwYsyfDao;
    
    @Autowired
    ECkReceivingNoticeDao eCkReceivingNoticeDao;

    @Autowired
    private SysGenNoteService sysGenNoteService;

    /**
     * 查询入库明细中是否有这个包装号
     */
    @Override
    public Map<String, Object> selectRKDetailsPackNumber(String act,String lib,String kw,String pack, String bpack) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sqlWhere = "";
        String sql="";
        if (!StringUtils.isBlank(pack)) {
            sqlWhere += " s.in_pack ='" + pack + "' and s.in_act='"+act+"' and s.in_lib='"+lib+"'  and s.in_flag='结算'";
            sql="SELECT COUNT(s.in_pack) AS ck_count "
                    +"FROM e_ck_in s "+
                    "  WHERE "+sqlWhere;
        }
        if (!StringUtils.isBlank(bpack)) {
            sqlWhere += " s.in_bpack ='" + bpack + "' and s.in_act='"+act+"' and s.in_lib='"+lib+"'  and s.in_flag='结算'";
            sql="SELECT COUNT(s.in_bpack) AS ck_count "
                    +"FROM e_ck_in s "+
                    "  WHERE "+sqlWhere;
        }

        String sql1Where = "";
        String sql1="";
        if (!StringUtils.isBlank(pack)) {
            sql1Where += " os.in_pack ='" + pack + "' and os.in_act='"+act+"' and os.in_lib='"+lib+"' ";//and os.in_kw='"+kw+"'
            sql1="SELECT COUNT(os.in_pack) AS el_count "
                    +"FROM e_ck_in_ls os "+
                    "  WHERE "+sql1Where;
        }
        if (!StringUtils.isBlank(bpack)) {
            sql1Where += " os.in_bpack ='" + bpack + "' and os.in_act='"+act+"' and os.in_lib='"+lib+"' ";//and os.in_kw='"+kw+"'
            sql1="SELECT COUNT(os.in_bpack) AS el_count "
                    +"FROM e_ck_in_ls os "+
                    "  WHERE "+sql1Where;
        }


        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("datals", eCkInDao.QueryListMap(sql1));
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 从工程表里里查询该包装号的详细信息
     */
    @Override
    public Map<String, Object> selectRKPackInfo(String act,String lib,String kw,String pack){
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql="select in_rq,in_note,in_customer,in_act,in_code,in_js,in_sl,in_zl,in_pack,in_bpack,in_price,in_contract,in_kfcode,in_lib,"
        		+ " in_type,in_flag,in_bz,in_tax,s.data_corp,in_hs,'"+kw+"' in_kw," +
                "a.cpcode_id,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,a.cpcode_name_en,a.cpcode_size,a.cpcode_size_en " +
                "FROM e_ck_in s LEFT JOIN e_js_cpcode a ON s.in_code=a.cpcode_id WHERE s.in_pack='"+pack+ "' and s.in_act='"+act+"' and s.in_lib='"+lib+"' ";
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 新增入库信息到临时表
     */
    @Override
    public void insert(String inString) {
        if (inString != null) {
        	
        	SessionUser securityUser=SessionUser.SessionUser();
        	
            EckInLs eckIn = new EckInLs();
            JSONObject jsonObject = new JSONObject(inString);
            //登记日期
            if (jsonObject.opt("in_rq")!=null){
                SimpleDateFormat simpleDateFormat;
                if ((jsonObject.opt("in_rq")+"").contains("/")){
                    simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                }else {
                    simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                }
                try {
                    eckIn.setInRq(simpleDateFormat.parse(jsonObject.opt("in_rq")+""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //入库状态
            eckIn.setInFlag("登记");
            //产品编码
            eckIn.setInCode(jsonObject.optString("in_code"));
            //产品件数
            if(jsonObject.opt("in_js")!=null){
                eckIn.setInJs(BigDecimal.valueOf(jsonObject.optDouble("in_js")));
            }
            //核算(件数/数量)
            eckIn.setInHs(jsonObject.optString("in_hs"));
            //合同编号
            eckIn.setInContract(jsonObject.optString("in_contract"));
            //所属公司
            eckIn.setDataCorp(jsonObject.optString("data_corp"));
            //大批包装
            eckIn.setInBpack(jsonObject.optString("in_pack"));
            //批次包装
            eckIn.setInPack(jsonObject.optString("in_bpack"));
            //出库库房
            eckIn.setInLib(jsonObject.optString("in_lib"));
            //操作人员
            
            //eckIn.setDataMan(jsonObject.optString("data_man"));
            eckIn.setDataMan(securityUser.getUserId());
            //入库业务
            eckIn.setInAct(jsonObject.optString("in_act"));
            //操作日期
            if (jsonObject.opt("data_date")!=null){
                SimpleDateFormat simpleDateFormat;
                if ((jsonObject.opt("data_date")+"").contains("/")){
                    simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                }else {
                    simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                }
                try {
                    eckIn.setDataDate(simpleDateFormat.parse(jsonObject.opt("data_date")+""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //发票税率
            if(jsonObject.opt("in_tax")!=null){
                eckIn.setInTax(BigDecimal.valueOf(jsonObject.optDouble("in_tax")));
            }
            //入库单位
            eckIn.setInCustomer(String.valueOf(jsonObject.opt("in_customer")));
            //入库大类
            eckIn.setInType(String.valueOf(jsonObject.opt("in_type")));
            //币种
            eckIn.setInBz(String.valueOf(jsonObject.opt("in_bz")));
            //产品数量
            if(jsonObject.opt("in_sl")!=null){
                eckIn.setInSl(BigDecimal.valueOf(jsonObject.optDouble("in_sl")));
            }
            //产品价格
            if(jsonObject.opt("in_price")!=null){
                eckIn.setInPrice(BigDecimal.valueOf(jsonObject.optDouble("in_price")));
            }
            //入库单号
            eckIn.setInNote(jsonObject.opt("in_note")+"");
            //领用库房
            eckIn.setInCklib(jsonObject.opt("in_cklib")+"");
            //发票汇率
            if(jsonObject.opt("in_rate")!=null){
                eckIn.setInRate(BigDecimal.valueOf(jsonObject.optDouble("in_rate")));
            }
            //领用人
            eckIn.setInLyr(jsonObject.opt("in_lyr")+"");
            //品牌
            eckIn.setBrand(jsonObject.opt("brand")+"");
            //原料炉号
            eckIn.setInLuono(jsonObject.opt("in_luono")+"");
            //记账月结
            eckIn.setInMonth(jsonObject.opt("in_month")+"");
            //出库检验
            eckIn.setInCheckflag(jsonObject.opt("in_checkflag")+"");
            //采购编码
            eckIn.setInKfcode(jsonObject.opt("in_kfcode")+"");
            //客户合同
            eckIn.setInKfcontract(jsonObject.opt("in_kfcontract")+"");
            //产品重量
            if(jsonObject.opt("in_zl")!=null){
                eckIn.setInZl(BigDecimal.valueOf(jsonObject.optDouble("in_zl")));
            }
            //库位
            eckIn.setInKw(jsonObject.opt("in_kw")+"");
            //库审人
            eckIn.setInMan(jsonObject.opt("in_man")+"");
            //库审时间
            eckIn.setInDate(new Date(System.currentTimeMillis()));
            eckIn.setDataAct(jsonObject.opt("data_act")+"");
            eCkInDao.store(eckIn);
        }
    }
    /**
     * 根据单号查询该包装号的所有信息
     */
    @Override
    public Map<String, Object> selectRKNoteInfo(String note) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sql="SELECT s.*, "+
                "a.cpcode_id,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,a.cpcode_name_en,a.cpcode_size,a.cpcode_size_en " +
                "FROM e_ck_receiving_notice s LEFT JOIN e_js_cpcode a ON s.sh_code=a.cpcode_id" +
                "  WHERE s.sh_note='"+note+"'";
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 第二种新增入库信息到临时表
     */
    @Override
    public void insert2(String inString,String pack,String zl,String luhao,String js) {
        if (inString != null) {
            
        	SessionUser securityUser=SessionUser.SessionUser();
        	
        	EckInLs eckIn = new EckInLs();
            JSONObject jsonObject = new JSONObject(inString);
            //登记日期
            if (jsonObject.opt("sh_rq")!=null){
                SimpleDateFormat simpleDateFormat;
                if ((jsonObject.opt("sh_rq")+"").contains("/")){
                    simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                }else {
                    simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                }
                try {
                    eckIn.setInRq(simpleDateFormat.parse(jsonObject.opt("sh_rq")+""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //入库状态
            eckIn.setInFlag("登记");
            //产品编码
            eckIn.setInCode(jsonObject.optString("sh_code"));
            //产品件数
            eckIn.setInJs(new BigDecimal(js));
            //核算(件数/数量)
            eckIn.setInHs(jsonObject.optString("sh_hs"));
            //合同编号
            eckIn.setInContract(jsonObject.optString("sh_contract"));
            //所属公司
            eckIn.setDataCorp(jsonObject.optString("data_corp"));
            //大批包装
            eckIn.setInBpack(pack);
            //批次包装
            eckIn.setInPack(pack);
            //出库库房
            eckIn.setInLib(jsonObject.optString("sh_lib"));
            //操作人员
            //eckIn.setDataMan(jsonObject.optString("data_man"));
            eckIn.setDataMan(securityUser.getUserId());
            //入库业务
            eckIn.setInAct(jsonObject.optString("sh_act"));
            //操作日期
            if (jsonObject.opt("data_rq")!=null){
                SimpleDateFormat simpleDateFormat;
                if ((jsonObject.opt("data_rq")+"").contains("/")){
                    simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                }else {
                    simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                }
                try {
                    eckIn.setDataDate(simpleDateFormat.parse(jsonObject.opt("data_rq")+""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //发票税率
            if(jsonObject.opt("sh_tax")!=null){
                //发票税率
                eckIn.setInTax(BigDecimal.valueOf(jsonObject.optDouble("sh_tax")));
            }
            //入库单位
            eckIn.setInCustomer(jsonObject.opt("sh_customer")+"");
            //入库大类
            eckIn.setInType(jsonObject.opt("sh_type")+"");
            //币种
            eckIn.setInBz(jsonObject.opt("sh_bz")+"");
            //产品数量
            if(jsonObject.opt("sh_sl")!=null){
                //产品数量
                //eckIn.setInSl(BigDecimal.valueOf(jsonObject.optDouble("sh_sl")));
                eckIn.setInSl(BigDecimal.valueOf(Double.valueOf(zl)));
            }
            //产品价格
            if(jsonObject.opt("sh_price")!=null){
                //产品价格
                eckIn.setInPrice(BigDecimal.valueOf(jsonObject.optDouble("sh_price")));
            }
            //入库单号
            eckIn.setInNote(jsonObject.opt("sh_note")+"");
            //领用库房
            //发票汇率
            //领用人
            //品牌
            //原料炉号
            //记账月结
            //出库检验
            //采购编码
            eckIn.setInCode(jsonObject.optString("sh_code"));
            //客户合同
            //产品重量
            //eckIn.setInZl(BigDecimal.valueOf(Double.valueOf(zl)));
            //炉号
            eckIn.setInLuono(luhao);
            //库审人
            //库审时间
            eckIn.setInDate(new Date(System.currentTimeMillis()));
            eckIn.setDataAct(jsonObject.optString("data_act"));

            eCkInDao.store(eckIn);
        }
    }

    /**
     * 批量保存到in表
     */
    @Override
    public void batchInsert(String note,String dataAct) {
        String sql="select * " +
                "from e_ck_in_ls s " +
                "  WHERE   s.in_note ='"+note+"' and data_act='"+dataAct+"' and in_flag='库审' ";
        List<ECkIn> eCkOuts = eCkInDao.QueryListModel(ECkIn.class, sql);
        eCkOuts.forEach(item ->{
            item.setInFlag("确认");
            eCkInDao.store(item);
        });
        eCkReceivingNoticeDao.setFlag(note,"扫描");
    }

    /**
     * 入库扫描批量保存到in表
     */
    @Override
    public void batchInsertsm(String lib,String act,String kw) {
    	SessionUser securityUser= SessionUser.SessionUser();
        String sql="select * " +
                "from e_ck_in_ls s " +
                "  WHERE s.in_lib='"+lib+"' and s.in_act='"+act+"'  and  in_flag ='登记' and data_act='P1002' ";//and s.in_kw='"+kw+"'
        List<ECkInLsFlag> eCkInLs = rKFlagDao.QueryListModel(ECkInLsFlag.class, sql);
        eCkInLs.forEach(item ->{
        	eCkInDao.rkpack(item.getInKw(),securityUser.getUserId(),item.getInPack());
        	rKFlagDao.rkpackls(item.getInPack(),securityUser.getUserId(),item.getDataAct());
        	eCwYsyfDao.setFlag(item.getInNote(), "库审");
            //eCkInDao.store(item);
        });
    }
    /**
     * 库审判断数量是否大于最小数量范围
     */
    @Override
    public Map<String, Object> selectPackQuantity(String note,String dataAct ) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        /*String sql="select sum(s.in_sl) AS sl " +
                "from e_ck_in_ls s " +
                "  WHERE s.in_lib='"+inLib+"' and s.in_act='"+inAct+"'";*/
        String sql =" select f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',sh_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcodename from ("
        		+ " select cp.cpcode_name,a.sh_code,a.sh_sl+sh_ceiling slmax,a.sh_sl+sh_lower slmin,coalesce(sum(in_sl),0) sl,a.sh_type from e_js_cpcode cp, e_ck_receiving_notice a "
        		+ " left join e_ck_in_ls b on a.sh_note = b.in_note and a.sh_contract = b.in_contract and a.sh_code = b.in_code and b.data_act='"+dataAct+"' "
        		+ "  where a.sh_note='"+note+"'  and cp.cpcode_id = a.sh_code"
        		+ " group by a.sh_note,a.sh_contract,a.sh_code,a.sh_sl,a.sh_ceiling ,a.sh_lower,a.sh_type,cp.cpcode_name "
        		+ " )sh  where sh.slmax<sh.sl or sh.slmin>sh.sl ";
        List<Map> cpcodenameList = eCkInDao.QueryListMap(sql);
        if(cpcodenameList.size()>0) {
        	rmap.put("status",false);
        	rmap.put("message", cpcodenameList.get(0).get("cpcodename")+"收货数量不符合要求");
        }else {
        	rmap.put("status",true);
        }
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 入货单号查询
     */
    @Override
    public  Map<String, Object> selectIncomingSingleNumber(String rqOne,String rqTwo,String shType,String shAct,String shLib){
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sqlWhere = "";
        if (!StringUtils.isBlank(rqOne)) {
            sqlWhere += "  and sh_rq >='" + rqOne + "'";
        }
        if (!StringUtils.isBlank(rqTwo)) {
            sqlWhere += " and sh_rq <='" + rqTwo + "'";
        }
        if (!StringUtils.isBlank(shType)) {
            sqlWhere += " and sh_type ='" + shType + "'";
        }
        if (!StringUtils.isBlank(shAct)) {
            sqlWhere += " and sh_act ='" + shAct + "'";
        }
        if (!StringUtils.isBlank(shLib)) {
            sqlWhere += " and sh_lib ='" + shLib + "'";
        }
        String sql="select DISTINCT sh_note " +
                "from e_ck_receiving_notice  where sh_flag in ('登记','确认') "
                +sqlWhere;
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 入货查询
     */
    @Override
    public Map<String, Object> selectIncoming(String shNote) {
        Map<String, Object> rmap = new HashMap<String, Object>();
        String sqlWhere = "WHERE";
        if (!StringUtils.isBlank(shNote)) {
            sqlWhere += " sh_note ='" + shNote + "'";
        }
        String sql="select s.*, " +
                "a.cpcode_id,f_getparamname('GETBCPCODENAME',cpcode_name,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_name,a.cpcode_name_en,a.cpcode_size,a.cpcode_size_en,cpcode_xp,f_getparamname('GETBYCPCODEFL',cpcode_fl,'','技术',cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpcode_fl " +
                "from e_ck_receiving_notice s LEFT JOIN e_js_cpcode a ON s.sh_code=a.cpcode_id "
                +sqlWhere;
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
    }

    /**
     * 自动生成包装号
     */
    @Override
    public String getPackNumber() {
        try {
            return sysGenNoteService.getyyyyMMddNote4(ECkIn.class, "P");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public Map<String, Object> getIncoming(String menu, String inAct, String inLib,String inKw, String packType) {
		Map<String, Object> rmap = new HashMap<String, Object>();
		String sql ="";
		if("大包装".equals(packType)) {
			
		}else {
			sql="select a.*,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"')  cpcode_name,b.cpcode_size  from e_ck_in_ls a,e_js_cpcode b " +
	                "  WHERE a.in_code=b.cpcode_id and a.in_lib='"+inLib+"' and a.in_act='"+inAct+"'  and data_act='"+menu+"' and in_flag='登记' ";//and a.in_kw='"+inKw+"'
		}
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
	}


    /**
     * 根据库房查询区位
     * @return
     */
    @Override
    public Map<String, Object> selectLocation(String paramType,String paramValue) {
        Map<String, Object> rmap = new HashMap<String, Object>();

        String sql="with recursive tmp0 as (" +
                "SELECT param_id,param_name,parent_id,param_type,0 AS level " +
                "FROM sys_param " +
                "where param_xtype ='仓库库位' and param_value1='"+paramValue+"'" +
                "union " +
                "SELECT t1.param_id,t1.param_name,t1.parent_id,t1.param_type,(t0.level + 1) AS level "  +
                "FROM sys_param t1,tmp0 t0 " +
                "where t0.param_id=t1.parent_id " + //t1.param_type ='"+paramType+"' and 
                ")" +
                "SELECT param_id,param_name,parent_id,param_type,level " +
                "FROM tmp0 where level=2 order by param_id ";
        rmap.put("data", eCkInDao.QueryListMap(sql));
        rmap.put("code", 200);
        return rmap;
    }

    @Override
    public  Map<String, Object> getReceivedQuantity(String note,String item,String code) {
    	Map<String, Object> rmap = new HashMap<String, Object>();
    	rmap.put("data", eCkInDao.getSingleResult(" select  case when count(in_sl) =0 then '0' else round(sum(in_sl),1) end  sysl  from e_ck_receiving_notice a  left join e_ck_in_ls b "
					+ "on a.sh_note=b.in_note  and a.sh_contract = b.in_contract and a.sh_code=b.in_code where a.sh_note='"+note+"' and a.sh_contract='"+item+"' and a.sh_code = '"+code+"' group by sh_code ,sh_sl "));
    	rmap.put("code", 200);
    	return rmap;
    }



}
