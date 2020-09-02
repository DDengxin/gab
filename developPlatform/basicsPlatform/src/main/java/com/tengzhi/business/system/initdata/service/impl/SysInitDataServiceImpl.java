package com.tengzhi.business.system.initdata.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.tengzhi.business.system.initdata.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tengzhi.base.jpa.dto.BaseDto;
import com.tengzhi.base.jpa.page.BasePage;
import com.tengzhi.base.jpa.result.Result;
import com.tengzhi.base.security.common.model.SessionUser;
import com.tengzhi.base.tools.beans.BeanUtils;
import com.tengzhi.base.tools.excel.ExcelUtil;
import com.tengzhi.business.system.initdata.model.InitECgContractDetailed;
import com.tengzhi.business.system.initdata.model.InitECkIn;
import com.tengzhi.business.system.initdata.model.InitECkLib;
import com.tengzhi.business.system.initdata.model.InitECwYsyf;
import com.tengzhi.business.system.initdata.model.InitEHrWorker;
import com.tengzhi.business.system.initdata.model.InitEJsCpcode;
import com.tengzhi.business.system.initdata.model.InitEXsContractDetailed;
import com.tengzhi.business.system.initdata.model.InitMSbJt;
import com.tengzhi.business.system.initdata.model.InitSysCustomer;
import com.tengzhi.business.system.initdata.model.InitSysDept;
import com.tengzhi.business.system.initdata.model.SysDbDo;
import com.tengzhi.business.system.initdata.service.SysInitDataService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

@Service
@Transactional
public class SysInitDataServiceImpl implements SysInitDataService {

    @Autowired
    private InitaECgContractDetailedDao initaECgContractDetailedDao;
    @Autowired
    private InitECkInDao initECkInDao;
    @Autowired
    private InitECkLibDao initECkLibDao;
    @Autowired
    private  InitECwYsyfDao initECwYsyfDao;
    @Autowired
    private InitEHrWorkerDao initEHrWorkerDao;
    @Autowired
    private InitEJsCpcodeDao initEJsCpcodeDao;
    @Autowired
    private InitEXsContractDetailedDao initEXsContractDetailedDao;
    @Autowired
    private InitMSbJtDao initMSbJtDao;
    @Autowired
    private InitSysCustomerDao initSysCustomerDao;
    @Autowired
    private  InitSysDeptDao initSysDeptDao;
	@Autowired
	private SysInitDataDao sysInitDataDao;
	@Autowired
	private SysInitDataDaoBasic sysInitDataDaoBasic;



    public  boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


