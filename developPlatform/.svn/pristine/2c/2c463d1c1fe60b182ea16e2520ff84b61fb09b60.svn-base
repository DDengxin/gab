package com.tengzhi.business.system.datachart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import cn.hutool.core.util.ObjectUtil;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.business.resouces.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.extension.SqlJoint;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.business.system.datachart.dao.SysDatachartDao;
import com.tengzhi.business.system.datachart.model.SysDatachart;
import com.tengzhi.business.system.datachart.service.SysDatachartService;
@Service
@Transactional
public class SysDatachartServiceImpl  implements SysDatachartService{
	@Autowired
    private SysDatachartDao sysDatachartDao;
	
	
	/**
	 * 获取列表信息
	 */
	public Result findAll(BaseDto dto) {
		Map<String,String> map = dto.getParamsMap();
		String datachartId = map.get("datachartId");
		String datachartName = map.get("datachartName");
		String datachartModule = map.get("datachartModule");
		SessionUser user = SessionUser.SessionUser();
		String where = SqlJoint.where(c -> {
			c.contains("datachart_id", datachartId);
			c.contains("datachart_name", datachartName);
			c.contains("datachart_module", datachartModule);
			c.eq("corp_id", user.getCorpId());
		}, true);
		String sql = "select * from sys_datachart " + where;
		return sysDatachartDao.QueryMapPageList(dto, sql, true).getResult();
	}

	/**
	 * 新增
	 */
	public Result save(SysDatachart sysDatachart) throws Exception{
		sysDatachartDao.save(sysDatachart);
		return Result.resultOk(sysDatachart.getDatachartId());
	}
	
	/**
	 * 获取修改记录
	 */
	public Result findByDatachartId(String datachartid) {
		return Result.resultOk(sysDatachartDao.findById(datachartid).orElse(null));
	}
	
	/**
	 * 修改
	 */
	public void update(SysDatachart sysDatachart) {
		sysDatachartDao.update(sysDatachart);
	}
	
	/**
	 * 删除
	 */
	public void delete(String DatachartId) {
		sysDatachartDao.deleteById(DatachartId);
	}

	/**
	 *
	 * 获取图表下拉框
	 */
	public List<SelectVo> getDataChart() {
		List<SelectVo> list = new ArrayList<SelectVo>();
		SessionUser securityUser=SessionUser.SessionUser();
		String sqlString="select * from sys_datachart where datachart_flag='true' and corp_id='"+securityUser.getCorpId()+"'  ";
		List<SysDatachart> mgetchart = sysDatachartDao.QueryListModel(SysDatachart.class,sqlString);
		mgetchart.forEach(item -> {
			list.add(new SelectVo(item.getDatachartId(), item.getDatachartName()));
		});
		return list;
	}

	/**
	 *
	 * 获取图表下拉框
	 */
	public Result getDataChartlist() {
		Map<String,Object> rtsmp = new HashMap<String,Object>();
		List<Map> cgmap =null,xsmap=null;
		String sql="";
		//采购交易额
		sql="select * from v_cg_chart_jyl order by rq ";
		cgmap=sysDatachartDao.QueryListMap(sql);
		rtsmp.put("CGJYL",cgmap);

		//销售交易额
		sql="select * from v_xs_chart_jyl order by rq ";
		xsmap=sysDatachartDao.QueryListMap(sql);
		rtsmp.put("XSJYL",xsmap);

		//采购前五产品
		return Result.resultOk(rtsmp);
	}

	//封装销售前5产品数据组
	public List<String> getXslData(List<Map> rqlist,String code){
		List<String> listdata= new ArrayList<String>();
			for (int i = 0; i < rqlist.size(); i++) {
				Map slmap = sysDatachartDao.QueryToFirstMap("select cksl from v_cg_chart_xsl where out_code='"+code+"' and rq='"+rqlist.get(i).get("rq")+"'");
					if(!slmap.isEmpty()&&slmap.get("cksl")!=null){
						listdata.add(slmap.get("cksl").toString());
					}else{
						listdata.add("0");
					}
				}
		return listdata;

	}

