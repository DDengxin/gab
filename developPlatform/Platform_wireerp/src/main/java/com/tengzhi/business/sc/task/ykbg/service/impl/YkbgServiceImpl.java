package com.tengzhi.business.sc.task.ykbg.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.tengzhi.business.cg.yw.purchaseReceipt.model.ECkIn;
import com.tengzhi.business.sc.task.blcl.service.BlclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.js.cpgy.scgx.dao.MScScgxDao;
import com.tengzhi.business.production.productionSite.siteTask.Vo.MScGclistVo;
import com.tengzhi.business.production.productionSite.siteTask.dao.MScGclistDao;
import com.tengzhi.business.production.productionSite.siteTask.model.MScGclist;
import com.tengzhi.business.sc.task.ykbg.service.YkbgService;
import com.tengzhi.business.sc.task.sctack.dao.ScTackDao;
import com.tengzhi.business.system.core.service.SysGenNoteService;

import cn.hutool.core.util.ObjectUtil;

@Service
@Transactional
public class YkbgServiceImpl implements YkbgService {

	@Autowired
	private ScTackDao scTackDao;
	
	@Autowired
	private MScScgxDao  mScScgxDao;

	@Autowired
	private MScGclistDao mScGclistDao;
	
	@Autowired
	private SysGenNoteService sysGenNoteService;

	@Autowired
	private BlclService blclService;
	