	@Override
	public Result upload(MultipartFile files,String dbTable,String dbName, String dataType) throws Exception {
		
		ExcelUtil execlutil = ExcelUtil.getInstance();
		Result rs= new  Result();
	       if (StringUtils.isNotBlank(files.getOriginalFilename())) {
	           
	        } else {
	            return Result.error("未选择文件");	        
	        }
	     

				String columnsql = "select a.attname ,replace(d.description,'imp_','') description from pg_class c,pg_attribute a,pg_type t,pg_description d\r\n"
						+ "where c.relname='" + dbTable
						+ "' and a.attnum>0 and a.attrelid=c.oid and a.atttypid=t.oid  and d.description like 'imp_%' and d.objoid=a.attrelid and d.objsubid=a.attnum;";
				List<Map> lpm = sysInitDataDao.QueryListMap(columnsql, new  Object[]{});
				Map<String, String> nameprops = new HashMap<String, String>();
				for (Map m : lpm) {
					String attname = m.get("attname").toString();
					String description = m.get("description").toString();
					nameprops.put(description, StrUtil.toCamelCase(attname));
				}

		Result map = execlutil.readExcelByTable(files,nameprops, StrUtil.upperFirst(StrUtil.toCamelCase(dbTable)));
		//数据处理
		if (!Result.judge(map)) {
			return Result.error(map.get("msg").toString());
		}
		List<?> dataList = (List<?>) map.get(Result.KEY_DATA);
		String	dataCorp =  SessionUser.SessionUser().getCorpId() ;
	    
			switch(StrUtil.upperFirst(StrUtil.toCamelCase(dbTable))) {
			case "InitECgContractDetailed":{

				for (int i = 0; i < dataList.size(); i++) {
					InitECgContractDetailed mbean = (InitECgContractDetailed) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initaECgContractDetailedDao.save(mbean);
				}
				break;	
			}
			
			case "InitSysDept":{

				for (int i = 0; i < dataList.size(); i++) {
					InitSysDept mbean = (InitSysDept) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initSysDeptDao.save(mbean);
				}
				break;	
			}
			
			case "InitECkIn":{

				for (int i = 0; i < dataList.size(); i++) {
					InitECkIn mbean = (InitECkIn) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initECkInDao.save(mbean);
				}
				break;	
			}
			case "InitECkLib":{

				for (int i = 0; i < dataList.size(); i++) {
					InitECkLib mbean = (InitECkLib) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initECkLibDao.save(mbean);
				}
 break;			
}
			case "InitECwYsyf":{

				for (int i = 0; i < dataList.size(); i++) {
					InitECwYsyf mbean = (InitECwYsyf) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initECwYsyfDao.save(mbean);
				}
				 break;	
			}
			case "InitEHrWorker":{

				for (int i = 0; i < dataList.size(); i++) {
					InitEHrWorker mbean = (InitEHrWorker) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initEHrWorkerDao.save(mbean);
				}
				 break;	
				}
			case "InitEJsCpcode":{

				for (int i = 0; i < dataList.size(); i++) {
					InitEJsCpcode mbean = (InitEJsCpcode) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initEJsCpcodeDao.save(mbean);
				}
			 break;	
			}
			case "InitEXsContractDetailed":{

				for (int i = 0; i < dataList.size(); i++) {
					InitEXsContractDetailed mbean = (InitEXsContractDetailed) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initEXsContractDetailedDao.save(mbean);
				}
				 break;	
			}
			case "InitMSbJt":{

				for (int i = 0; i < dataList.size(); i++) {
					InitMSbJt mbean = (InitMSbJt) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
					initMSbJtDao.save(mbean);
				}
				 break;	
			}
			case "InitSysCustomer":{

				for (int i = 0; i < dataList.size(); i++) {
					InitSysCustomer mbean = (InitSysCustomer) dataList.get(i);
					mbean.setDataCorp(dataCorp);
					mbean.setDataType(dataType);
			        initSysCustomerDao.save(mbean);
				}
			}
			
			 
			}
			return Result.resultOk();
	}
	@Override
    public BasePage<Map<String, Object>> getSrchTopList(BaseDto baseDto) throws Exception {
		String	dataCorp =  SessionUser.SessionUser().getCorpId() ;
		String sql = "select db_id, db_name, db_mk, db_mb, db_table, db_sm, data_corp, data_type,  db_imporder,f_querybytable(db_table,data_type,'"+dataCorp+"') db_impflag from  sys_db_do   where db_table is not null  ";
	return sysInitDataDao.QueryMapPageList(baseDto,sql +"  order by db_imporder ",true);
	} 

	@Override
	public  Map<String, Object> getSrchBottomNList(BaseDto baseDto) throws IOException {
		Map<String, String> map = baseDto.getParamsMap();
		Object dbTable = map.get("dbTable");
		Object dataType = map.get("dataType");
//		Object dataCorp = map.get("dataCorp");
		//默认当前公司账套
		Object	dataCorp =  SessionUser.SessionUser().getCorpId() ;

        Map<String, Object> rmap = new HashMap<String, Object>();
        String columnsql="select a.attname field,d.description \"header\",120 width,'center' headerAlign from pg_class c,pg_attribute a,pg_description d where c.relname='"+dbTable+"' and a.attnum>0 and a.attrelid=c.oid and d.objoid=a.attrelid and d.objsubid=a.attnum ";
        rmap.put("columndata", sysInitDataDao.QueryListMap(columnsql, new  Object[]{}));
        String sql="select * from "+dbTable+"  where data_corp='"+dataCorp+"'  and data_type='"+dataType+"'";
        rmap.put("data", sysInitDataDao.QueryListMap(sql, new  Object[]{}));
        rmap.put("code", 200);
        return  rmap;
	}

	@Override
	public Result callSolveDataProduce() {

		Integer code=null;
		String message = null;
		try {
			Map<String,Object> map=new HashMap<>();
			String	dataCorp =  SessionUser.SessionUser().getCorpId() ;
			map.put("datacorp",String.valueOf(dataCorp));

			StoredProcedureQuery storedProcedureQuery= sysInitDataDaoBasic.callSolveDataProduce("proc_solvedata",map);
			code = (Integer)storedProcedureQuery.getOutputParameterValue("code");
			message = (String)storedProcedureQuery.getOutputParameterValue("message");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(e);
		}
		return code.equals("0")?Result.resultOk(message):Result.error(message);
	}
	@Override
	public Result callSysDataInitProduce() {
		String message = "ok";
		try {
			Map<String,Object> map=new HashMap<>();
			String	dataCorp =  SessionUser.SessionUser().getCorpId() ;
			map.put("datacorp",String.valueOf(dataCorp));
			sysInitDataDaoBasic.callInitDataProduce("p_sys_datainit",map);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(e);
		}
		return Result.resultOkMsg(message);
	}
}