	/**
	 * //销售前五产品
	 * @return
	 */
	public Result getChartXslDatalist() {
		Map<String,Object> rtsmp = new HashMap<String,Object>();
		Map<String,Object> xsldata = new HashMap<String,Object>();
		List<Map> rqlistMap =sysDatachartDao.QueryListMap("select rq from v_cg_chart_xsl  group by rq order by rq");

		List<Map> codelistMap =sysDatachartDao.QueryListMap("select out_code,cpcode_name from v_cg_chart_xsl group by out_code,cpcode_name ");

		List<Map> xsllist= new ArrayList<Map>();
		List<String> rqlist= new ArrayList<String>();
		List<String> codelist= new ArrayList<String>();
		for (int i = 0; i <rqlistMap.size() ; i++) {
			rqlist.add(rqlistMap.get(i).get("rq").toString());
		}
		for (int j = 0; j <codelistMap.size() ; j++) {
			codelist.add(codelistMap.get(j).get("cpcode_name").toString());
			Map<String,Object> rsmapdata=new HashMap<String,Object>();
			List<String> sldata =getXslData(rqlistMap,codelistMap.get(j).get("out_code").toString());
			if(!sldata.isEmpty()){
				rsmapdata.put("name",codelistMap.get(j).get("cpcode_name"));
				rsmapdata.put("type","bar");
				rsmapdata.put("stack","总量");
				rsmapdata.put("data",sldata);
				xsllist.add(rsmapdata);
			}


		}
		xsldata.put("xslrq",rqlist);
		xsldata.put("xslcode",codelist);
		xsldata.put("xsldata",xsllist);
		rtsmp.put("XSL",xsldata);
		return Result.resultOk(rtsmp);
	}


	/**
	 * //采购前五产品
	 * @return
	 */
	public Result getChartCglDatalist() {
		Map<String,Object> rtsmp = new HashMap<String,Object>();
		Map<String,Object> xsldata = new HashMap<String,Object>();
		List<Map> rqlistMap =sysDatachartDao.QueryListMap("select rq from v_xs_chart_cgl  group by rq order by rq");

		List<Map> codelistMap =sysDatachartDao.QueryListMap("select in_code,cpcode_name from v_xs_chart_cgl group by in_code,cpcode_name ");

		List<Map> xsllist= new ArrayList<Map>();
		List<String> rqlist= new ArrayList<String>();
		List<String> codelist= new ArrayList<String>();
		for (int i = 0; i <rqlistMap.size() ; i++) {
			rqlist.add(rqlistMap.get(i).get("rq").toString());
		}
		for (int j = 0; j <codelistMap.size() ; j++) {
			codelist.add(codelistMap.get(j).get("cpcode_name").toString());
			Map<String,Object> rsmapdata=new HashMap<String,Object>();
			List<String> sldata =getCglData(rqlistMap,codelistMap.get(j).get("in_code").toString());
			if(!sldata.isEmpty()){
				rsmapdata.put("name",codelistMap.get(j).get("cpcode_name"));
				rsmapdata.put("type","bar");
				//rsmapdata.put("stack","总量");//合并条形图
				rsmapdata.put("data",sldata);
				xsllist.add(rsmapdata);
			}


		}
		xsldata.put("cglrq",rqlist);
		xsldata.put("cglcode",codelist);
		xsldata.put("cgldata",xsllist);
		rtsmp.put("CGL",xsldata);
		return Result.resultOk(rtsmp);
	}

	//封装采购前五产品数据组
	public List<String> getCglData(List<Map> rqlist,String code){
		List<String> listdata= new ArrayList<String>();
		for (int i = 0; i < rqlist.size(); i++) {
			Map slmap = sysDatachartDao.QueryToFirstMap("select rksl from v_xs_chart_cgl where in_code='"+code+"' and rq='"+rqlist.get(i).get("rq")+"'");
			if(!slmap.isEmpty()&&slmap.get("rksl")!=null){
				listdata.add(slmap.get("rksl").toString());
			}else{
				listdata.add("0");
			}
		}
		return listdata;

	}
}