	@Override
	public BasePage<Map<String, Object>> getSrchGridList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		String where=" where 1=1 ";
		if(ObjectUtil.isNotEmpty(map.get("srchRq1"))) {
            where+=" and rq >='"+map.get("srchRq1")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchRq2"))) {
            where+=" and rq <='"+map.get("srchRq2")+"'";
        }
		if(ObjectUtil.isNotEmpty(map.get("srchBgc"))) {
			where+=" and bgc like '%"+map.get("srchBgc")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchCode"))) {
			where+=" and code like '%"+map.get("srchCode")+"%'";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchName"))) {
			where+=" and f_getparamname('GETBCPCODENAME',tname,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') like '%"+map.get("srchName")+"%' ";
		}
		if(ObjectUtil.isNotEmpty(map.get("srchSize"))) {
			where+=" and tsize like '%"+map.get("srchSize")+"%'";
		}
		String sql="select *,f_getparamname('GETBCPCODENAME',tname,'','技术',wl_type,'"+   SessionUser.getCurrentCorpId()   +"') zzpname,f_getname('GETGXNAME', gx, '', '') gxname,f_getparamname('GETTYPEBYRAMNAME',tcj,'生产车间','','','"+   SessionUser.getCurrentCorpId()   +"') cjname,f_getname('GETJTNAMES', tct, '', '') ctname"
				+ ",f_getname('GETUSERNAME',do_man,'',data_corp) manname,f_get_param_name('生产班次',tbc,'"+   SessionUser.getCurrentCorpId()   +"','cn',false) tbcname "
				+ " from  v_sc_gxbg "+where ;
		String countsql="select count(*) cn from ("+sql+") t";
		baseDto.setSortField("rq");
		baseDto.setSortOrder("desc");
		return scTackDao.QueryPageList(sql,countsql,baseDto);
	}

	@Override
	public Result getByScmo(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object sgc=map.get("sgc");
		List<Map>  gcList=scTackDao.QueryListMap("select bgc,sc_mo,gy,gx,tph,tts,tsl,bgc_vnote,gc_flag,xgx,tjs from m_sc_gclist where bgc='"+sgc+"'");
		if(gcList.size()==0) {
			return Result.error("未获取到上料数据！");
		}
		Map gcMap=gcList.get(0);
		if(!"HG".equals(gcMap.get("gc_flag"))){
			return Result.error("该工程不合格，不能操作");
		}
		String xgx = gcMap.get("xgx")!=null?gcMap.get("xgx").toString():"";
		String tjs = gcMap.get("tjs")!=null?gcMap.get("tjs").toString():"";
		String sql=" select gx_ord,a.sc_mo,gx_id,gx_name,gx_cj,gx_ct,gx_stype,tcode,tname,tsize,'"+xgx+"' xgx,'"+tjs+"' tjs, "
				+ " '"+gcMap.get("gy")+"' gy,'"+gcMap.get("tsl")+"' tsl,COALESCE('"+gcMap.get("tph")+"','') tph ,'"+gcMap.get("tts")+"' tts,'"+gcMap.get("bgc_vnote")+"' bgcvnote,b.cpname,b.cpsize  "
				+ "from m_sc_scrw_gx a,(select a.sc_mo,b.cpcode_id,f_getparamname('GETBCPCODENAME',b.cpcode_name,'','技术',b.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpname,cpcode_size cpsize  from m_sc_scrw a , e_js_cpcode b where a.code=b.cpcode_id and a.sc_mo=?1) b  where a.sc_mo=b.sc_mo and a.sc_mo=?2 and   a.gx_ord>( select gx_ord from m_sc_scrw_gx where sc_mo=?3 and gx_id=?4) order by a.gx_ord";
		Object[] val = {gcMap.get("sc_mo"),gcMap.get("sc_mo"),gcMap.get("sc_mo"),gcMap.get("gx")};
		List<Map> gclsit=scTackDao.QueryListMap(sql,val);
		if(gclsit.size()==0) {
			return Result.error("未获取到下料料数据！");
		}
		return Result.resultOk(gclsit);
	}
	
	@Override
	public  Result getByScgx(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object scMo=map.get("scMo");
		Object gxId=map.get("gxId");
		String sql=" select COALESCE(gx_sl,0) sl ,COALESCE(gx_cj,'') tcj,COALESCE(gx_ct,'') tct,COALESCE(tcode,'') tcode,COALESCE(tname,'') tname,COALESCE(tsize,'') tsize from  m_sc_scrw_gx where sc_mo=?1 and gx_id=?2 ";
		Object[] val = { scMo,gxId };
		List<Map> gclsit=mScScgxDao.QueryListMap(sql,val);
		return Result.resultOk(gclsit.get(0));
	}
	
	@Override
	public Result gxxl(MScGclistVo vo) throws Exception {
		SessionUser securityUser=SessionUser.SessionUser();
		//自动生成本工程 编码规则：工序+班别+YYMMDD+0001
		String sgc=vo.getmScGclist().getSgc();
		String prefix=sgc+vo.getmScGclist().getGxOrd();
		String  not="Z";
		List<Map> noteList=mScScgxDao.QueryListMap("select split_part(bgc,'"+prefix+"', 2) as note from m_sc_gclist  where  sc_mo='"+vo.getmScGclist().getScMo()+"' and gx='"+vo.getmScGclist().getGx()+"' and bgc like '"+vo.getmScGclist().getSgc()+"%'");
		for(int i=0;i<noteList.size();i++) {
			Map  noteMap=noteList.get(i);
			String note=noteMap.get("note").toString();
			if(note.length()==1) {
				not=note;
			}
		}
		String bgc=prefix+getNextUpEn(not);
		vo.getmScGclist().setBgc(bgc);
		vo.getmScGclist().setDoDate(new Date());
		vo.getmScGclist().setDataCorp(securityUser.getCorpId());
		vo.getmScGclist().setGcCl("SC");
		mScGclistDao.save(vo.getmScGclist());
		if("BL".equals(vo.getmScGclist().getGcFlag())){
			MScGclist mScgc= vo.getmScGclist();
			blclService.saveDataBl(mScgc);
		}
		return Result.resultOk("下料成功！");
	}
	
	//java 获取下一个字母（传大写返回大写，传小写返回小写）
	public static String getNextUpEn(String en){  
        char lastE = 'a';  
        char st = en.toCharArray()[0];
        if(Character.isUpperCase(st)){
            if("Z".equals(en)){
                return "A";
            }
            if(en==null || "".equals(en)){
                return "A";  
            }
            lastE = 'Z';  
        }else{
            if("z".equals(en)){
                return "a";
            }
            if(en==null || "".equals(en)){
                return "a";  
            }
            lastE = 'z';  
        }
        int lastEnglish = lastE;
        char[] c = en.toCharArray();  
        if(c.length>1){  
            return null;  
        }else{  
            int now = c[0];
            if(now >= lastEnglish) {
                return null;
            }
            char uppercase = (char)(now+1);  
            return String.valueOf(uppercase);  
        }  
    }

	@Override
	public Result getInList(String inValue) {
		List<Map> gykmap = scTackDao.QueryListMap("select b.ht_mo,b.cpfl,b.cpname,b.code cpcode,b.cpsize,a.tph e_pz_luhao,a.bgc,f_getname('GETGXNAME', a.gx, '', '') gx,a.tsize,a.tsl,to_char(a.bgc_wc,'mm-dd') bgc_wc,f_getname('GETUSERNAME',a.do_man,'',a.data_corp) do_man \n" +
				"from m_sc_gclist a left join \n" +
				"(\n" +
				"select b.ht_mo,b.sc_mo,b.code,f_getparamname('GETBCPCODENAME',c.cpcode_size,'','技术',c.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpsize,\n" +
				"f_getparamname('GETBCPCODENAME',c.cpcode_fl,'','技术',c.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpfl,\n" +
				"f_getparamname('GETBCPCODENAME',c.cpcode_name,'','技术',c.cpcode_type,'"+   SessionUser.getCurrentCorpId()   +"') cpname\n" +
				"from m_sc_scrw b,e_js_cpcode c where b.code=c.cpcode_id\n" +
				") b  on a.sc_mo=b.sc_mo\n" +
				"where position(a.bgc in '"+inValue+"')>0 or position('"+inValue+"' in a.bgc)>0 order by a.bgc_wc");
		return Result.resultOk(gykmap);
	}
}
